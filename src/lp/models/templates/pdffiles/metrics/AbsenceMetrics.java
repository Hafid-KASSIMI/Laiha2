
package lp.models.templates.pdffiles.metrics;

import lp.models.templates.Metrics;
import lp.util.Calendar.DAY_HALF;
import net.mdrassty.util.A4_PAPER;
import net.mdrassty.util.Format;
import net.mdrassty.util.pdf.PDFRectangle;

public abstract class AbsenceMetrics extends Metrics<PDFRectangle> {

    protected PDFRectangle bureau, week, toAdmin, divider, message;
    protected PDFRectangle dayHeader, halfDayHeader, halfDayBody, hour;
    protected float[] halfDaysXs, hoursXs;
    protected float workAreaWidth, workAreaHeight, firstY;
    protected final float H_DIVIDER_THICKNESS = 0.6f;
    protected int daysCount;
    
    public AbsenceMetrics() {
        super();
        schoolInfos =  new PDFRectangle(A4_PAPER.getLandscapeHeight());
        title =  new PDFRectangle(A4_PAPER.getLandscapeHeight());
        group =  new PDFRectangle(A4_PAPER.getLandscapeHeight());
        year = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        week = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        toAdmin = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        bureau = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        dayHeader = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        halfDayHeader = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        halfDayBody = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        hour = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        divider = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        pager = new PDFRectangle(A4_PAPER.getLandscapeHeight());
        divider.setFormat(new Format());
        divider.getFormat().setBackColor(divider.getFormat().getBorderColor());
        divider.setHeight(H_DIVIDER_THICKNESS);
    }
    
    public PDFRectangle getDayHeader(int day) {
        dayHeader.setY(firstY + day * (halfDayBody.getHeight() + H_DIVIDER_THICKNESS) - dayHeader.getHeight());
        return dayHeader;
    }
    
    public PDFRectangle getHorDivider(int index) {
        divider.setY(firstY + (index - 1) * (halfDayBody.getHeight() + H_DIVIDER_THICKNESS) + halfDayBody.getHeight());
        return divider;
    }
    
    public PDFRectangle getHour(int h) {
        hour.setX(hoursXs[h]);
        return hour;
    }
    
    public PDFRectangle getHalfDayBody(int day, DAY_HALF half) {
        if ( half == DAY_HALF.MORNING ) {
            halfDayBody.setX(halfDaysXs[0]);
        }
        else {
            halfDayBody.setX(halfDaysXs[1]);
        }
        halfDayBody.setY(firstY + day * (halfDayBody.getHeight() + H_DIVIDER_THICKNESS));
        return halfDayBody;
    }
    
    public PDFRectangle getHalfDayHeader(DAY_HALF half) {
        if ( half == DAY_HALF.MORNING ) {
            halfDayHeader.setX(halfDaysXs[0]);
        }
        else {
            halfDayHeader.setX(halfDaysXs[1]);
        }
        return halfDayHeader;
    }

    public PDFRectangle getBureau() {
        return bureau;
    }

    public PDFRectangle getWeek() {
        return week;
    }

    public PDFRectangle getToAdmin() {
        return toAdmin;
    }

    public void setDaysCount(int daysCount) {
        halfDayBody.setHeight(workAreaHeight / daysCount - H_DIVIDER_THICKNESS);
        dayHeader.setWidth(halfDayBody.getHeight());
    }

    public PDFRectangle getMessage() {
        return message;
    }

    public void setMessage(PDFRectangle message) {
        this.message = message;
    }

}
