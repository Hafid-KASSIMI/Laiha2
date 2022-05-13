

package lp.models.templates.pdffiles.metrics;

import java.awt.Color;
import net.mdrassty.util.A4_PAPER;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Absence2Metrics extends AbsenceMetrics {
    
    public Absence2Metrics() {
        super();
        
        template = "ABSENCE_2";
        message =  new PDFRectangle(A4_PAPER.getLandscapeHeight());
        
        message.setFormat(12, PAvailableFonts.TIMES);
        message.getFormat().setRotation(90f);
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(16, PAvailableFonts.TIMES, true);
        year.setFormat(12, PAvailableFonts.TIMES);
        week.setFormat(year.getFormat());
        bureau.setFormat(year.getFormat());
        group.setFormat(16, PAvailableFonts.TIMES, true);
        dayHeader.setFormat(12, PAvailableFonts.TIMES, true);
        dayHeader.getFormat().setRotation(-90f);
        halfDayHeader.setFormat(year.getFormat());
        toAdmin.setFormat(year.getFormat());
        pager.setFormat(year.getFormat());
        pager.getFormat().setRotation(-90f);
        
        workAreaWidth = 713.124f;
        workAreaHeight = 453.479f;
        firstY = 120.721f;
        
        message.reset(99.102f, 498.167f, 452.879f, 75.433f);
        schoolInfos.reset(595.785f, 20.279f, 225.255f, 55.744f);
        title.reset(246.105f, 20.279f, 349.680f, 31.623f);
        week.reset(title.getX(), 51.903f, title.getWidth(), 24.120f);
        year.reset(23.519f, 20.279f, 222.586f, 18f);
        bureau.reset(year.getX(), 38.371f, year.getWidth(), year.getHeight());
        group.reset(year.getX(), 56.462f, year.getWidth(), 19.561f);
        toAdmin.reset(722.099f, 78.241f, 70.260f, 41.580f);
        hour.reset(0f, 99.359f, 75.858f, 20.462f);
        pager.reset(793.559f, 51.241f, 41.580f, 27f);
        hoursXs = new float[] { 645.041f, 568.583f, 492.125f, 415.667f, 338.009f, 261.551f, 185.093f, 108.635f };
        dayHeader.reset(793.559f, 0f, 0f, 27f);
        halfDayHeader.reset(0f, 78.241f, 305.232f, 20.518f);
        halfDaysXs = new float[] { 415.667f, 108.635f };
        halfDayBody.reset(0f, 0f, halfDayHeader.getWidth(), 0f);
        divider.setXW(108.035f, workAreaWidth);
    }
}
