

package lp.models.templates.pdffiles;

import lp.Settings;
import lp.models.templates.pdffiles.metrics.Marks3Metrics;
import lp.models.templates.pdffiles.metrics.StudentPRectangle;
import net.mdrassty.object.Student;
import net.mdrassty.object.Matter;
import net.mdrassty.object.Group;
import org.apache.pdfbox.pdmodel.PDPage;
import net.mdrassty.util.SEMESTER;

public class Marks3 extends MarksPDF {
    
    private final String separator;
    
    public Marks3() {
        super();
        pageMetrics = new Marks3Metrics();
        tpl = tempaltesDir + pageMetrics.getTemplate() + extension;
        separator = " " + Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SCHOOL_INFOS_SEPARATOR") + " ";
        initialize();
    }

    @Override
    protected void add1PageGroupNoMarks(Group group, PDPage page, int start, int stuCount) {
        super.add1PageGroupNoMarks(group, page, start, stuCount);
        pdf.placeString(((Marks3Metrics) pageMetrics).getTeacher(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_EMPTY_STRING"));
        pdf.placeString(((Marks3Metrics) pageMetrics).getMatter(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_EMPTY_STRING"));
        pdf.placeString(((Marks3Metrics) pageMetrics).getSemester(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_EMPTY_STRING"));
        placeCommonHeader(group, page);
    }

    @Override
    protected void add1PageGroupWithMarks(Group group, PDPage page, int start, int stuCount, 
            Matter matter, SEMESTER semester) {
        super.add1PageGroupWithMarks(group, page, start, stuCount, matter, semester);
        pdf.placeString(((Marks3Metrics) pageMetrics).getTeacher(), page, group.getTeacher(matter.getCode()));
        pdf.placeString(((Marks3Metrics) pageMetrics).getMatter(), page, matter.getLabel());
        pdf.placeString(((Marks3Metrics) pageMetrics).getSemester(), page, semester.intValue() + "");
        placeCommonHeader(group, page);
    }

    @Override
    protected void placeStudent(int index, PDPage page, Student stu) {
        StudentPRectangle rectangle;
        super.placeStudent(index, page, stu);
        rectangle = pageMetrics.getStudentRow(index);
        pdf.placeString(rectangle.getNumero(), page, INTEGER.format(stu.getNum()));
        pdf.placeString(rectangle.getCode(), page, stu.getCode());
        pdf.wrapNResizeText(rectangle.getSecondName(), page, stu.getFullName());
    }
    
    private void placeCommonHeader(Group group, PDPage page) {
        pdf.placeMultilineString(pageMetrics.getSchoolInfos(), page, group.getLevel().getSchool().getAcademy() + separator + group.getLevel().getSchool().getDirection() + separator + group.getLevel().getSchool().getSchool());
        pdf.placeString(pageMetrics.getYear(), page, group.getLevel().getSchool().getYear());
        pdf.placeString(((Marks3Metrics) pageMetrics).getYearLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_YEAR_LABEL"));
        pdf.placeString(pageMetrics.getTitle(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TITLE") + " " + group.getName());
        pdf.placeString(pageMetrics.getLastColumnLabel(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_CUSTOM_STRING"));
        pdf.placeString(((Marks3Metrics) pageMetrics).getTeacherLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_TEACHER_LABEL"));
        pdf.placeString(((Marks3Metrics) pageMetrics).getMatterLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_MATTER_LABEL"));
        pdf.placeString(((Marks3Metrics) pageMetrics).getSemesterLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_SEMESTER_LABEL"));
    }
}
