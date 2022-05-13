

package lp.models.templates.workbooks.metrics;

import lp.models.templates.StudentMetrics;
import net.mdrassty.util.excel.XRectangle;

public class XStudentRow extends StudentMetrics<XRectangle> {
    
    private float height;
    
    public XStudentRow() {
    }

    public void setRowHeight(float h) {
        height = h;
    }

    public float getRowHeight() {
        return height;
    }
}
