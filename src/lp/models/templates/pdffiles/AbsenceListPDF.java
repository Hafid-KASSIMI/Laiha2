/*
 * Copyright (C) 2022 Hafid KASSIMI (@mdrassty.net)
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
package lp.models.templates.pdffiles;

import java.awt.Color;
import lp.Settings;
import lp.models.templates.pdffiles.metrics.AbsenceListMetrics;
import org.apache.pdfbox.pdmodel.PDPage;
import lp.util.Calendar.*;
import net.mdrassty.object.Group;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public abstract class AbsenceListPDF extends ListPDF {
    
    protected final Color STRIP_COLOR = new Color(0xf0, 0xf0, 0xf0);
    protected float GRAYING_GAP = 8f;

    public AbsenceListPDF() {
        super();
    }
    
    protected void placeTableHeader(PDPage page, WEEK w) {
        DateRange dr = getDateRange(Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_FIRST_DAY_OF_WEEK"), 
                Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SELECTED_WEEK"), w);
        pdf.placeString(((AbsenceListMetrics) pageMetrics).getWeek(), page, (Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " " + 
                dr.getBeginning() + " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " " + dr.getEnd()));
        for ( int j = 0, i = dr.getFirstday(), n = dr.getLastDay(), day = dr.getToday(); i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            pdf.placeString(((AbsenceListMetrics) pageMetrics).getDayHeader(j), page, Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            pdf.wrapNResizeText(((AbsenceListMetrics) pageMetrics).getHalfDayHeader(j, DAY_HALF.MORNING), page, Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
            pdf.wrapNResizeText(((AbsenceListMetrics) pageMetrics).getHalfDayHeader(j, DAY_HALF.AFTERNOON), page, Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                pdf.gray(((AbsenceListMetrics) pageMetrics).getHalfDayBody(j, DAY_HALF.MORNING), page, true, GRAYING_GAP);
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                pdf.gray(((AbsenceListMetrics) pageMetrics).getHalfDayBody(j, DAY_HALF.AFTERNOON), page, true, GRAYING_GAP);
            }
        }
    }
    
    protected void add1PageGroupHoleWeek(Group g, PDPage page, int start, int stuCount) {
        add1PageGroup(g, page, start, stuCount, WEEK.HOLE_WEEK, "");
    }
    
    protected void add1PageGroupWeekHalfs(Group g, PDPage page, int start, int stuCount) {
        add1PageGroup(g, page, start, stuCount, WEEK.FIRST_HALF, "1 / 2");
        add1PageGroup(g, pdf.clonePage(0), start, stuCount, WEEK.SECOND_HALF, "2 / 2");
    }
    
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount, WEEK week, String pager) {
        super.add1PageGroup(g, page, start, stuCount);
        placeTableHeader(page, week);
    }
    
    protected void add2PagesGroupWeekHalfs(Group g) {
        int stuCount, ini_unify_lev_size, ini_empty_rows, ini_dbl_page_size, max;
        boolean ini_unify_lev;
        stuCount = g.getStudentsCount();
        ini_dbl_page_size = pageMetrics.getDoublePageRowsCount();
        PDPage page;
        if ( stuCount > ini_dbl_page_size ) {
            ini_unify_lev_size = pageMetrics.getUniLevEmptyRows();
            ini_empty_rows = pageMetrics.getEmptyRows();
            max = stuCount + ini_unify_lev_size + ini_empty_rows;
            if ( max > ini_dbl_page_size * 2 ) {
                max = max / 2 + max % 2;
            }
            else
                max = ini_dbl_page_size;
            
            ini_unify_lev = unifyLevelGroupsSize;
            unifyLevelGroupsSize = false;
            pageMetrics.setUniLevEmptyRows(0);
            pageMetrics.setEmptyRows(0);
            add1PageGroup(g, pdf.clonePage(0), 0, max, WEEK.FIRST_HALF, "1 / 4");
            
            unifyLevelGroupsSize = ini_unify_lev;
            pageMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            pageMetrics.setEmptyRows(ini_empty_rows);
            add1PageGroup(g, pdf.clonePage(0), max, stuCount, WEEK.FIRST_HALF, "2 / 4");
            
            unifyLevelGroupsSize = false;
            pageMetrics.setUniLevEmptyRows(0);
            pageMetrics.setEmptyRows(0);
            add1PageGroup(g, pdf.clonePage(0), 0, max, WEEK.SECOND_HALF, "3 / 4");
            
            unifyLevelGroupsSize = ini_unify_lev;
            pageMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            pageMetrics.setEmptyRows(ini_empty_rows);
            add1PageGroup(g, pdf.clonePage(0), max, stuCount, WEEK.SECOND_HALF, "4 / 4");
        }
        else {
            add1PageGroup(g, pdf.clonePage(0), 0, stuCount, WEEK.FIRST_HALF, "1 / 2");
            add1PageGroup(g, pdf.clonePage(0), 0, stuCount, WEEK.SECOND_HALF, "2 / 2");
        }
    }
    
    protected void add2PagesGroupHoleWeek(Group g) {
        int stuCount, ini_unify_lev_size, ini_empty_rows, ini_dbl_page_size, max;
        boolean ini_unify_lev;
        stuCount = g.getStudentsCount();
        ini_dbl_page_size = pageMetrics.getDoublePageRowsCount();
        PDPage page;
        if ( stuCount > ini_dbl_page_size ) {
            ini_unify_lev_size = pageMetrics.getUniLevEmptyRows();
            ini_empty_rows = pageMetrics.getEmptyRows();
            max = stuCount + ini_unify_lev_size + ini_empty_rows;
            if ( max > ini_dbl_page_size * 2 ) {
                max = max / 2 + max % 2;
            }
            else
                max = ini_dbl_page_size;
            
            ini_unify_lev = unifyLevelGroupsSize;
            unifyLevelGroupsSize = false;
            pageMetrics.setUniLevEmptyRows(0);
            pageMetrics.setEmptyRows(0);
            page = pdf.clonePage(0);
            add1PageGroup(g, page, 0, max, WEEK.HOLE_WEEK, "1 / 2");
            
            unifyLevelGroupsSize = ini_unify_lev;
            pageMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            pageMetrics.setEmptyRows(ini_empty_rows);
            page = pdf.clonePage(0);
            add1PageGroup(g, page, max, stuCount, WEEK.HOLE_WEEK, "2 / 2");
        }
        else {
            page = pdf.clonePage(0);
            add1PageGroup(g, page, 0, stuCount, WEEK.HOLE_WEEK, "");
        }
    }
}
