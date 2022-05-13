

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class Absence5Metrics extends AbsenceListMetrics {
        
    public Absence5Metrics() {
        super();
        template = "ABSENCE_5";
        
        pager = new XRectangle("A1", 0, 0);
        schoolInfos = new XRectangle("A1", 0, 0);
        title = new XRectangle("K1", 0, 0);
        week = new XRectangle("K3", 0, 0);
        year = new XRectangle("AA1", 0, 0);
        bureau = new XRectangle("AA2", 0, 0);
        group = new XRectangle("AA3", 0, 0);
        signature = new XRectangle("B3", 0, 0);
        toAdmin = new XRectangle("B3", 0, 0);
        
        firstRow = 6;
        
        studentRow = new XAbsenceStudentRow();
        studentRow.setNumero(new XRectangle("A7", 0, 0, new Format(11, XAvailableFonts.TIMES)));
        studentRow.getNumero().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getNumero().getFormat().setDataType(DATA_TYPE.INTEGER);
        studentRow.setSecondName(new XRectangle("B7", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getSecondName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getSecondName().setColSpan(10);
        ((XAbsenceStudentRow) studentRow).hours = new XRectangle[24];
        dayHeader = new XRectangle[3];
        halfDayHeader = new XRectangle[6];
        halfDayBody = new XRectangle[6];
        hours = new String[] { "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "AB", 
                        "AC", "AD", "AE", "AF", "AG", "AH", "AI" };
        for ( int i = 0; i < dayHeader.length; i++ ) {
            dayHeader[i] = new XRectangle(hours[i * 8] + "5", 0, 0);
        }
        for ( int i = 0; i < halfDayHeader.length; i++ ) {
            halfDayHeader[i] = new XRectangle(hours[i * 4] + "6", 0, 0);
            halfDayBody[i] = new XRectangle(hours[i * 4] + "7", 0, 0, new Format());
            halfDayBody[i].getFormat().setForeColor(halfDayBody[i].getFormat().getDefaultBorderColor());
        }
        for ( int i = 0, n = ((XAbsenceStudentRow) studentRow).hours.length; i < n; i++ ) {
            ((XAbsenceStudentRow) studentRow).hours[i] = new XRectangle(hours[i] + "7", 0, 0, new Format());
            ((XAbsenceStudentRow) studentRow).hours[i].getFormat().setBorders(0, 1, 1, 1);
        }
        for ( int i = 3; i < 20; i += 8 ) {
            ((XAbsenceStudentRow) studentRow).hours[i].getFormat().setBorder(1, 1.5f);
        }
        ((XAbsenceStudentRow) studentRow).hours[7].getFormat().setBorder(1, 3);
        ((XAbsenceStudentRow) studentRow).hours[15].getFormat().setBorder(1, 3);
        
        setHeight(663f);
    }
    
}
