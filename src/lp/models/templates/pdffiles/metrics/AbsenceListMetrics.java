

package lp.models.templates.pdffiles.metrics;

import lp.util.Calendar.DAY_HALF;
import net.mdrassty.util.pdf.PDFRectangle;

public class AbsenceListMetrics extends ListMetrics {
    
    protected final float[] DIVIDER_THICKNESSES = { 0.6f, 1.2f, 1.8f };
    private final int HALF_DAY_INNER_V_DIDERS_COUNT = 3 + 1;
    protected VDivider[] vDividersXs;
    protected int[] daysXsIndices;
    protected PDFRectangle bureau, week, signature, toAdmin;
    protected PDFRectangle dayHeader, halfDayHeader, halfDayBody;
    protected final float HALF_DAY_HEADER_MARGIN = 2;
    protected float halfDayWidth;
    
    public AbsenceListMetrics() {
        super();
        signature = new PDFRectangle();
        toAdmin = new PDFRectangle();
        dayHeader = new PDFRectangle();
        halfDayHeader = new PDFRectangle();
        halfDayBody = new PDFRectangle();
    }
    
    @Override
    public PDFRectangle nextVDivider() {
        vDivider.setX(vDividersXs[curVDividerIndex].x);
        vDivider.setWidth(DIVIDER_THICKNESSES[vDividersXs[curVDividerIndex].thickness]);
        curVDividerIndex++;
        return vDivider;
    }

    @Override
    public boolean hasVDividers() {
        return curVDividerIndex < vDividersXs.length;
    }
    
    protected class VDivider {
        public int thickness;
        public float x;

        public VDivider(int thickness, float x) {
            this.thickness = thickness;
            this.x = x;
        }
        
    }

    public PDFRectangle getBureau() {
        return bureau;
    }

    public PDFRectangle getWeek() {
        return week;
    }

    public PDFRectangle getSignature() {
        return signature;
    }

    public PDFRectangle getToAdmin() {
        return toAdmin;
    }
    
    public PDFRectangle getDayHeader(int i) {
        dayHeader.setX(vDividersXs[daysXsIndices[i]].x + DIVIDER_THICKNESSES[vDividersXs[daysXsIndices[i]].thickness]);
        return dayHeader;
    }
    
    private float getHalfDayX(int day, DAY_HALF half) {
        if ( half == DAY_HALF.AFTERNOON )
            return vDividersXs[daysXsIndices[day]].x + DIVIDER_THICKNESSES[vDividersXs[daysXsIndices[day]].thickness];
        return vDividersXs[daysXsIndices[day]].x + 
                    DIVIDER_THICKNESSES[vDividersXs[daysXsIndices[day]].thickness] + halfDayWidth +
                    DIVIDER_THICKNESSES[vDividersXs[daysXsIndices[day] + HALF_DAY_INNER_V_DIDERS_COUNT].thickness];
    }
    
    public PDFRectangle getHalfDayHeader(int day, DAY_HALF half) {
        halfDayHeader.setX(getHalfDayX(day, half) + HALF_DAY_HEADER_MARGIN);
        return halfDayHeader;
    }
    
    public PDFRectangle getHalfDayBody(int day, DAY_HALF half) {
        halfDayBody.setX(getHalfDayX(day, half));
        return halfDayBody;
    }
    
}
