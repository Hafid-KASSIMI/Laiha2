
package lp.models.templates.pdffiles.metrics;

import lp.models.templates.StudentsListsMetrics;
import java.awt.Color;
import java.util.function.Function;
import net.mdrassty.object.Student;
import net.mdrassty.util.pdf.PDFRectangle;

public abstract class ListMetrics extends StudentsListsMetrics<PDFRectangle, StudentPRectangle> {
    public static final int SINGLE_PAGE_ROWS_MIN_COUNT = 30;
    protected int doublePageRowsCount = 35;
    protected float DIVIDER_THICKNESS = 0.6f;
    protected final Color DIVIDER_COLOR = new Color(0x7F, 0x7F, 0x7F);
    protected PDFRectangle hDivider, vDivider;
    protected float[] vDividersXs;
    protected boolean pageEmpty;
    protected int curVDividerIndex;
    private int emptyRows, uniLevEmptyRows;
    protected float width, x, firstRowY, height, workAreaRealHeight;
    protected Function<Student, String> genderFunc;

    public ListMetrics() {
        super();
        schoolInfos =  new PDFRectangle();
        title =  new PDFRectangle();
        group =  new PDFRectangle();
        year = new PDFRectangle();
        studentRow = new StudentPRectangle();
        studentRow.setFullRow(new PDFRectangle());
        hDivider =  new PDFRectangle(0f, 0f, 0f, DIVIDER_THICKNESS);
//        hDivider.getFormat().setForeColor(DIVIDER_COLOR);
        hDivider.getFormat().setBackColor(DIVIDER_COLOR);
        vDivider =  new PDFRectangle(0f, 0f, DIVIDER_THICKNESS, 0f);
//        vDivider.getFormat().setForeColor(DIVIDER_COLOR);
        vDivider.getFormat().setBackColor(DIVIDER_COLOR);
        emptyRows = uniLevEmptyRows = 0;
    }

    public void prepareRows(int size) {
        int tmpSize;
        pageEmpty = size < 1;
        size += emptyRows + uniLevEmptyRows;
        tmpSize = size;
        if ( size < SINGLE_PAGE_ROWS_MIN_COUNT )
            size = SINGLE_PAGE_ROWS_MIN_COUNT;
        studentRow.setRowHeight( ( height - DIVIDER_THICKNESS * ( size - 1 ) ) / size );
        workAreaRealHeight = ( studentRow.getRowHeight() + DIVIDER_THICKNESS ) * tmpSize;
        curVDividerIndex = 0;
    }

    public StudentPRectangle getStudentRow(int index) {
        studentRow.setRowIndex(index);
        studentRow.setRowY(firstRowY + ( studentRow.getRowHeight() + DIVIDER_THICKNESS ) * index);
        return studentRow;
    }

    public StudentPRectangle getAnyStudentRow() {
        return studentRow;
    }

    public boolean hasVDividers() {
        return curVDividerIndex < vDividersXs.length;
    }

    public PDFRectangle nextVDivider() {
        vDivider.setX(vDividersXs[curVDividerIndex++]);
        return vDivider;
    }

    public PDFRectangle getHDivider(int index) {
        hDivider.setY(firstRowY + studentRow.getRowHeight() * ( index + 1 ) + DIVIDER_THICKNESS * index);
        return hDivider;
    }

    public PDFRectangle getHDivider() {
        return hDivider;
    }

    public boolean emptySpaceExists() {
        //  + studentRow.getRowHeight() / 3 to be very sure
        return ( ( hDivider.getInkscapeY() + studentRow.getRowHeight() / 3 ) < 
                ( firstRowY + height ) );
    }

    public PDFRectangle nextHDivider() {
        hDivider.setY(hDivider.getInkscapeY() + studentRow.getRowHeight() + DIVIDER_THICKNESS);
        return hDivider;
    }

    public float getWidth() {
        return width;
    }

    public final void setWidth(float width) {
        this.width = width;
        hDivider.setWidth(width);
        studentRow.getFullRow().setWidth(width);
    }

    public float getX() {
        return x;
    }

    public final void setX(float x) {
        this.x = x;
        hDivider.setX(x);
        studentRow.getFullRow().setX(x);
    }

    public float getFirstRowY() {
        return firstRowY;
    }

    public final void setFirstRowY(float firstRowY) {
        this.firstRowY = firstRowY;
        vDivider.setY(firstRowY);
    }

    public float getHeight() {
        return height;
    }

    public final void setHeight(float height) {
        this.height = height;
        vDivider.setHeight(height);
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
