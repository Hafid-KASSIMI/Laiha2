

package lp.models.templates;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import lp.Settings;
import lp.util.FILE_TYPE;
import lp.util.ProcessingObserver;
import net.mdrassty.object.DataObject;
import net.mdrassty.object.Group;

public class TemplateProcessor {
    protected BaseFile bFile;
    protected ArrayList<DataObject> items;
    private String dirPath, title, jobsKey;
    private final ArrayList<String> processedNames = new ArrayList();
    private final ProcessingObserver jobs;

    public TemplateProcessor(ArrayList<DataObject> items, ProcessingObserver jobs, String jobsKey) {
        this(jobs, jobsKey);
        setItems(items);
    }

    public TemplateProcessor(ProcessingObserver jobs, String jobsKey) {
        bFile = null;
        this.jobs = jobs;
        this.jobsKey = jobsKey;
    }

    public BaseFile getFile() {
        return bFile;
    }

    public void setFile(BaseFile bFile) {
        this.bFile = bFile;
    }

    public final void setItems(ArrayList<DataObject> items) {
        this.items = items;
    }
    
    protected String generateFileName() {
        return "/Listes [" + title + "] " + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now()) + bFile.getExtension();
    }
    
    protected String generateFileName(String grpName) {
        return "/" + grpName + " [" + title + "] " + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now()) + bFile.getExtension();
    }
    
    protected String generateDirectoryName() {
        String s = ( ( bFile.getType() == FILE_TYPE.PDF ) ? "Listes PDF " : "Classeurs Excel " ) + " [" + title + "] ";
        return "/" + s + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now());
    }
    
    public void export() {
        Boolean force2pgs = Settings.PREF_TEMPORARY.get("TWO_SHEETS_PER_GROUP").equals("Y");
        Boolean oneFilePerGrp = Settings.PREF_TEMPORARY.get("ONE_FILE_PER_GROUP").equals("Y");
        String selectedDest = Settings.PREF_TEMPORARY.get("OUTPUT_DIR");
        if ( !(new File(selectedDest)).exists() ) {
            selectedDest = Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR");
            if ( !(new File(selectedDest)).exists() ) {
                selectedDest = net.mdrassty.util.EnvVariable.HOMEDirectory();
                Settings.PREF_TEMPORARY.update("OUTPUT_DIR", selectedDest);
            }
        }
        if ( oneFilePerGrp ) {
            dirPath = selectedDest + generateDirectoryName();
            processedNames.clear();
            if ( !(new File(dirPath)).mkdir() )
                dirPath = selectedDest;
            items.forEach((item) -> {
                if ( item.isGroup() ) {
                    Group tmpGrp = Settings.DATABASES.get(item.getUId()).getLevel(((Group) item).getLevelName()).getGroup(item.toString());
                    long n = processedNames.stream().filter(name -> tmpGrp.getName().equals(name)).count();
                    bFile.reset();
                    bFile.addGroup(tmpGrp, force2pgs);
                    jobs.decrement(jobsKey);
                    processedNames.add(tmpGrp.getName());
                    bFile.save(dirPath + generateFileName(tmpGrp.getName() + (( n > 0 ) ? " (" + ++n + ")" : "")));
                }
                else if ( item.isLevel() ) {
                    Settings.DATABASES.get(item.getUId()).getLevel(item.toString()).getGroups().forEach((g) -> {
                        long n = processedNames.stream().filter(name -> g.getName().equals(name)).count();
                        bFile.reset();
                        bFile.addGroup(g, force2pgs);
                        jobs.decrement(jobsKey);
                        processedNames.add(g.getName());
                        bFile.save(dirPath + generateFileName(g.getName() + (( n > 0 ) ? " (" + ++n + ")" : "")));
                    });
                }
                else {
                    Settings.DATABASES.get(item.getUId()).getLevels().forEach((l) -> {
                        l.getGroups().forEach((g) -> {
                            long n = processedNames.stream().filter(name -> g.getName().equals(name)).count();
                            bFile.reset();
                            bFile.addGroup(g, force2pgs);
                            jobs.decrement(jobsKey);
                            processedNames.add(g.getName());
                            bFile.save(dirPath + generateFileName(g.getName() + (( n > 0 ) ? " (" + ++n + ")" : "")));
                        });
                    });
                }
            });
        }
        else {
            bFile.reset();
            items.forEach((item) -> {
                if ( item.isGroup() ) {
                    bFile.addGroup(Settings.DATABASES.get(item.getUId()).getLevel(((Group) item).getLevelName()).getGroup(item.toString()), force2pgs);
                    jobs.decrement(jobsKey);
                }
                else if ( item.isLevel() ) {
                    Settings.DATABASES.get(item.getUId()).getLevel(item.toString()).getGroups().forEach(g -> {
                        bFile.addGroup(g, force2pgs);
                        jobs.decrement(jobsKey);
                    });
                }
                else {
                    Settings.DATABASES.get(item.getUId()).getLevels().forEach(l -> {
                        l.getGroups().forEach(g -> {
                            bFile.addGroup(g, force2pgs);
                            jobs.decrement(jobsKey);
                        });
                    });
                }
            });
            bFile.save(selectedDest + generateFileName());
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
