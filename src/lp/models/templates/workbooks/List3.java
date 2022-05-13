

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.List3Metrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class List3 extends ListWorkbook {
    private final String separator = "\n";
    
    public List3(){
        super();
        sheetMetrics = new List3Metrics();
        initialize();
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount) {
        super.add1PageGroup(g, sht, start, stuCount);
        sht.getRow(sheetMetrics.getKingdom().getY()).getCell(sheetMetrics.getKingdom().getX()).setCellValue(Settings.PREF_TEMPORARY.get("KINGDOM-AR").replaceAll("%nl%", "\n"));
        sht.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX()).setCellValue(g.getLevel().getSchool().getAcademy() + separator + g.getLevel().getSchool().getDirection() + separator + g.getLevel().getSchool().getSchool());
        sht.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX()).setCellValue(g.getLevel().getSchool().getYear());
        sht.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE") + " " + g.getName());
        sht.getRow(((List3Metrics) sheetMetrics).getLastColumnLabel().getY()).getCell(((List3Metrics) sheetMetrics).getLastColumnLabel().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_CUSTOM_STRING"));
    }
    
    @Override
    protected void placeStudent(int index, XSSFSheet sht, Student stu) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        super.placeStudent(index, sht, stu);
        current.getCell(row.getNumero().getX()).setCellValue(stu.getNum());
        current.getCell(row.getCode().getX()).setCellValue(stu.getCode());
        current.getCell(row.getSecondName().getX()).setCellValue(stu.getFullName());
        current.getCell(row.getGender().getX()).setCellValue(sheetMetrics.getGenderFunc().apply(stu));
        current.getCell(row.getBirthDate().getX()).setCellValue(stu.getBirthDate());
        current.getCell(row.getBirthPlace().getX()).setCellValue(stu.getAddress());

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
        row.getGender().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getGender().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getGender().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getGender().getX() + 1, 
                n = row.getGender().getX() + row.getGender().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getGender().reloadStyle(sht.getWorkbook()));
        }
        row.getBirthDate().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getBirthDate().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getBirthDate().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getBirthDate().getX() + 1, 
                n = row.getBirthDate().getX() + row.getBirthDate().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getBirthDate().reloadStyle(sht.getWorkbook()));
        }
        row.getBirthPlace().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getBirthPlace().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getBirthPlace().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getBirthPlace().getX() + 1, 
                n = row.getBirthPlace().getX() + row.getBirthPlace().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getBirthPlace().reloadStyle(sht.getWorkbook()));
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
