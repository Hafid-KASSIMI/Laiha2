

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Marks4Metrics extends MarksMetrics {
    
    public Marks4Metrics() {
        super();
        
        template = "MARKS_4";
        pager = new PDFRectangle();
        lastColumnLabel = new PDFRectangle();
        kingdom = new PDFRectangle();
        yearLbl = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setFirstName(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setMark1(new PDFRectangle());
        studentRow.setMark2(new PDFRectangle());
        studentRow.setMark3(new PDFRectangle());
        studentRow.setMark4(new PDFRectangle());
        studentRow.setAi(new PDFRectangle());
        
        kingdom.setFormat(8, PAvailableFonts.MAGHRIBI);
        kingdom.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        schoolInfos.setFormat(8, PAvailableFonts.MAGHRIBI);
        schoolInfos.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
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
        lastColumnLabel.setFormat(12, PAvailableFonts.TIMES, true);
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getFirstName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getFirstName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getSecondName().setFormat(studentRow.getFirstName().getFormat());
        studentRow.getMark1().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getMark1().getFormat().setDataType(DATA_TYPE.DECIMAL);
        studentRow.getMark2().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark3().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark4().setFormat(studentRow.getMark1().getFormat());
        studentRow.getAi().setFormat(studentRow.getMark1().getFormat());
        
        kingdom.reset(474.96878f, 20.76172f, 101.75f, 51.718751f);
        schoolInfos.reset(317.71877f, 20.76172f, 101.75f, 51.718751f);
        title.reset(57.336787f, 74.466238f, 480.602f, 22.612f);
        teacher.reset(21.359375f, 20.762001f, 129.398f, 13.426f);
        teacherLbl.reset(teacher.getX() + teacher.getWidth() + 2, teacher.getInkscapeY(), teacher.getWidth(), teacher.getHeight());
        matter.reset(21.359375f, 34.188061f, 129.398f, 13.426f);
        matterLbl.reset(matter.getX() + matter.getWidth() + 2, matter.getInkscapeY(), matter.getWidth(), matter.getHeight());
        semester.reset(21.359375f, 47.614121f, 129.398f, 13.426f);
        semesterLbl.reset(semester.getX() + semester.getWidth() + 2, semester.getInkscapeY(), semester.getWidth(), semester.getHeight());
        year.reset(21.359375f, 61.040178f, 129.398f, 13.426f);
        yearLbl.reset(year.getX() + year.getWidth() + 2, year.getInkscapeY(), year.getWidth(), year.getHeight());
        pager.reset(538.44142f, 82.873f, 38.27735f, 14.058f);
        lastColumnLabel.reset(22.320313f, 100.79883f, 36.999998f, 16.5625f);
        
        studentRow.getNumero().setXW(558.23822f, 17.52352f);
        studentRow.getCode().setXW(469.66119f, 87.62f);
        studentRow.getFirstName().setXW(250.08592f, 108.82673f);
        studentRow.getFirstName().applyMargin(2, 2);
        studentRow.getSecondName().setXW(359.8736f, 108.82666f);
        studentRow.getSecondName().applyMargin(2, 2);
        studentRow.getMark1().setXW(212.12499f, 36.99998f);
        studentRow.getMark2().setXW(174.16407f, 36.99998f);
        studentRow.getMark3().setXW(136.20314f, 36.99998f);
        studentRow.getMark4().setXW(98.242196f, 36.999984f);
        studentRow.getAi().setXW(60.281268f, 36.99998f);
        studentRow.setRowY(119.16032f);
        
        vDividersXs = new float[] { 21.359f, 59.320f, 97.281f, 135.242f, 173.203f, 211.164f, 249.125f, 358.913f, 468.700f, 557.281f, 575.762f };
        
        setHeight(702.887f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }
    
}
