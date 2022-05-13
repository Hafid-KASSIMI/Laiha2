

package lp.models.templates.workbooks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import lp.models.templates.BaseFile;
import lp.util.FILE_TYPE;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class BaseWorkbook extends BaseFile<XSSFWorkbook> {
    protected String tpl;
    protected final String tempaltesDir = "/lp/resources/templates/";
    protected final ArrayList<String> sheetsNames = new ArrayList();
    
    public BaseWorkbook(){
        extension = ".xlsx";
        type = FILE_TYPE.XLSX;
    }
    
    @Override
    public final void reset(){
        sheetsNames.clear();
        try {
            coreFile = new XSSFWorkbook(getClass().getResource(tpl).openStream());
        } catch (IOException ex) {
            coreFile = null;
        }
    }
    
//    protected abstract void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount);
    
//    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount, int page, int pages) {
        // May be overidden
//    }
    
    @Override
    public boolean save(String path) {
        coreFile.removeSheetAt(0);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            coreFile.write(fos);
            coreFile.close();
            return true;
        } catch (IOException ex) { 
            return false;
        }
    }
    
//    protected HorizontalAlignment getAlignment(String alignment) {
//        switch ( alignment ) {
//            case "L":
//                return HorizontalAlignment.LEFT;
//            case "R":
//                return HorizontalAlignment.RIGHT;
//            default:
//                return HorizontalAlignment.CENTER;
//        }
//    }
//    
//    protected void grayDayHalf(XSSFSheet sheet, String[] arr, int column) {
//        CellRangeAddress cr;
//        XSSFCell cell;
//        CellStyle grayedStyle;
//        cr = CellRangeAddress.valueOf(arr[column]);
//        sheet.addMergedRegion(cr);
//        cell = sheet.getRow(cr.getFirstRow()).getCell(cr.getFirstColumn(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//        grayedStyle = cell.getSheet().getWorkbook().createCellStyle();
//        grayedStyle.cloneStyleFrom(cell.getCellStyle());
//        grayedStyle.setFillPattern(FillPatternType.THIN_FORWARD_DIAG);
//        grayedStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        grayedStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
//        cell.setCellStyle(grayedStyle);
//    }
    
}
