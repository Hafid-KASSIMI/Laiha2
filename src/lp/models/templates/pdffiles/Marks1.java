

package lp.models.templates.pdffiles;

import lp.Settings;
import lp.models.templates.pdffiles.metrics.Marks1Metrics;
import lp.models.templates.pdffiles.metrics.StudentPRectangle;
import net.mdrassty.object.Student;
import net.mdrassty.object.Matter;
import net.mdrassty.object.Group;
import org.apache.pdfbox.pdmodel.PDPage;
import net.mdrassty.util.SEMESTER;

public class Marks1 extends MarksPDF {
    
    public Marks1() {
        super();
        pageMetrics = new Marks1Metrics();
        tpl = tempaltesDir + pageMetrics.getTemplate() + extension;
        initialize();
    }

    @Override
    protected void add1PageGroupNoMarks(Group group, PDPage page, int start, int stuCount) {
        super.add1PageGroupNoMarks(group, page, start, stuCount);
        pdf.placeString(((Marks1Metrics) pageMetrics).getTeacher(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_EMPTY_STRING"));
        pdf.placeString(((Marks1Metrics) pageMetrics).getMatter(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_EMPTY_STRING"));
        pdf.placeString(((Marks1Metrics) pageMetrics).getSemester(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_EMPTY_STRING"));
        placeCommonHeader(group, page);
    }

    @Override
    protected void add1PageGroupWithMarks(Group group, PDPage page, int start, int stuCount, 
            Matter matter, SEMESTER semester) {
        super.add1PageGroupWithMarks(group, page, start, stuCount, matter, semester);
        pdf.placeString(((Marks1Metrics) pageMetrics).getTeacher(), page, group.getTeacher(matter.getCode()));
        pdf.placeString(((Marks1Metrics) pageMetrics).getMatter(), page, matter.getLabel());
        pdf.placeString(((Marks1Metrics) pageMetrics).getSemester(), page, semester.intValue() + "");
        placeCommonHeader(group, page);
    }

    @Override
    protected void placeStudent(int index, PDPage page, Student stu) {
        StudentPRectangle rectangle;
        super.placeStudent(index, page, stu);
        rectangle = pageMetrics.getStudentRow(index);
        pdf.placeString(rectangle.getNumero(), page, INTEGER.format(stu.getNum()));
        pdf.placeString(rectangle.getCode(), page, stu.getCode());
        pdf.wrapNResizeText(rectangle.getFirstName(), page, stu.getFirName());
        pdf.wrapNResizeText(rectangle.getSecondName(), page, stu.getSecName());
    }
    
    private void placeCommonHeader(Group group, PDPage page) {
        pdf.placeMultilineString(pageMetrics.getSchoolInfos(), page, group.getLevel().getSchool().getAcademy() + "\n" + group.getLevel().getSchool().getDirection() + "\n" + group.getLevel().getSchool().getSchool());
        pdf.placeMultilineString(pageMetrics.getKingdom(), page, Settings.PREF_TEMPORARY.get("KINGDOM-AR").replaceAll("%nl%", "\n"));
        pdf.placeString(pageMetrics.getYear(), page, group.getLevel().getSchool().getYear());
        pdf.placeString(pageMetrics.getTitle(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TITLE"));
        pdf.placeString(pageMetrics.getGroup(), page, group.getName());
        pdf.placeString(((Marks1Metrics) pageMetrics).getTeacherLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_TEACHER_LABEL"));
        pdf.placeString(((Marks1Metrics) pageMetrics).getMatterLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_MATTER_LABEL"));
        pdf.placeString(((Marks1Metrics) pageMetrics).getSemesterLbl(), page, Settings.PREF_TEMPORARY.get("MARKS_SEMESTER_LABEL"));
    }
}
