

package lp.models.templates.pdffiles;

import lp.models.templates.pdffiles.metrics.List1Metrics;
import lp.Settings;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import lp.models.templates.pdffiles.metrics.StudentPRectangle;
import org.apache.pdfbox.pdmodel.PDPage;

public class List1 extends ListPDF {
    
    private final String separator;
    
    public List1() {
        super();
        pageMetrics = new List1Metrics();
        tpl = tempaltesDir + pageMetrics.getTemplate() + extension;
        separator = " " + Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SCHOOL_INFOS_SEPARATOR") + " ";
        initialize();
    }

    @Override
    protected void add1PageGroup(Group group, PDPage page, int start, int stuCount) {
        super.add1PageGroup(group, page, start, stuCount);
        pdf.placeMultilineString(pageMetrics.getSchoolInfos(), page, group.getLevel().getSchool().getAcademy() + separator + group.getLevel().getSchool().getDirection() + separator + group.getLevel().getSchool().getSchool());
        pdf.placeString(pageMetrics.getYear(), page, group.getLevel().getSchool().getYear());
        pdf.placeString(pageMetrics.getTitle(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TITLE") + " " + group.getName());
        pdf.wrapNResizeText(((List1Metrics) pageMetrics).getLastColumnLabel(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_CUSTOM_STRING"));
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
