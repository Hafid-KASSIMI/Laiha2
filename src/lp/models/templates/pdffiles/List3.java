

package lp.models.templates.pdffiles;

import lp.models.templates.pdffiles.metrics.List3Metrics;
import lp.Settings;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import lp.models.templates.pdffiles.metrics.StudentPRectangle;
import org.apache.pdfbox.pdmodel.PDPage;

public class List3 extends ListPDF {
    
    public List3() {
        super();
        pageMetrics = new List3Metrics();
        tpl = tempaltesDir + pageMetrics.getTemplate() + extension;
        initialize();
    }

    @Override
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount) {
        super.add1PageGroup(g, page, start, stuCount);
        pdf.placeMultilineString(((List3Metrics) pageMetrics).getKingdom(), page,  Settings.PREF_TEMPORARY.get("KINGDOM-AR").replaceAll("%nl%", "\n"));
        pdf.placeMultilineString(pageMetrics.getSchoolInfos(), page, g.getLevel().getSchool().getAcademy() + "\n" + g.getLevel().getSchool().getDirection() + "\n" + g.getLevel().getSchool().getSchool());
        pdf.placeNRotateString(pageMetrics.getYear(), page, g.getLevel().getSchool().getYear());
        pdf.placeString(pageMetrics.getTitle(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TITLE") + " " + g.getName());
        pdf.wrapNResizeText(((List3Metrics) pageMetrics).getLastColumnLabel(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_CUSTOM_STRING"));
    }

    @Override
    protected void placeStudent(int index, PDPage page, Student stu) {
        StudentPRectangle rectangle;
        super.placeStudent(index, page, stu);
        rectangle = pageMetrics.getStudentRow(index);
        pdf.placeString(rectangle.getNumero(), page, INTEGER.format(stu.getNum()));
        pdf.placeString(rectangle.getCode(), page, stu.getCode());
        pdf.wrapNResizeText(rectangle.getSecondName(), page, stu.getFullName());
        pdf.placeString(rectangle.getGender(), page, pageMetrics.getGenderFunc().apply(stu));
        pdf.placeString(rectangle.getBirthDate(), page, stu.getBirthDate());
        pdf.wrapNResizeText(rectangle.getBirthPlace(), page, stu.getAddress());
    }
}
