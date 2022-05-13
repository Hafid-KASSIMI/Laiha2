

package lp.models.templates.workbooks.metrics;

import net.mdrassty.util.excel.XRectangle;

public class XAbsenceStudentRow extends XStudentRow {
    
    protected XRectangle[] hours;
    
    public XAbsenceStudentRow() {
        super();
    }

    public XRectangle[] getHours() {
        return hours;
    }

    public XRectangle getHour(int index) {
        return hours[index];
    }

    public void setHours(XRectangle[] hours) {
        this.hours = hours;
    }
    
}
