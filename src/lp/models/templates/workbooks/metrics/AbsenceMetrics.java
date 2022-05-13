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
package lp.models.templates.workbooks.metrics;

import lp.models.templates.Metrics;
import lp.util.Calendar.DAY_HALF;
import net.mdrassty.util.excel.XRectangle;

/**
 *
 * @author H. KASSIMI
 */
public class AbsenceMetrics extends Metrics<XRectangle> {
    protected XRectangle bureau, week, toAdminHeader, toAdmin, message;
    protected XRectangle dayHeader, halfDayHeader, halfDayBody;
    protected XRectangle[] hours;
    protected String[] halfDaysRefs, hoursRefs;
    protected float workAreaWidth, workAreaHeight;
    protected int firstRow, daysCount;
    protected boolean shiftRows = false;
    
    public AbsenceMetrics() {
        super();
        schoolInfos =  new XRectangle();
        title =  new XRectangle();
        group =  new XRectangle();
        year = new XRectangle();
        week = new XRectangle();
        toAdminHeader = new XRectangle();
        toAdmin = new XRectangle();
        bureau = new XRectangle();
        dayHeader = new XRectangle();
        halfDayHeader = new XRectangle();
        halfDayBody = new XRectangle();
        hours = new XRectangle[8];
        pager = new XRectangle();
    }
    
    public XRectangle getDayHeader(int day) {
        dayHeader.setY(day + firstRow);
        return dayHeader;
    }

    public XRectangle getToAdmin(int day) {
        toAdmin.setY(day + firstRow);
        return toAdmin;
    }
    
    public XRectangle getHour(int h) {
        hours[h].reset(hoursRefs[h]);
        return hours[h];
    }
    
    public XRectangle getHour(int day, int h) {
        hours[h].setY(day + firstRow);
        return hours[h];
    }
    
    public int getHoursCount() {
        return hours.length;
    }
    
    public XRectangle getHalfDayBody(int day, DAY_HALF half) {
        halfDayBody.reset(halfDaysRefs[( half == DAY_HALF.MORNING ) ? 0 : 1]);
        halfDayBody.setY(halfDayBody.getY() + day);
        return halfDayBody;
    }
    
    public XRectangle getHalfDayHeader(DAY_HALF half) {
        halfDayHeader.reset(halfDaysRefs[( half == DAY_HALF.MORNING ) ? 0 : 1]);
        return halfDayHeader;
    }

    public XRectangle getBureau() {
        return bureau;
    }

    public XRectangle getWeek() {
        return week;
    }

    public XRectangle getToAdminHeader() {
        return toAdminHeader;
    }

    public void setDaysCount(int daysCount) {
        dayHeader.setHeight(workAreaHeight / daysCount);
    }

    public int getFirstRow() {
        return firstRow;
    }

    public XRectangle getMessage() {
        return message;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public boolean isShiftRows() {
        return shiftRows;
    }
    
}
