

package lp.models.templates.workbooks.metrics;

import java.awt.Color;
import net.mdrassty.util.Format;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class Absence0Metrics extends AbsenceMetrics {
    
    public Absence0Metrics() {
        super();
        
        template = "ABSENCE_0";
        shiftRows = true;
        message =  new XRectangle();
        schoolInfos.reset("A1");
        title.reset("G1");
        week.reset("G3");
        year.reset("O1");
        bureau.reset("O2");
        group.reset("O3");
        message.reset("C5");
        message.getFormat().setWrapped(true);
        pager.reset("A5");
        toAdminHeader.reset("B5", new Format());
        toAdmin.reset("B5", new Format());
        toAdmin.getFormat().setBorders(0, 1, 1, 0);
        toAdmin.setColSpan(2);
        dayHeader.reset("A7", new Format(12, XAvailableFonts.TIMES, true));
        dayHeader.getFormat().setRotation(-90f);
        dayHeader.getFormat().setForeColor(new Color(0xf0, 0xf0, 0xf0));
        dayHeader.getFormat().setBorders(0, 1, 1, 1);
        
        hoursRefs = new String[] { "D6", "F6", "H6", "J6", "L6", "N6", "P6", "R6" };
        for ( int i = 0; i < hoursRefs.length; i++ ) {
            hours[i] = new XRectangle(hoursRefs[i], 0, 0, new Format());
            hours[i].getFormat().setBorders(0, 1, 1, 1);
            hours[i].setColSpan(2);
        }
        hours[0].getFormat().setBorders(0, 1, 1, 1.5f);
        hours[4].getFormat().setBorders(0, 1, 1, 3);
        
        halfDayBody.setColSpan(8);
        
        halfDaysRefs = new String[] { "D5", "L5" };
        
        
        firstRow = 6;
        workAreaHeight = 427f;
    }
    
}
