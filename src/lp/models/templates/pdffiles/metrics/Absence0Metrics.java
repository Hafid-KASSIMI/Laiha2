

package lp.models.templates.pdffiles.metrics;

import net.mdrassty.util.A4_PAPER;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Absence0Metrics extends AbsenceMetrics {
    
    public Absence0Metrics() {
        template = "ABSENCE_0";
        message =  new PDFRectangle(A4_PAPER.getLandscapeHeight());
        message.setFormat(12, PAvailableFonts.TIMES);
        message.getFormat().setWrapped(true);
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(16, PAvailableFonts.TIMES, true);
        year.setFormat(schoolInfos.getFormat());
        week.setFormat(schoolInfos.getFormat());
        bureau.setFormat(schoolInfos.getFormat());
        group.setFormat(16, PAvailableFonts.TIMES, true);
        dayHeader.setFormat(12, PAvailableFonts.TIMES, true);
        dayHeader.getFormat().setRotation(-90f);
        halfDayHeader.setFormat(year.getFormat());
        toAdmin.setFormat(year.getFormat());
        pager.setFormat(year.getFormat());
        pager.getFormat().setRotation(-90f);
        
        workAreaWidth = 798.120f;
        workAreaHeight = 409f;
        firstY = 121.021f;
        
        message.reset(22.589f, 533.8f, 757.112f, 39.8f);
        schoolInfos.reset(595.785f, 20.279f, 225.255f, 55.744f);
        title.reset(246.105f, 20.279f, 349.680f, 31.623f);
        week.reset(title.getX(), 51.903f, title.getWidth(), 24.120f);
        year.reset(23.519f, 20.279f, 222.586f, 18f);
        bureau.reset(year.getX(), 38.371f, year.getWidth(), year.getHeight());
        group.reset(year.getX(), 56.462f, year.getWidth(), 19.561f);
        toAdmin.reset(722.099f, 78.241f, 70.260f, 41.580f);
        hour.reset(0f, 99.359f, 86.483f, 20.462f);
        pager.reset(793.559f, 51.241f, 41.580f, 27f);
        hoursXs = new float[] { 634.416f, 547.334f, 460.251f, 373.169f, 284.887f, 197.804f, 110.722f, 23.639f };
        dayHeader.reset(793.559f, 0f, 0f, 27f);
        halfDayHeader.reset(0f, 78.241f, 347.730f, 20.518f);
        halfDaysXs = new float[] { 373.169f, 23.639f };
        halfDayBody.reset(0f, 0f, halfDayHeader.getWidth(), 0f);
        divider.setXW(23.039f, workAreaWidth);
        
    }
}
