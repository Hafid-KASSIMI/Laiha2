

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Marks2Metrics extends MarksMetrics {
    
    public Marks2Metrics() {
        super();
        
        template = "MARKS_2";
        pager = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setFirstName(new PDFRectangle());
        studentRow.setMark1(new PDFRectangle());
        studentRow.setMark2(new PDFRectangle());
        studentRow.setMark3(new PDFRectangle());
        studentRow.setMark4(new PDFRectangle());
        studentRow.setAi(new PDFRectangle());
        
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(16, PAvailableFonts.TIMES);
        group.setFormat(16, PAvailableFonts.TIMES, true);
        year.setFormat(11, PAvailableFonts.TIMES);
        teacher.setFormat(11, PAvailableFonts.TIMES);
        teacher.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        semester.setFormat(teacher.getFormat());
        matter.setFormat(teacher.getFormat());
        teacherLbl.setFormat(11, PAvailableFonts.TIMES);
        teacherLbl.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        semesterLbl.setFormat(teacherLbl.getFormat());
        matterLbl.setFormat(teacherLbl.getFormat());
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getFirstName().setFormat(studentRow.getSecondName().getFormat());
        studentRow.getMark1().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getMark1().getFormat().setDataType(DATA_TYPE.DECIMAL);
        studentRow.getMark2().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark3().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark4().setFormat(studentRow.getMark1().getFormat());
        studentRow.getAi().setFormat(studentRow.getMark1().getFormat());
        
        schoolInfos.reset(368.23801f, 20.279251f, 207.162f, 61.918f);
        title.reset(174.88751f, 20.279251f, 193.3505f, 30.847557f);
        group.reset(174.88751f, 51.349663f, 193.3505f, 30.847557f);
        teacher.reset(22.078f, 20.279f, 102.817f, 15.204f);
        teacherLbl.reset(teacher.getX() + teacher.getWidth() + 2, teacher.getInkscapeY(), teacher.getWidth(), teacher.getHeight());
        matter.reset(22.078f, 35.851f, 102.817f, teacher.getHeight());
        matterLbl.reset(matter.getX() + matter.getWidth() + 2, matter.getInkscapeY(), matter.getWidth(), matter.getHeight());
        semester.reset(22.078f, 51.422f, 102.817f, teacher.getHeight());
        semesterLbl.reset(semester.getX() + semester.getWidth() + 2, semester.getInkscapeY(), semester.getWidth(), semester.getHeight());
        pager.reset(22.0782f, 66.994f, 49.993f, teacher.getHeight());
        year.reset(pager.getX() + pager.getWidth(), 66.994f, 102.817f, teacher.getHeight());

        vDividersXs = new float[] { 
            22.078f,
            73.006f,
            123.934f,
            174.863f,
            225.791f,
            276.719f,
            367.801f,
            470.160f,
            553.920f,
            574.922f
        };
        
        studentRow.getAi().setX(vDividersXs[0] + DIVIDER_THICKNESS);
        studentRow.getAi().setWidth(vDividersXs[1] - studentRow.getAi().getX());
        studentRow.getMark4().setX(vDividersXs[1] + DIVIDER_THICKNESS);
        studentRow.getMark4().setWidth(vDividersXs[2] - studentRow.getMark4().getX());
        studentRow.getMark3().setX(vDividersXs[2] + DIVIDER_THICKNESS);
        studentRow.getMark3().setWidth(vDividersXs[3] - studentRow.getMark3().getX());
        studentRow.getMark2().setX(vDividersXs[3] + DIVIDER_THICKNESS);
        studentRow.getMark2().setWidth(vDividersXs[4] - studentRow.getMark2().getX());
        studentRow.getMark1().setX(vDividersXs[4] + DIVIDER_THICKNESS);
        studentRow.getMark1().setWidth(vDividersXs[5] - studentRow.getMark1().getX());
        studentRow.getFirstName().setX(vDividersXs[5] + DIVIDER_THICKNESS);
        studentRow.getFirstName().setWidth(vDividersXs[6] - studentRow.getFirstName().getX());
        studentRow.getFirstName().applyMargin(2, 2);
        studentRow.getSecondName().setX(vDividersXs[6] + DIVIDER_THICKNESS);
        studentRow.getSecondName().setWidth(vDividersXs[7] - studentRow.getSecondName().getX());
        studentRow.getSecondName().applyMargin(2, 2);
        studentRow.getCode().setX(vDividersXs[7] + DIVIDER_THICKNESS);
        studentRow.getCode().setWidth(vDividersXs[8] - studentRow.getCode().getX());
        studentRow.getNumero().setX(vDividersXs[8] + DIVIDER_THICKNESS);
        studentRow.getNumero().setWidth(vDividersXs[9] - studentRow.getNumero().getX());
        
        studentRow.setRowY(106.676f);
        
        setHeight(715.372f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }
    
}
