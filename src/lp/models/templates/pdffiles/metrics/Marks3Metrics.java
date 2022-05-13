

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Marks3Metrics extends MarksMetrics {
    
    public Marks3Metrics() {
        super();
        
        template = "MARKS_3";
        pager = new PDFRectangle();
        lastColumnLabel = new PDFRectangle();
        yearLbl = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setMark1(new PDFRectangle());
        studentRow.setMark2(new PDFRectangle());
        studentRow.setMark3(new PDFRectangle());
        studentRow.setMark4(new PDFRectangle());
        studentRow.setAi(new PDFRectangle());
        
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(16, PAvailableFonts.TIMES, true);
        year.setFormat(11, PAvailableFonts.TIMES);
        year.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        teacher.setFormat(year.getFormat());
        semester.setFormat(year.getFormat());
        matter.setFormat(year.getFormat());
        yearLbl.setFormat(11, PAvailableFonts.TIMES);
        yearLbl.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        teacherLbl.setFormat(yearLbl.getFormat());
        semesterLbl.setFormat(yearLbl.getFormat());
        matterLbl.setFormat(yearLbl.getFormat());
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        pager.getFormat().setRotation(90f);
        lastColumnLabel.setFormat(12, PAvailableFonts.TIMES, true);
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getMark1().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getMark1().getFormat().setDataType(DATA_TYPE.DECIMAL);
        studentRow.getMark2().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark3().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark4().setFormat(studentRow.getMark1().getFormat());
        studentRow.getAi().setFormat(studentRow.getMark1().getFormat());
        
        schoolInfos.reset(224.75738f, 20.76172f, 334.4374f, 26.852f);
        title.reset(224.75738f, 47.614178f, 351.9614f, 26.852f);
        teacher.reset(21.359375f, 20.762001f, 129.398f, 13.426f);
        teacherLbl.reset(teacher.getX() + teacher.getWidth() + 2, teacher.getInkscapeY(), teacher.getWidth(), teacher.getHeight());
        matter.reset(21.359375f, 34.188061f, 129.398f, 13.426f);
        matterLbl.reset(matter.getX() + matter.getWidth() + 2, matter.getInkscapeY(), matter.getWidth(), matter.getHeight());
        semester.reset(21.359375f, 47.614121f, 129.398f, 13.426f);
        semesterLbl.reset(semester.getX() + semester.getWidth() + 2, semester.getInkscapeY(), semester.getWidth(), semester.getHeight());
        year.reset(21.359375f, 61.040178f, 129.398f, 13.426f);
        yearLbl.reset(year.getX() + year.getWidth() + 2, year.getInkscapeY(), year.getWidth(), year.getHeight());
        pager.reset(576.719f, 30.09f, 26.852f, 17.524f);
        lastColumnLabel.reset(22.320313f, 79.799f, 77.5f, 16.5625f);

        vDividersXs = new float[] { 
            21.359f, 
            99.820f, 
            137.781f, 
            175.742f, 
            213.703f, 
            251.664f, 
            289.625f, 
            468.700f,
            557.281f, 
            575.762f 
        };
        
        studentRow.getAi().setX(vDividersXs[1] + DIVIDER_THICKNESS);
        studentRow.getAi().setWidth(vDividersXs[2] - studentRow.getAi().getX());
        studentRow.getMark4().setX(vDividersXs[2] + DIVIDER_THICKNESS);
        studentRow.getMark4().setWidth(vDividersXs[3] - studentRow.getMark4().getX());
        studentRow.getMark3().setX(vDividersXs[3] + DIVIDER_THICKNESS);
        studentRow.getMark3().setWidth(vDividersXs[4] - studentRow.getMark3().getX());
        studentRow.getMark2().setX(vDividersXs[4] + DIVIDER_THICKNESS);
        studentRow.getMark2().setWidth(vDividersXs[5] - studentRow.getMark2().getX());
        studentRow.getMark1().setX(vDividersXs[5] + DIVIDER_THICKNESS);
        studentRow.getMark1().setWidth(vDividersXs[6] - studentRow.getMark1().getX());
        studentRow.getSecondName().setX(vDividersXs[6] + DIVIDER_THICKNESS);
        studentRow.getSecondName().setWidth(vDividersXs[7] - studentRow.getSecondName().getX());
        studentRow.getSecondName().applyMargin(3, 3);
        studentRow.getCode().setX(vDividersXs[7] + DIVIDER_THICKNESS);
        studentRow.getCode().setWidth(vDividersXs[8] - studentRow.getCode().getX());
        studentRow.getNumero().setX(vDividersXs[8] + DIVIDER_THICKNESS);
        studentRow.getNumero().setWidth(vDividersXs[9] - studentRow.getNumero().getX());
        studentRow.setRowY(98.160f);
        
        setHeight(723.887f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }
    
}
