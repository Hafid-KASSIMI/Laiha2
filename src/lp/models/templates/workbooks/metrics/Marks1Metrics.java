

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class Marks1Metrics extends MarksMetrics {
    
    public Marks1Metrics() {
        super();
        template = "MARKS_1";
        
        pager = new XRectangle("AB4", 0, 0);
        kingdom = new XRectangle("A1", 0, 0);
        schoolInfos = new XRectangle("H1", 0, 0);
        title = new XRectangle("M1", 0, 0);
        group = new XRectangle("M3", 0, 0);
        teacher = new XRectangle("Z1", 0, 0);
        matter = new XRectangle("Z2", 0, 0);
        semester = new XRectangle("Z3", 0, 0);
        year = new XRectangle("V4", 0, 0);
        teacherLbl = new XRectangle("V1", 0, 0);
        matterLbl = new XRectangle("V2", 0, 0);
        semesterLbl = new XRectangle("V3", 0, 0);
        
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
        studentRow.getSecondName().setColSpan(5);
        studentRow.setFirstName(new XRectangle("L7", 0, 0));
        studentRow.getFirstName().setFormat(studentRow.getSecondName().getFormat());
        studentRow.getFirstName().setColSpan(4);
        studentRow.setMark1(new XRectangle("P7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getMark1().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getMark1().getFormat().setDataType(DATA_TYPE.DECIMAL);
        studentRow.getMark1().setColSpan(3);
        studentRow.setMark2(new XRectangle("S8", 0, 0));
        studentRow.getMark2().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark2().setColSpan(3);
        studentRow.setMark3(new XRectangle("V8", 0, 0));
        studentRow.getMark3().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark3().setColSpan(3);
        studentRow.setMark4(new XRectangle("Y8", 0, 0));
        studentRow.getMark4().setFormat(studentRow.getMark1().getFormat());
        studentRow.getMark4().setColSpan(3);
        studentRow.setAi(new XRectangle("AB8", 0, 0));
        studentRow.getAi().setFormat(studentRow.getMark1().getFormat());
        studentRow.getAi().setColSpan(3);
        
        
        setHeight(746.85f);
    }
    
}
