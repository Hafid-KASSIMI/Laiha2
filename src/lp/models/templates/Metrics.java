

package lp.models.templates;

public abstract class Metrics<T> {
    
    protected T schoolInfos, title, group, year, pager, kingdom;
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

    public T getKingdom() {
        return kingdom;
    }

    public void setKingdom(T kingdom) {
        this.kingdom = kingdom;
    }
    
    
}
