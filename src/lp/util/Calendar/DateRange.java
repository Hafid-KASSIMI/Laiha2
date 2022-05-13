

package lp.util.Calendar;

public class DateRange {
    private String beginning, end;
    private int firstday, lastDay, today;

    public DateRange(String beginning, String end) {
        this.beginning = beginning;
        this.end = end;
    }

    public DateRange() {
        
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getFirstday() {
        return firstday;
    }

    public void setFirstday(int firstday) {
        this.firstday = firstday;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public int getToday() {
        return today;
    }

    public void setToday(int today) {
        this.today = today;
    }
    
}
