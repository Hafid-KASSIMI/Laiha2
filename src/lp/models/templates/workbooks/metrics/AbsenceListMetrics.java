

package lp.models.templates.workbooks.metrics;

import net.mdrassty.util.excel.XRectangle;

public class AbsenceListMetrics extends ListMetrics {
    
    protected XRectangle bureau, week, signature, toAdmin;
    protected XRectangle[] dayHeader, halfDayHeader, halfDayBody;
    protected String[] hours;
    
    public AbsenceListMetrics() {
        super();
    }

    public XRectangle getBureau() {
        return bureau;
    }

    public XRectangle getWeek() {
        return week;
    }

    public XRectangle getSignature() {
        return signature;
    }

    public XRectangle getToAdmin() {
        return toAdmin;
    }

    public XRectangle getDayHeader(int day) {
        return dayHeader[day];
    }

    public XRectangle getHalfDayHeader(int day) {
        return halfDayHeader[day];
    }

    public XRectangle getHalfDayBody(int day) {
        return halfDayBody[day];
    }

    public XRectangle[] getDayHeader() {
        return dayHeader;
    }

    public XRectangle[] getHalfDayHeader() {
        return halfDayHeader;
    }

    public XRectangle[] getHalfDayBody() {
        return halfDayBody;
    }

    protected String[] getHours() {
        return hours;
    }
    
}
