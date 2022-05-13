

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.List5Metrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class List5 extends ListWorkbook {
    private final String separator = "\n";
    
    public List5(){
        super();
        sheetMetrics = new List5Metrics();
        initialize();
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount) {
        super.add1PageGroup(g, sht, start, stuCount);
        sht.getRow(sheetMetrics.getKingdom().getY()).getCell(sheetMetrics.getKingdom().getX()).setCellValue(Settings.PREF_TEMPORARY.get("KINGDOM-AR").replaceAll("%nl%", "\n"));
        sht.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX()).setCellValue(g.getLevel().getSchool().getAcademy() + separator + g.getLevel().getSchool().getDirection() + separator + g.getLevel().getSchool().getSchool());
        sht.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX()).setCellValue(g.getLevel().getSchool().getYear());
        sht.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE") + " " + g.getName());
        sht.getRow(((List5Metrics) sheetMetrics).getCustomLabel1().getY()).getCell(((List5Metrics) sheetMetrics).getCustomLabel1().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_CUSTOM_STRING"));
        sht.getRow(((List5Metrics) sheetMetrics).getCustomLabel2().getY()).getCell(((List5Metrics) sheetMetrics).getCustomLabel2().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_CUSTOM_STRING2"));
        sht.getRow(((List5Metrics) sheetMetrics).getCustomLabel3().getY()).getCell(((List5Metrics) sheetMetrics).getCustomLabel3().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_CUSTOM_STRING3"));
    }
    
    @Override
    protected void placeStudent(int index, XSSFSheet sht, Student stu) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        super.placeStudent(index, sht, stu);
        current.getCell(row.getNumero().getX()).setCellValue(stu.getNum());
        current.getCell(row.getCode().getX()).setCellValue(stu.getCode());
        current.getCell(row.getSecondName().getX()).setCellValue(stu.getFullName());
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
        row.getAdditional().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getAdditional().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getAdditional().getX() + 1, 
                n = row.getAdditional().getX() + row.getAdditional().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        }
        row.getAdditional1().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getAdditional1().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getAdditional1().getX() + 1, 
                n = row.getAdditional1().getX() + row.getAdditional1().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional1().reloadStyle(sht.getWorkbook()));
        }
        row.getAdditional2().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getAdditional2().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getAdditional2().getX() + 1, 
                n = row.getAdditional2().getX() + row.getAdditional2().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional2().reloadStyle(sht.getWorkbook()));
        }
        return xlRow;
    }
}
