

package lp.models.templates;

public abstract class StudentsListsMetrics<T, S extends StudentMetrics> {
    
    protected T schoolInfos, title, group, year, pager, lastColumnLabel, kingdom;
    protected S studentRow;
    protected String template;
    
    public T getSchoolInfos() {
        return schoolInfos;
    }

    public void setSchoolInfos(T schoolInfos) {
        this.schoolInfos = schoolInfos;
    }

    public T getTitle() {
        return title;
    }

    public void setTitle(T title) {
        this.title = title;
    }

    public T getGroup() {
        return group;
    }

    public void setGroup(T group) {
        this.group = group;
    }

    public T getYear() {
        return year;
    }

    public void setYear(T year) {
        this.year = year;
    }

    public T getPager() {
        return pager;
    }

    public void setPager(T pager) {
        this.pager = pager;
    }

    public String getTemplate() {
        return template;
    }

    public T getLastColumnLabel() {
        return lastColumnLabel;
    }

    public void setLastColumnLabel(T lastColumnLabel) {
        this.lastColumnLabel = lastColumnLabel;
    }

    public T getKingdom() {
        return kingdom;
    }

    public void setKingdom(T kingdom) {
        this.kingdom = kingdom;
    }
    
    
}
