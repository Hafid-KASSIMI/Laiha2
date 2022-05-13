
package lp.models.templates.workbooks.metrics;

import lp.models.templates.StudentsListsMetrics;
import java.util.function.Function;
import net.mdrassty.object.Student;
import net.mdrassty.util.excel.XRectangle;
// x = col ; y = row
public abstract class ListMetrics extends StudentsListsMetrics<XRectangle, XStudentRow> {
    public static final int SINGLE_PAGE_ROWS_MIN_COUNT = 30;
    protected int doublePageRowsCount = 35;
//    protected final int DEPARTURE_ASCII_CODE = Character.getNumericValue('A');
    //protected final float DIVIDER_THICKNESS = 0.961f;
    //protected final Color DIVIDER_COLOR = new Color(0x7F, 0x7F, 0x7F);
    //protected float maxRowsCount;
    protected boolean pageEmpty;
    protected int firstRow;
    private int emptyRows, uniLevEmptyRows;
    private float width, height, workAreaRealHeight;
    protected Function<Student, String> genderFunc;
    
    public ListMetrics() {
        super();
        studentRow = new XStudentRow();
        emptyRows = uniLevEmptyRows = 0;
    }
    
    public void prepareRows(int size) {
        float h;
        int tmpSize;
        pageEmpty = size < 1;
        size += emptyRows + uniLevEmptyRows;
        tmpSize = size;
        if ( size < SINGLE_PAGE_ROWS_MIN_COUNT )
            size = SINGLE_PAGE_ROWS_MIN_COUNT;
        h = height / size;
        workAreaRealHeight = h * tmpSize;
        studentRow.setRowHeight(h);
    }
    
    public boolean emptySpaceExists() {
        return ( studentRow.getRowIndex() - firstRow +  1 ) * studentRow.getRowHeight() < height;
    }
    
    public XStudentRow getStudentRow(int index) {
        studentRow.setRowIndex( firstRow + index );
        return studentRow;
    }
    
    public XStudentRow getNextStudentRow() {
        studentRow.setRowIndex(studentRow.getRowIndex() + 1 );
        return studentRow;
    }
    
    public XStudentRow getAnyStudentRow() {
        return studentRow;
    }

    public float getWidth() {
        return width;
    }

    public float getFirstRow() {
        return firstRow;
    }

    public final void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public float getHeight() {
        return height;
    }

    public final void setHeight(float height) {
        this.height = height;
    }

    public int getDoublePageRowsCount() {
        return doublePageRowsCount;
    }

    public void setDoublePageRowsCount(int doublePageRowsCount) {
        this.doublePageRowsCount = doublePageRowsCount;
    }

    public int getEmptyRows() {
        return emptyRows;
    }

    public void setEmptyRows(int emptyRows) {
        this.emptyRows = emptyRows;
    }

    public void setUniLevEmptyRows(int uniLevEmptyRows) {
        this.uniLevEmptyRows = uniLevEmptyRows;
    }

    public int getUniLevEmptyRows() {
        return uniLevEmptyRows;
    }

    public boolean isPageEmpty() {
        return pageEmpty;
    }

    public Function<Student, String> getGenderFunc() {
        return genderFunc;
    }

}
