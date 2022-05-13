

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.Marks2Metrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Student;
import net.mdrassty.object.Matter;
import net.mdrassty.object.Group;
import net.mdrassty.util.SEMESTER;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Marks2 extends MarksWorkbook {
    
    public Marks2() {
        super();
        sheetMetrics = new Marks2Metrics();
        tpl = tempaltesDir + sheetMetrics.getTemplate() + extension;
        initialize();
    }

    @Override
    protected void add1PageGroupNoMarks(Group group, XSSFSheet sheet, int start, int stuCount) {
        sheet.getRow(((Marks2Metrics) sheetMetrics).getTeacher().getY()).getCell(((Marks2Metrics) sheetMetrics).getTeacher().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_EMPTY_STRING"));
        sheet.getRow(((Marks2Metrics) sheetMetrics).getMatter().getY()).getCell(((Marks2Metrics) sheetMetrics).getMatter().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_EMPTY_STRING"));
        sheet.getRow(((Marks2Metrics) sheetMetrics).getSemester().getY()).getCell(((Marks2Metrics) sheetMetrics).getSemester().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_EMPTY_STRING"));
        placeCommonHeader(group, sheet);
        super.add1PageGroupNoMarks(group, sheet, start, stuCount);
    }

    @Override
    protected void add1PageGroupWithMarks(Group group, XSSFSheet sheet, int start, int stuCount, 
            Matter matter, SEMESTER semester) {
        sheet.getRow(((Marks2Metrics) sheetMetrics).getTeacher().getY()).getCell(((Marks2Metrics) sheetMetrics).getTeacher().getX()).setCellValue(group.getTeacher(matter.getCode()));
        sheet.getRow(((Marks2Metrics) sheetMetrics).getMatter().getY()).getCell(((Marks2Metrics) sheetMetrics).getMatter().getX()).setCellValue(matter.getLabel());
        sheet.getRow(((Marks2Metrics) sheetMetrics).getSemester().getY()).getCell(((Marks2Metrics) sheetMetrics).getSemester().getX()).setCellValue(semester.intValue());
        placeCommonHeader(group, sheet);
        super.add1PageGroupWithMarks(group, sheet, start, stuCount, matter, semester);
    }

    @Override
    protected void placeStudent(int index, XSSFSheet sht, Student stu) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        super.placeStudent(index, sht, stu);
        current.getCell(row.getNumero().getX()).setCellValue(stu.getNum());
        current.getCell(row.getCode().getX()).setCellValue(stu.getCode());
        current.getCell(row.getSecondName().getX()).setCellValue(stu.getSecName());
        current.getCell(row.getFirstName().getX()).setCellValue(stu.getFirName());
    }
    
    private void placeCommonHeader(Group group, XSSFSheet sheet) {
        sheet.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX()).setCellValue(group.getLevel().getSchool().getAcademy() + "\n" + group.getLevel().getSchool().getDirection() + "\n" + group.getLevel().getSchool().getSchool());
        sheet.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX()).setCellValue(group.getLevel().getSchool().getYear());
        sheet.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE"));
        sheet.getRow(sheetMetrics.getGroup().getY()).getCell(sheetMetrics.getGroup().getX()).setCellValue(group.getName());
        sheet.getRow(((Marks2Metrics) sheetMetrics).getTeacherLbl().getY()).getCell(((Marks2Metrics) sheetMetrics).getTeacherLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_TEACHER_LABEL"));
        sheet.getRow(((Marks2Metrics) sheetMetrics).getMatterLbl().getY()).getCell(((Marks2Metrics) sheetMetrics).getMatterLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_MATTER_LABEL"));
        sheet.getRow(((Marks2Metrics) sheetMetrics).getSemesterLbl().getY()).getCell(((Marks2Metrics) sheetMetrics).getSemesterLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_SEMESTER_LABEL"));
    }
    
    @Override
    protected XSSFRow initializeRow(XStudentRow row, XSSFSheet sht) {
        XSSFRow xlRow = sht.createRow(row.getRowIndex());
        xlRow.setHeightInPoints(row.getRowHeight());
        xlRow.getCell(row.getNumero().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getNumero().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getCode().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getCode().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getSecondName().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getSecondName().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getFirstName().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getFirstName().reloadStyle(sht.getWorkbook()));
        for ( int m = 0; m < 4; m++ ) {
            xlRow.getCell(row.getMark(m).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getMark(m).reloadStyle(sht.getWorkbook()));
        }
        xlRow.getCell(row.getAi().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAi().reloadStyle(sht.getWorkbook()));
        return xlRow;
    }
}
