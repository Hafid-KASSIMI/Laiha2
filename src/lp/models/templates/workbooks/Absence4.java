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
import lp.models.templates.workbooks.metrics.Absence4Metrics;
import lp.util.Calendar.WEEK;
import net.mdrassty.object.Group;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author H. KASSIMI
 */
public class Absence4 extends AbsenceWorkbook {
    
    private final String separator = "\n";
    
    public Absence4() {
        super();
        sheetMetrics = new Absence4Metrics();
        initializeTemplate();
    }

    @Override
    protected void add1PageGroup(Group g, XSSFSheet sht, WEEK week) {
        super.add1PageGroup(g, sht, week);
        sht.getRow(sheetMetrics.getSchoolInfos().getY()).getCell(sheetMetrics.getSchoolInfos().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(g.getLevel().getSchool().getAcademy() + separator + g.getLevel().getSchool().getDirection() + separator + g.getLevel().getSchool().getSchool());
        sht.getRow(sheetMetrics.getYear().getY()).getCell(sheetMetrics.getYear().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_YEAR") + " " + g.getLevel().getSchool().getYear());
        sht.getRow(sheetMetrics.getTitle().getY()).getCell(sheetMetrics.getTitle().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TITLE"));
        sht.getRow(sheetMetrics.getGroup().getY()).getCell(sheetMetrics.getGroup().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(g.getName());
        sht.getRow(sheetMetrics.getBureau().getY()).getCell(sheetMetrics.getBureau().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SURVEILANCE_DESKTOP")
                    + Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SURVEILANCE_NUMERO"));
        sht.getRow(sheetMetrics.getToAdminHeader().getY()).getCell(sheetMetrics.getToAdminHeader().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_TO_ADMINISTRATION"));
        placeTableHeader(sht, week);
    }
    
}
