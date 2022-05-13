

package lp.models.templates.pdffiles.metrics;

import net.mdrassty.util.pdf.PDFRectangle;

public class MarksMetrics extends ListMetrics {
    
    protected PDFRectangle teacher, matter, semester, teacherLbl, matterLbl, semesterLbl, yearLbl;
    
    public MarksMetrics() {
        super();
        teacher = new PDFRectangle();
        semester = new PDFRectangle();
        matter = new PDFRectangle();
        teacherLbl = new PDFRectangle();
        semesterLbl = new PDFRectangle();
        matterLbl = new PDFRectangle();
    }

    public PDFRectangle getTeacher() {
        return teacher;
    }

    public PDFRectangle getMatter() {
        return matter;
    }

    public PDFRectangle getSemester() {
        return semester;
    }

    public PDFRectangle getTeacherLbl() {
        return teacherLbl;
    }

    public PDFRectangle getMatterLbl() {
        return matterLbl;
    }

    public PDFRectangle getSemesterLbl() {
        return semesterLbl;
    }

    public PDFRectangle getYearLbl() {
        return yearLbl;
    }
    
}
