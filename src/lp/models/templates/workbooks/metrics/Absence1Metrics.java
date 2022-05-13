

package lp.models.templates.workbooks.metrics;

import java.awt.Color;
import net.mdrassty.util.Format;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class Absence1Metrics extends AbsenceMetrics {
    
    public Absence1Metrics() {
        super();
        
        template = "ABSENCE_1";
        message =  new XRectangle();
        schoolInfos.reset("A1");
        title.reset("G1");
        week.reset("G3");
        year.reset("O1");
        bureau.reset("O2");
        group.reset("O3");
        message.reset("C5");
        pager.reset("A7");
        toAdminHeader.reset("B7", new Format());
        toAdmin.reset("B7", new Format());
        toAdmin.getFormat().setBorders(0, 1, 1, 0);
        toAdmin.setColSpan(2);
        dayHeader.reset("A9", new Format(12, XAvailableFonts.TIMES, true));
        dayHeader.getFormat().setRotation(-90f);
        dayHeader.getFormat().setForeColor(new Color(0xf0, 0xf0, 0xf0));
        dayHeader.getFormat().setBorders(0, 1, 1, 1);
        
        hoursRefs = new String[] { "D8", "F8", "H8", "J8", "L8", "N8", "P8", "R8" };
        for ( int i = 0; i < hoursRefs.length; i++ ) {
            hours[i] = new XRectangle(hoursRefs[i], 0, 0, new Format());
            hours[i].getFormat().setBorders(0, 1, 1, 1);
            hours[i].setColSpan(2);
        }
        hours[0].getFormat().setBorders(0, 1, 1, 1.5f);
        hours[4].getFormat().setBorders(0, 1, 1, 3);
        
        halfDayBody.setColSpan(8);
        
        halfDaysRefs = new String[] { "D7", "L7" };
        
        
        firstRow = 8;
        workAreaHeight = 427f;
    }
    
}
