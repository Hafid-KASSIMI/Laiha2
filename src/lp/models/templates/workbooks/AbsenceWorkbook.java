

package lp.models.templates.workbooks;

import java.awt.Color;
import lp.Settings;
import lp.models.templates.workbooks.metrics.AbsenceMetrics;
import lp.util.Calendar.DAY_HALF;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WEEK;
import net.mdrassty.object.Group;
import net.mdrassty.util.Format;
import net.mdrassty.util.excel.XRectangle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public abstract class AbsenceWorkbook extends BaseWorkbook {
    
    protected String sheetName;
    protected AbsenceMetrics sheetMetrics;

    public AbsenceWorkbook() {
        super();
    }
    
    protected void add1PageGroup(Group g, XSSFSheet sht, WEEK week) {
        sht.getPrintSetup().setLandscape(true);
        sht.getPrintSetup().setPaperSize(PaperSize.A4_PAPER);
    }
    
    @Override
    protected void add2PagesGroup(Group g) {
        XSSFSheet sheet;
        validateSheetName(g.getName());
        sheet = coreFile.cloneSheet(0, sheetName + "-p1");
        sheet.getRow(sheetMetrics.getPager().getY()).getCell(sheetMetrics.getPager().getX()).setCellValue("1 / 2");
        add1PageGroup(g, sheet, WEEK.FIRST_HALF);
        sheet = coreFile.cloneSheet(0, sheetName + "-p2");
        sheet.getRow(sheetMetrics.getPager().getY()).getCell(sheetMetrics.getPager().getX()).setCellValue("2 / 2");
        add1PageGroup(g, sheet, WEEK.SECOND_HALF);
    }
    
    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( coreFile == null || g == null )   return;
        validateSheetName(g.getName());
        if ( force2Pages ) {
            sheetMetrics.setDaysCount(3);
            add2PagesGroup(g);
        }
        else {
            sheetMetrics.setDaysCount(6);
            add1PageGroup( g, coreFile.cloneSheet(0, sheetName), WEEK.HOLE_WEEK );
        }
    }
    
    protected void validateSheetName(String name) {
        sheetName = name;
        if ( sheetsNames.contains(sheetName) ) {
            sheetName += " (" + sheetsNames.stream().filter(s -> s.equals(name)).count() + ")";
        }
        sheetsNames.add(name);
    }
    
    protected XSSFSheet clone(XSSFSheet sheet, String suffix) {
        return coreFile.cloneSheet(coreFile.getSheetIndex(sheet), sheet.getSheetName() + suffix);
    }
    
    protected void removeSheet(XSSFSheet sheet) {
        try {
            sheet.getWorkbook().removeSheetAt(sheet.getWorkbook().getSheetIndex(sheet));
            sheetsNames.remove(sheetName);
        } catch ( IllegalArgumentException ex ) {}
    }
    
    protected final void initializeTemplate() {
        tpl = tempaltesDir + sheetMetrics.getTemplate() + extension;
    }
    
    protected void placeTableHeader(XSSFSheet sht, WEEK w) {
        DateRange dr = getDateRange(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_FIRST_DAY_OF_WEEK"), 
                Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SELECTED_WEEK"), w);
        sht.getRow(sheetMetrics.getWeek().getY()).getCell(sheetMetrics.getWeek().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(
                Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " " + 
                dr.getBeginning() + " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " " + dr.getEnd());
        sht.getRow(sheetMetrics.getHalfDayHeader(DAY_HALF.MORNING).getY()).getCell(sheetMetrics.getHalfDayHeader(DAY_HALF.MORNING).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
        sht.getRow(sheetMetrics.getHalfDayHeader(DAY_HALF.AFTERNOON).getY()).getCell(sheetMetrics.getHalfDayHeader(DAY_HALF.AFTERNOON).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
        for ( int h = 0, hl = sheetMetrics.getHoursCount() / 2; h < hl; h++ ) {
            sht.getRow(sheetMetrics.getHour(h).getY()).getCell(sheetMetrics.getHour(h).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_" + (h + 1)));
            sht.getRow(sheetMetrics.getHour(h + hl).getY()).getCell(sheetMetrics.getHour(h + hl).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_" + (h + 1)));
        }
        for ( int j = 0, i = dr.getFirstday(), n = dr.getLastDay(), day = dr.getToday(); i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            XRectangle xRect = sheetMetrics.getDayHeader(j);
            Color c1, c2;
            XSSFRow row;
            if ( sheetMetrics.isShiftRows() )
                sht.shiftRows(xRect.getY(), sht.getLastRowNum(), 1, true, true);
            row = sht.createRow(xRect.getY());
            row.getCell(xRect.getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            row.setHeightInPoints(xRect.getHeight());
            row.getCell(xRect.getX()).setCellStyle(xRect.reloadStyle(sht.getWorkbook()));
            xRect = sheetMetrics.getToAdmin(j);
            row.getCell(xRect.getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(xRect.reloadStyle(sht.getWorkbook()));
            if ( xRect.getColSpan() > 1 ) {
                xRect.doMergeCols(sht);
                for ( int c = xRect.getX() + 1, cn = xRect.getX() + xRect.getColSpan(); c < cn; c++ ) {
                    row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(xRect.reloadStyle(sht.getWorkbook()));
                }
            }
            c1 = Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ? Format.getDefaultBorderColor() : null;
            c2 = Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ? Format.getDefaultBorderColor() : null;
            for ( int h = 0, hl = sheetMetrics.getHoursCount() / 2; h < hl; h++ ) {
                xRect = sheetMetrics.getHour(j, h);
                xRect.doMergeCols(sht);
                for ( int c = xRect.getX(), cn = xRect.getX() + xRect.getColSpan(); c < cn; c++ ) {
                    xRect.getFormat().setForeColor(c1);
                    row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(xRect.cCopyStyle(sht.getWorkbook(), FillPatternType.THIN_FORWARD_DIAG));
                }
                xRect = sheetMetrics.getHour(j, h + hl);
                xRect.doMergeCols(sht);
                for ( int c = xRect.getX(), cn = xRect.getX() + xRect.getColSpan(); c < cn; c++ ) {
                    xRect.getFormat().setForeColor(c2);
                    row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(xRect.cCopyStyle(sht.getWorkbook(), FillPatternType.THIN_FORWARD_DIAG));
                }
            }
        }
    }
    
}
