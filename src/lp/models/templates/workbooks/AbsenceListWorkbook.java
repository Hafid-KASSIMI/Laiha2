/*
 * Copyright (C) 2022 H. KASSIMI
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lp.models.templates.workbooks;

import lp.Settings;
import lp.models.templates.workbooks.metrics.AbsenceListMetrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WEEK;
import net.mdrassty.object.Group;
import net.mdrassty.util.excel.XRectangle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author H. KASSIMI
 */
public abstract class AbsenceListWorkbook extends ListWorkbook {
    

    @Override
    protected void addEmptyRow(XStudentRow row, XSSFSheet sht) {
        sht.shiftRows(row.getRowIndex(), sht.getLastRowNum(), 1, true, true);
        super.addEmptyRow(row, sht);
    }
    
    protected void placeTableHeader(XSSFSheet sht, WEEK w) {
        DateRange dr = getDateRange(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_FIRST_DAY_OF_WEEK"), 
                Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SELECTED_WEEK"), w);
        sht.getRow(((AbsenceListMetrics) sheetMetrics).getWeek().getY()).getCell(((AbsenceListMetrics) sheetMetrics).getWeek().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(
                Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " " + 
                dr.getBeginning() + " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " " + dr.getEnd());
        for ( int j = 0, rows = sht.getLastRowNum() - 2 - (int) sheetMetrics.getFirstRow(), i = dr.getFirstday(), 
                n = dr.getLastDay(), day = dr.getToday(); i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            sht.getRow(((AbsenceListMetrics) sheetMetrics).getDayHeader(j).getY()).getCell(((AbsenceListMetrics) sheetMetrics).getDayHeader(j).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            sht.getRow(((AbsenceListMetrics) sheetMetrics).getHalfDayHeader(j * 2).getY()).getCell(((AbsenceListMetrics) sheetMetrics).getHalfDayHeader(j * 2).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
            sht.getRow(((AbsenceListMetrics) sheetMetrics).getHalfDayHeader(j * 2 + 1).getY()).getCell(((AbsenceListMetrics) sheetMetrics).getHalfDayHeader(j * 2 + 1).getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
            
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                XRectangle tmp = ((AbsenceListMetrics) sheetMetrics).getHalfDayBody(j * 2);
                tmp.setColSpan(4);
                tmp.setRowSpan(rows);
                tmp.doMerge(sht);
                sht.getRow(tmp.getY()).getCell(tmp.getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(tmp.getStyle(sht.getWorkbook(), FillPatternType.THIN_FORWARD_DIAG));
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                XRectangle tmp = ((AbsenceListMetrics) sheetMetrics).getHalfDayBody(j * 2 + 1);
                tmp.setColSpan(4);
                tmp.setRowSpan(rows);
                tmp.doMerge(sht);
                sht.getRow(tmp.getY()).getCell(tmp.getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellStyle(tmp.getStyle(sht.getWorkbook(), FillPatternType.THIN_FORWARD_DIAG));
            }
        }
    }
    
    protected void add1PageGroupHoleWeek(Group g, XSSFSheet sheet, int start, int stuCount) {
        add1PageGroup(g, sheet, start, stuCount, WEEK.HOLE_WEEK, "");
    }
    
    protected void add1PageGroupWeekHalfs(Group g, XSSFSheet sheet, int start, int stuCount) {
        add1PageGroup(g, sheet, start, stuCount, WEEK.FIRST_HALF, "1 / 2");
        validateSheetName(g.getName());
        sheet = coreFile.cloneSheet(0, sheetName);
        add1PageGroup(g, sheet, start, stuCount, WEEK.SECOND_HALF, "2 / 2");
    }
    
    protected void add1PageGroup(Group g, XSSFSheet sht, int start, int stuCount, WEEK week, String page) {
        super.add1PageGroup(g, sht, start, stuCount);
        placeTableHeader(sht, week);
    }
    
    protected void add2PagesGroupWeekHalfs(Group g) {
        int stuCount, ini_unify_lev_size, ini_empty_rows, ini_dbl_page_size, max;
        boolean ini_unify_lev;
        stuCount = g.getStudentsCount();
        ini_dbl_page_size = sheetMetrics.getDoublePageRowsCount();
        XSSFSheet sheet;
        if ( stuCount > ini_dbl_page_size ) {
            ini_unify_lev_size = sheetMetrics.getUniLevEmptyRows();
            ini_empty_rows = sheetMetrics.getEmptyRows();
            max = stuCount + ini_unify_lev_size + ini_empty_rows;
            if ( max > ini_dbl_page_size * 2 ) {
                max = max / 2 + max % 2;
            }
            else
                max = ini_dbl_page_size;
            
            ini_unify_lev = unifyLevelGroupsSize;
            unifyLevelGroupsSize = false;
            sheetMetrics.setUniLevEmptyRows(0);
            sheetMetrics.setEmptyRows(0);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, 0, max, WEEK.FIRST_HALF, "1 / 4");
            
            unifyLevelGroupsSize = ini_unify_lev;
            sheetMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            sheetMetrics.setEmptyRows(ini_empty_rows);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, max, stuCount, WEEK.FIRST_HALF, "2 / 4");
            /*********************************************/
            unifyLevelGroupsSize = false;
            sheetMetrics.setUniLevEmptyRows(0);
            sheetMetrics.setEmptyRows(0);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, 0, max, WEEK.SECOND_HALF, "3 / 4");
            
            unifyLevelGroupsSize = ini_unify_lev;
            sheetMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            sheetMetrics.setEmptyRows(ini_empty_rows);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, max, stuCount, WEEK.SECOND_HALF, "4 / 4");
        }
        else {
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, 0, stuCount, WEEK.FIRST_HALF, "1 / 2");
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, 0, stuCount, WEEK.SECOND_HALF, "2 / 2");
        }
    }
    
    protected void add2PagesGroupHoleWeek(Group g) {
        int stuCount, ini_unify_lev_size, ini_empty_rows, ini_dbl_page_size, max;
        boolean ini_unify_lev;
        stuCount = g.getStudentsCount();
        ini_dbl_page_size = sheetMetrics.getDoublePageRowsCount();
        XSSFSheet sheet;
        if ( stuCount > ini_dbl_page_size ) {
            ini_unify_lev_size = sheetMetrics.getUniLevEmptyRows();
            ini_empty_rows = sheetMetrics.getEmptyRows();
            max = stuCount + ini_unify_lev_size + ini_empty_rows;
            if ( max > ini_dbl_page_size * 2 ) {
                max = max / 2 + max % 2;
            }
            else
                max = ini_dbl_page_size;
            
            ini_unify_lev = unifyLevelGroupsSize;
            unifyLevelGroupsSize = false;
            sheetMetrics.setUniLevEmptyRows(0);
            sheetMetrics.setEmptyRows(0);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, 0, max, WEEK.HOLE_WEEK, "1 / 2");
            
            unifyLevelGroupsSize = ini_unify_lev;
            sheetMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            sheetMetrics.setEmptyRows(ini_empty_rows);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, max, stuCount, WEEK.HOLE_WEEK, "2 / 2");
        }
        else {
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            add1PageGroup(g, sheet, 0, stuCount, WEEK.HOLE_WEEK, "");
        }
    }
    
}
