

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.Absence7Metrics;
import lp.models.templates.workbooks.metrics.XAbsenceStudentRow;
import lp.models.templates.workbooks.metrics.XStudentRow;
import lp.util.Calendar.WEEK;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import net.mdrassty.util.excel.XRectangle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Absence7 extends AbsenceListWorkbook {
    private final String separator = "\n";
    
    public Absence7(){
        super();
        sheetMetrics = new Absence7Metrics();
        initialize();
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sheet, int start, int stuCount) {
        super.add1PageGroupWeekHalfs(g, sheet, start, stuCount);
    }

    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount, WEEK week, String page) {
        super.add1PageGroup(g, sht, start, stuCount, week, page);
        sht.getRow(sheetMetrics.getKingdom().getY()).getCell(sheetMetrics.getKingdom().getX()).setCellValue(Settings.PREF_TEMPORARY.get("KINGDOM-AR").replaceAll("%nl%", "\n"));
        sht.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX()).setCellValue(g.getLevel().getSchool().getAcademy() + separator + g.getLevel().getSchool().getDirection() + separator + g.getLevel().getSchool().getSchool());
        sht.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_YEAR") + " " + g.getLevel().getSchool().getYear());
        sht.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE"));
        sht.getRow(sheetMetrics.getGroup().getY()).getCell(sheetMetrics.getGroup().getX()).setCellValue(g.getName());
        sht.getRow(((Absence7Metrics) sheetMetrics).getBureau().getY()).getCell(((Absence7Metrics) sheetMetrics).getBureau().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SURVEILANCE_DESKTOP")
                                + Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SURVEILANCE_NUMERO"));
        sht.getRow(sht.getLastRowNum() - 1).getCell(((Absence7Metrics) sheetMetrics).getSignature().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SIGNATURE"));
        sht.getRow(sht.getLastRowNum()).getCell(((Absence7Metrics) sheetMetrics).getToAdmin().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TO_ADMINISTRATION"));  
        sht.getRow(sht.getLastRowNum()).getCell(sheetMetrics.getPager().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(page);
    }

    @Override
    protected void add2PagesGroup(Group g) {
        super.add2PagesGroupWeekHalfs(g);
    }
    
    @Override
    protected void placeStudent(int index, XSSFSheet sht, Student stu) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        sht.shiftRows(row.getRowIndex(), sht.getLastRowNum(), 1, true, true);
        super.placeStudent(index, sht, stu);
        current.getCell(row.getNumero().getX()).setCellValue(stu.getNum());
        current.getCell(row.getSecondName().getX()).setCellValue(stu.getFullName());

    }
    
    @Override
    protected XSSFRow initializeRow(XStudentRow row, XSSFSheet sht) {
        XSSFRow xlRow = sht.createRow(row.getRowIndex());
        xlRow.setHeightInPoints(row.getRowHeight());
        xlRow.getCell(row.getNumero().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getNumero().reloadStyle(sht.getWorkbook()));
        row.getSecondName().doMergeCols(sht, row.getRowIndex());
        xlRow.getCell(row.getSecondName().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getSecondName().reloadStyle(sht.getWorkbook()));
        for ( int i = row.getSecondName().getX() + 1, 
                n = row.getSecondName().getX() + row.getSecondName().getColSpan(); i < n; i++ ) {
            xlRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getSecondName().reloadStyle(sht.getWorkbook()));
        }
        for ( XRectangle hour : ((XAbsenceStudentRow) row).getHours() ) {
            xlRow.getCell(hour.getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(hour.reloadStyle(sht.getWorkbook()));
        }
        return xlRow;
    }
}
