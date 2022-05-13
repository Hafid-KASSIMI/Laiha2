

package lp.models.templates.pdffiles;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.function.BiConsumer;
import lp.Settings;
import lp.models.templates.pdffiles.metrics.ListMetrics;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import net.mdrassty.util.Misc;
import org.apache.pdfbox.pdmodel.PDPage;

public abstract class ListPDF extends BasePDF {

    protected ListMetrics pageMetrics;
    protected boolean unifyLevelGroupsSize, strip;
    protected final DecimalFormat INTEGER = new DecimalFormat();
    protected BiConsumer<Group, Integer> unifyLevSize;
    protected int stripIndex;
    private Color stripColor = Color.white, stripTextColor = Color.BLACK;

    public ListPDF(){
        super();
        INTEGER.applyPattern("00");
    }
    
    protected void add1PageGroup(Group g, PDPage page, int start, int stuCount) {
        if ( unifyLevelGroupsSize )
            unifyLevSize.accept(g, stuCount);
        pageMetrics.prepareRows(stuCount - start);
        for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
            placeStudent(j, page, g.getStudent(i));
            pdf.fill(pageMetrics.getHDivider(j), page);
        }
        while ( pageMetrics.emptySpaceExists() ) {
            pdf.fill(pageMetrics.nextHDivider(), page);
        }
        if ( pageMetrics.isPageEmpty() )
            pdf.fill(pageMetrics.getHDivider(), page);
        while ( pageMetrics.hasVDividers() ) {
            pdf.fill(pageMetrics.nextVDivider(), page);
        }
    }
    
    protected void placeStudent(int index, PDPage page, Student stu) {
        if ( strip ) {
            if ( ( index + 1 ) % stripIndex == 0 ) {
                pdf.fill(pageMetrics.getStudentRow(index).getFullRow(), page, stripColor);
                pageMetrics.getStudentRow(index).getChilds().stream().filter(rect -> rect.getFormat() != null)
                .forEach(rectangle -> {
                    rectangle.getFormat().setFontColor(stripTextColor);
                });
            }
            else {
                pageMetrics.getStudentRow(index).getChilds().stream().filter(rect -> rect.getFormat() != null)
                .forEach(rectangle -> {
                    rectangle.getFormat().setFontColor(Color.BLACK);
                });
            }
        }
    }
    
    @Override
    protected void add2PagesGroup(Group g) {
        int stuCount, ini_unify_lev_size, ini_empty_rows, ini_dbl_page_size, max;
        boolean ini_unify_lev;
        stuCount = g.getStudentsCount();
        ini_dbl_page_size = pageMetrics.getDoublePageRowsCount();
        PDPage page;
        if ( stuCount > ini_dbl_page_size ) {
            ini_unify_lev_size = pageMetrics.getUniLevEmptyRows();
            ini_empty_rows = pageMetrics.getEmptyRows();
            max = stuCount + ini_unify_lev_size + ini_empty_rows;
            if ( max > ini_dbl_page_size * 2 ) {
                max = max / 2 + max % 2;
            }
            else
                max = ini_dbl_page_size;
            ini_unify_lev = unifyLevelGroupsSize;
            unifyLevelGroupsSize = false;
            pageMetrics.setUniLevEmptyRows(0);
            pageMetrics.setEmptyRows(0);
            page = pdf.clonePage(0);
            pdf.placeNRotateString(pageMetrics.getPager(), page, "1 / 2");
            add1PageGroup(g, page, 0, max);
            unifyLevelGroupsSize = ini_unify_lev;
            pageMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            pageMetrics.setEmptyRows(ini_empty_rows);
            page = pdf.clonePage(0);
            pdf.placeNRotateString(pageMetrics.getPager(), page, "2 / 2");
            add1PageGroup(g, page, max, stuCount);
        }
        else {
            page = pdf.clonePage(0);
            pdf.placeNRotateString(pageMetrics.getPager(), page, "1 / 1");
            add1PageGroup(g, page, 0, stuCount);
        }
    }
    
    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( pdf.getDoc() == null || g == null )   return;
        int n;        
        n = g.getStudentsCount();
        if ( force2Pages )
            add2PagesGroup(g);
        else
            add1PageGroup( g, pdf.clonePage(0), 0, n );
    }
    
    protected final void initialize() {
        unifyLevelGroupsSize = Settings.PREF_TEMPORARY.get("LSTTPL_UNIFY_ROWS_LEVEL").equals("Y");
        strip = Settings.PREF_TEMPORARY.get("LSTTPL_STRIPED").equals("Y");
        try {
            pageMetrics.setEmptyRows(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_ADDITIONAL_EMPTY_ROWS")));
        } catch ( NumberFormatException nfe ) { }
        try {
            pageMetrics.setDoublePageRowsCount(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_DOUBLE_PAGE_ROWS_COUNT")));
        } catch ( NumberFormatException nfe ) { }
        if ( unifyLevelGroupsSize )
            unifyLevSize = (g, count) -> { pageMetrics.setUniLevEmptyRows(g.getLevel().getGroupsMaxSize() - count); };
        else
            unifyLevSize = (g, count) -> { };
        if ( strip ) {
            try {
                javafx.scene.paint.Color fxCol = javafx.scene.paint.Color.valueOf(Settings.PREF_BUNDLE.get("LSTTPL_STRIP_COLOR"));
                stripColor = new Color((int) (fxCol.getRed() * 255), (int) (fxCol.getGreen() * 255), (int) (fxCol.getBlue() * 255));
                stripTextColor = Misc.isDark(stripColor.getRed(), stripColor.getGreen(), stripColor.getBlue()) ? Color.WHITE : Color.BLACK;
            } catch ( IllegalArgumentException | NullPointerException nfe ) { }
            try {
                stripIndex = Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_STRIP_INDEX"));
            } catch ( NumberFormatException nfe ) {
                stripIndex = 5;
            }
        }
    }
}
