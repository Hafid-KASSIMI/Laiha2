

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class Marks3Metrics extends MarksMetrics {
    
    public Marks3Metrics() {
        super();
        template = "MARKS_3";
        
        pager = new XRectangle("A1", 0, 0);
        schoolInfos = new XRectangle("B1", 0, 0);
        title = new XRectangle("A3", 0, 0);
        teacher = new XRectangle("W1", 0, 0);
        matter = new XRectangle("W2", 0, 0);
        semester = new XRectangle("W3", 0, 0);
        year = new XRectangle("W4", 0, 0);
        teacherLbl = new XRectangle("S1", 0, 0);
        matterLbl = new XRectangle("S2", 0, 0);
        semesterLbl = new XRectangle("S3", 0, 0);
        yearLbl = new XRectangle("S4", 0, 0);
        lastColumnLabel = new XRectangle("Z6", 0, 0);
        
        firstRow = 6;
        
        studentRow = new XStudentRow();
        studentRow.setNumero(new XRectangle("A7", 0, 0, new Format(11, XAvailableFonts.TIMES)));
        studentRow.getNumero().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getNumero().getFormat().setDataType(DATA_TYPE.INTEGER);
        studentRow.setCode(new XRectangle("B7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getCode().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getCode().setColSpan(5);
        studentRow.setSecondName(new XRectangle("G7", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getSecondName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getSecondName().setColSpan(9);
        studentRow.setMark1(new XRectangle("P7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getMark1().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getMark1().getFormat().setDataType(DATA_TYPE.DECIMAL);
        studentRow.getMark1().setColSpan(2);
        studentRow.setMark2(new XRectangle("R7", 0, 0));
        studentRow.getMark2().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark2().setColSpan(2);
        studentRow.setMark3(new XRectangle("T7", 0, 0));
        studentRow.getMark3().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark3().setColSpan(2);
        studentRow.setMark4(new XRectangle("V7", 0, 0));
        studentRow.getMark4().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark4().setColSpan(2);
        studentRow.setAi(new XRectangle("X7", 0, 0));
        studentRow.getAi().setFormat(studentRow.getMark1().getFormat());
        studentRow.getAi().setColSpan(2);
        studentRow.setAdditional(new XRectangle("Z7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getAdditional().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional().setColSpan(5);
        
        
        setHeight(746.85f);
    }
    
}
