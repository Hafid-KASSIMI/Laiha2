

package lp.models.templates.pdffiles;

import java.awt.Color;
import lp.Settings;
import lp.models.templates.pdffiles.metrics.AbsenceMetrics;
import lp.util.Calendar.DAY_HALF;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WEEK;
import net.mdrassty.object.Group;
import org.apache.pdfbox.pdmodel.PDPage;

public abstract class AbsencePDF extends BasePDF {

    protected AbsenceMetrics pageMetrics;
    private final float GRAYING_GAP = 10f;

    public AbsencePDF(){
        super();
    }
    
    protected abstract void add1PageGroup(Group g, PDPage page, WEEK week);
    
    @Override
    protected void add2PagesGroup(Group g) {
        PDPage page = pdf.clonePage(0);
        pdf.placeNRotateString(pageMetrics.getPager(), page, "1 / 2");
        add1PageGroup(g, page, WEEK.FIRST_HALF);
        page = pdf.clonePage(0);
        pdf.placeNRotateString(pageMetrics.getPager(), page, "2 / 2");
        add1PageGroup(g, page, WEEK.SECOND_HALF);
    }
    
    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( pdf.getDoc() == null || g == null )   return;
        int n = g.getStudentsCount();
        if ( force2Pages ) {
            pageMetrics.setDaysCount(3);
            add2PagesGroup(g);
        }
        else {
            pageMetrics.setDaysCount(6);
            add1PageGroup( g, pdf.clonePage(0), WEEK.HOLE_WEEK );
        }
    }
    
    protected void placeTableHeader(PDPage page, WEEK w) {
        DateRange dr = getDateRange(Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_FIRST_DAY_OF_WEEK"), 
                Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SELECTED_WEEK"), w);
        AbsenceMetrics metrics = (AbsenceMetrics) pageMetrics;
        pdf.placeString(metrics.getWeek(), page, (Settings.PREF_TEMPORARY.get("ABSTPL_FROM") + " " + 
                dr.getBeginning() + " " + Settings.PREF_TEMPORARY.get("ABSTPL_TO") + " " + dr.getEnd()));
        pdf.placeString(metrics.getHalfDayHeader(DAY_HALF.MORNING), page, Settings.PREF_TEMPORARY.get("ABSTPL_MORNING"));
        pdf.placeString(metrics.getHalfDayHeader(DAY_HALF.AFTERNOON), page, Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON"));
        for ( int h = 1; h < 5; h++ ) {
            pdf.placeString(metrics.getHour(h - 1), page, Settings.PREF_TEMPORARY.get("ABSTPL_MORNING_" + h));
            pdf.placeString(metrics.getHour(h + 3), page, Settings.PREF_TEMPORARY.get("ABSTPL_AFTERNOON_" + h));
        }
        for ( int j = 0, i = dr.getFirstday(), day = dr.getToday(), n = dr.getLastDay(); i < n; i++, j++, day = ( day + 1 ) % 7 ) {
            pdf.placeNRotateString(metrics.getDayHeader(j), page, Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + day));
            pdf.fill(metrics.getHorDivider(j + 1), page);
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_MORNING_GRAYED").equals("Y") ) {
                pdf.gray(metrics.getHalfDayBody(j, DAY_HALF.MORNING), page, false, GRAYING_GAP);
            }
            if ( Settings.PREF_TEMPORARY.get("ABSTPL_DAY_" + i + "_AFTERNOON_GRAYED").equals("Y") ) {
                pdf.gray(metrics.getHalfDayBody(j, DAY_HALF.AFTERNOON), page, false, GRAYING_GAP);
            }
        }
    }
}
