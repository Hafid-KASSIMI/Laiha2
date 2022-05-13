

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.Marks5Metrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Student;
import net.mdrassty.object.Matter;
import net.mdrassty.object.Group;
import net.mdrassty.util.SEMESTER;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Marks5 extends MarksWorkbook {
    
    public Marks5() {
        super();
        sheetMetrics = new Marks5Metrics();
        tpl = tempaltesDir + sheetMetrics.getTemplate() + extension;
        initialize();
    }

    @Override
    protected void add1PageGroupNoMarks(Group group, XSSFSheet sheet, int start, int stuCount) {
        sheet.getRow(((Marks5Metrics) sheetMetrics).getTeacher().getY()).getCell(((Marks5Metrics) sheetMetrics).getTeacher().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_EMPTY_STRING"));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getMatter().getY()).getCell(((Marks5Metrics) sheetMetrics).getMatter().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_EMPTY_STRING"));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getSemester().getY()).getCell(((Marks5Metrics) sheetMetrics).getSemester().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_EMPTY_STRING"));
        placeCommonHeader(group, sheet);
        super.add1PageGroupNoMarks(group, sheet, start, stuCount);
    }

    @Override
    protected void add1PageGroupWithMarks(Group group, XSSFSheet sheet, int start, int stuCount, 
            Matter matter, SEMESTER semester) {
        sheet.getRow(((Marks5Metrics) sheetMetrics).getTeacher().getY()).getCell(((Marks5Metrics) sheetMetrics).getTeacher().getX()).setCellValue(group.getTeacher(matter.getCode()));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getMatter().getY()).getCell(((Marks5Metrics) sheetMetrics).getMatter().getX()).setCellValue(matter.getLabel());
        sheet.getRow(((Marks5Metrics) sheetMetrics).getSemester().getY()).getCell(((Marks5Metrics) sheetMetrics).getSemester().getX()).setCellValue(semester.intValue());
        placeCommonHeader(group, sheet);
        super.add1PageGroupWithMarks(group, sheet, start, stuCount, matter, semester);
    }

    @Override
    protected void placeStudent(int index, XSSFSheet sht, Student stu) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        super.placeStudent(index, sht, stu);
        current.getCell(row.getNumero().getX()).setCellValue(stu.getNum());
        current.getCell(row.getCode().getX()).setCellValue(stu.getCode());
        current.getCell(row.getSecondName().getX()).setCellValue(stu.getFullName());
    }
    
    private void placeCommonHeader(Group group, XSSFSheet sheet) {
        sheet.getRow(sheetMetrics.getKingdom().getY()).getCell(sheetMetrics.getKingdom().getX()).setCellValue(Settings.PREF_TEMPORARY.get("KINGDOM-AR").replaceAll("%nl%", "\n"));
        sheet.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX()).setCellValue(group.getLevel().getSchool().getAcademy() + "\n" + group.getLevel().getSchool().getDirection() + "\n" + group.getLevel().getSchool().getSchool());
        sheet.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX()).setCellValue(group.getLevel().getSchool().getYear());
        sheet.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE") + " " + group.getName());
        sheet.getRow(sheetMetrics.getLastColumnLabel().getY()).getCell(sheetMetrics.getLastColumnLabel().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_CUSTOM_STRING"));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getTeacherLbl().getY()).getCell(((Marks5Metrics) sheetMetrics).getTeacherLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_TEACHER_LABEL"));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getMatterLbl().getY()).getCell(((Marks5Metrics) sheetMetrics).getMatterLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_MATTER_LABEL"));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getSemesterLbl().getY()).getCell(((Marks5Metrics) sheetMetrics).getSemesterLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_SEMESTER_LABEL"));
        sheet.getRow(((Marks5Metrics) sheetMetrics).getYearLbl().getY()).getCell(((Marks5Metrics) sheetMetrics).getYearLbl().getX()).setCellValue(Settings.PREF_TEMPORARY.get("MARKS_YEAR_LABEL"));
    }
    
    @Override
    protected XSSFRow initializeRow(XStudentRow row, XSSFSheet sht) {
        XSSFRow xlRow = sht.createRow(row.getRowIndex());
        xlRow.setHeightInPoints(row.getRowHeight());
        xlRow.getCell(row.getNumero().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getNumero().reloadStyle(sht.getWorkbook()));
        row.getCode().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getCode().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getCode().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getCode().getX() + 1, n = row.getCode().getX() + row.getCode().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getCode().reloadStyle(sht.getWorkbook()));
        }
        row.getSecondName().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getSecondName().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getSecondName().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getSecondName().getX() + 1, 
                n = row.getSecondName().getX() + row.getSecondName().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getSecondName().reloadStyle(sht.getWorkbook()));
        }
        for ( int m = 0; m < 4; m++ ) {
            row.getMark(m).doMergeCols(sht, row.getRowIndex());
            xlRow.getCell(row.getMark(m).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getMark(m).reloadStyle(sht.getWorkbook()));
            for ( int i = row.getMark(m).getX() + 1, 
                    n = row.getMark(m).getX() + row.getMark(m).getColSpan(); i < n; i++ ) {
                xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getMark(m).reloadStyle(sht.getWorkbook()));
            }
        }
        row.getAi().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getAi().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAi().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getAi().getX() + 1, 
                n = row.getAi().getX() + row.getAi().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAi().reloadStyle(sht.getWorkbook()));
        }
        row.getAdditional().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getAdditional().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getAdditional().getX() + 1, 
                n = row.getAdditional().getX() + row.getAdditional().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        }
        return xlRow;
    }
}
