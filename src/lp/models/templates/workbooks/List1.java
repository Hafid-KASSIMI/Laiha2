

package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.List1Metrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class List1 extends ListWorkbook {
    
    private final String separator;
    
    public List1(){
        super();
        sheetMetrics = new List1Metrics();
        separator = " " + Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SCHOOL_INFOS_SEPARATOR") + " ";
        initialize();
    }
    
    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount) {
        super.add1PageGroup(g, sht, start, stuCount);
        sht.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX()).setCellValue(g.getLevel().getSchool().getAcademy() + separator + g.getLevel().getSchool().getDirection() + separator + g.getLevel().getSchool().getSchool());
        sht.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX()).setCellValue(g.getLevel().getSchool().getYear());
        sht.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE") + " " + g.getName());
        sht.getRow(((List1Metrics) sheetMetrics).getLastColumnLabel().getY()).getCell(((List1Metrics) sheetMetrics).getLastColumnLabel().getX()).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_CUSTOM_STRING"));
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
        xlRow.getCell(row.getCode().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getCode().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getSecondName().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getSecondName().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getGender().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getGender().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getBirthDate().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getBirthDate().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getBirthPlace().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getBirthPlace().reloadStyle(sht.getWorkbook()));
        xlRow.getCell(row.getAdditional().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(row.getAdditional().reloadStyle(sht.getWorkbook()));
        return xlRow;
    }
    
}
