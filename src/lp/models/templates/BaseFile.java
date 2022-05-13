

package lp.models.templates;

import java.util.Calendar;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WEEK;
import lp.util.Calendar.WeekInfos;
import lp.util.FILE_TYPE;
import net.mdrassty.object.Group;
import net.mdrassty.object.Level;

public abstract class BaseFile<T> {
    
    protected String extension;
    protected FILE_TYPE type;
    protected T coreFile;
    
    public abstract void reset();
    public abstract void addGroup(Group g, boolean force2Pages);
    protected abstract void add2PagesGroup(Group g);
    public abstract boolean save(String path);

    
    public void addLevel(Level l, boolean force2Pages) {
        if ( coreFile == null || l == null )   return;
        for ( int i = 0, n = l.getGroupsCount(); i < n; i++ ) {
            addGroup(l.getGroup(i), force2Pages);
        }
    }

    public String getExtension() {
        return extension;
    }

    public FILE_TYPE getType() {
        return type;
    }
    
    protected DateRange getDateRange(String TPL_FIRST_DAY_OF_WEEK, String TPL_SELECTED_WEEK, WEEK w) {
        WeekInfos wi;
        Integer sw;
        DateRange dr;
        try {
            wi = new WeekInfos(Integer.parseInt(TPL_FIRST_DAY_OF_WEEK));
        } catch(NumberFormatException nfe) {
            wi = new WeekInfos(Calendar.MONDAY);
        }
        try {
            sw = Integer.parseInt(TPL_SELECTED_WEEK);
        } catch(NumberFormatException nfe) {
            sw = 1;
        }
        switch ( w ) {
            case FIRST_HALF:
                dr = wi.getWeekHalf(WEEK.FIRST_HALF, sw);
                dr.setFirstday(1);
                dr.setLastDay(4);
                dr.setToday(wi.getFirstDay() - 1);
                break;
            case SECOND_HALF:
                dr = wi.getWeekHalf(WEEK.SECOND_HALF, sw);
                dr.setFirstday(4);
                dr.setLastDay(7);
                dr.setToday(( wi.getFirstDay() + 2 ) % 7);
                break;
            default:
                dr = wi.getWeek(sw);
                dr.setFirstday(1);
                dr.setLastDay(7);
                dr.setToday(wi.getFirstDay() - 1);
                break;
        }
        return dr;
    }
}
