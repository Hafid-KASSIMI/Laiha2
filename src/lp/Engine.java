/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/

package lp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import lp.models.templates.BaseFile;
import lp.models.templates.TemplateProcessor;
import lp.models.templates.pdffiles.BasePDF;
import lp.models.templates.workbooks.BaseWorkbook;
import lp.util.ProcessingObserver;
import net.mdrassty.database.Preferences;
import net.mdrassty.object.DataObject;


public class Engine {

    private ArrayList<DataObject> items;
    private final ProcessingObserver jobs;
    private final String xlKey = "XLSX", pdfKey = "PDF";
    private ChangeListener xlListener, pdfListener;
    protected final DecimalFormat INTEGER = new DecimalFormat();

    public Engine() {
        jobs = new ProcessingObserver();
        INTEGER.applyPattern("00");
    }

    public final void setItems(ArrayList<DataObject> items) {
        this.items = items;
    }

    public void setXlListener(ChangeListener xlListener) {
        this.xlListener = xlListener;
    }

    public void setPdfListener(ChangeListener pdfListener) {
        this.pdfListener = pdfListener;
    }
    
    public double getXlPercentage() {
        return jobs.getPercentage(xlKey);
    }
    
    public double getPdfPercentage() {
        return jobs.getPercentage(pdfKey);
    }
    
    public double getPercentage() {
        return jobs.getPercentage();
    }
    
    public void vroom() {
        try {
            int itemsCount = items.stream().collect(Collectors.summingInt(o -> {
                if ( o.isGroup() ) 
                    return 1;
                else if ( o.isLevel() )
                    return Settings.DATABASES.get(o.getUId()).getLevel(o.toString()).getGroupsCount();
                else
                    return Settings.DATABASES.get(o.getUId()).getGroupsCount();
            }));
            Settings.PREF_TEMPORARY = (Preferences) Settings.PREF_BUNDLE.clone();
            Settings.SELECTED_TEMPLATES_TEMPORARY = new ArrayList(Settings.SELECTED_TEMPLATES);
            itemsCount *= Settings.SELECTED_TEMPLATES_TEMPORARY.size();
            if (Settings.PREF_TEMPORARY.get("PDF_EXPORT").equals("Y")) {
                jobs.setJobs(pdfKey, itemsCount);
                jobs.getJobsProperty(pdfKey).addListener(pdfListener);
                new Thread(() -> {
                    Settings.SELECTED_TEMPLATES_TEMPORARY.forEach(template -> {
                        if ( Settings.AVAILABLE_TEMPLATES.containsKey(template) ) {
                            try {
                                process((BasePDF) Class.forName("lp.models.templates.pdffiles." + 
                                        Settings.AVAILABLE_TEMPLATES.get(template).getClassName()).newInstance(), 
                                        Settings.AVAILABLE_TEMPLATES.get(template).getNumero(), pdfKey);
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) { }
                        }
                    });
                }).start();
            }
            if (Settings.PREF_TEMPORARY.get("XL_EXPORT").equals("Y")) {
                jobs.setJobs(xlKey, itemsCount);
                jobs.getJobsProperty(xlKey).addListener(xlListener);
                new Thread(() -> {
                    Settings.SELECTED_TEMPLATES_TEMPORARY.forEach( template -> {
                        if ( Settings.AVAILABLE_TEMPLATES.containsKey(template) ) {
                            try {
//                                long startTime = System.currentTimeMillis();
                                process((BaseWorkbook) Class.forName("lp.models.templates.workbooks." + 
                                        Settings.AVAILABLE_TEMPLATES.get(template).getClassName()).newInstance(), 
                                        Settings.AVAILABLE_TEMPLATES.get(template).getNumero(), xlKey);
//                                System.out.println((System.currentTimeMillis() - startTime) / 1000f);
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) { }
                        }
                    });
                }).start();
            }
        } catch (CloneNotSupportedException ex) {}
    }
    
    private void process(BaseFile file, Integer numero, String key) {
        TemplateProcessor tpl = new TemplateProcessor(items, jobs, key);
        tpl.setFile(file);
        tpl.setTitle(INTEGER.format(numero));
        if (tpl.getFile() != null) {
            tpl.export();
        }
    }

}
