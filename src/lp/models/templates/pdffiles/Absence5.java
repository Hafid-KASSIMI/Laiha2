

package lp.models.templates.pdffiles;

import lp.models.templates.pdffiles.metrics.Absence5Metrics;
import lp.Settings;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import lp.models.templates.pdffiles.metrics.StudentPRectangle;
import lp.util.Calendar.WEEK;
import org.apache.pdfbox.pdmodel.PDPage;

public class Absence5 extends AbsenceListPDF {
    
    public Absence5() {
        super();
        pageMetrics = new Absence5Metrics();
        tpl = tempaltesDir + pageMetrics.getTemplate() + extension;
        initialize();
    }

    @Override
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount) {
        super.add1PageGroupWeekHalfs(g, page, start, stuCount);
    }

    @Override
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount, WEEK week, String pager) {
        super.add1PageGroup(g, page, start, stuCount, week, pager);
        pdf.placeMultilineString(pageMetrics.getSchoolInfos(), page, g.getLevel().getSchool().getAcademy() + "\n" + g.getLevel().getSchool().getDirection() + "\n" + g.getLevel().getSchool().getSchool());
        pdf.placeString(pageMetrics.getTitle(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TITLE"));
        pdf.placeString(pageMetrics.getYear(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_YEAR") + " " + g.getLevel().getSchool().getYear());
        pdf.placeString(((Absence5Metrics) pageMetrics).getBureau(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SURVEILANCE_DESKTOP")
                                + Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SURVEILANCE_NUMERO"));
        pdf.placeString(((Absence5Metrics) pageMetrics).getToAdmin(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TO_ADMINISTRATION"));
        pdf.placeString(((Absence5Metrics) pageMetrics).getSignature(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SIGNATURE"));
        pdf.placeString(pageMetrics.getGroup(), page, g.getName());
        pdf.placeNRotateString(pageMetrics.getPager(), page, pager);
    }

    @Override
    protected void add2PagesGroup(Group g) {
        super.add2PagesGroupWeekHalfs(g);
    }

    @Override
    protected void placeStudent(int index, PDPage page, Student stu) {
        StudentPRectangle rectangle;
        super.placeStudent(index, page, stu);
        rectangle = pageMetrics.getStudentRow(index);
        pdf.wrapNResizeText(rectangle.getSecondName(), page, stu.getFullName());
        pdf.placeString(rectangle.getNumero(), page, INTEGER.format(stu.getNum()));
    }
}
