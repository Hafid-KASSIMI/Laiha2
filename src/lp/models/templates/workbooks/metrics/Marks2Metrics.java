

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class Marks2Metrics extends MarksMetrics {
    
    public Marks2Metrics() {
        super();
        template = "MARKS_2";
        
        pager = new XRectangle("I4", 0, 0);
        schoolInfos = new XRectangle("A1", 0, 0);
        title = new XRectangle("D1", 0, 0);
        group = new XRectangle("D3", 0, 0);
        teacher = new XRectangle("H1", 0, 0);
        matter = new XRectangle("H2", 0, 0);
        semester = new XRectangle("H3", 0, 0);
        year = new XRectangle("G4", 0, 0);
        teacherLbl = new XRectangle("G1", 0, 0);
        matterLbl = new XRectangle("G2", 0, 0);
        semesterLbl = new XRectangle("G3", 0, 0);
        
        firstRow = 6;
        
        studentRow = new XStudentRow();
        studentRow.setNumero(new XRectangle("A7", 0, 0, new Format(11, XAvailableFonts.TIMES)));
        studentRow.getNumero().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getNumero().getFormat().setDataType(DATA_TYPE.INTEGER);
        studentRow.setCode(new XRectangle("B7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getCode().getFormat().setBorders(0, 1, 1, 1);
        studentRow.setSecondName(new XRectangle("C7", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getSecondName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.setFirstName(new XRectangle("D7", 0, 0));
        studentRow.getFirstName().setFormat(studentRow.getSecondName().getFormat());
        studentRow.setMark1(new XRectangle("E7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getMark1().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getMark1().getFormat().setDataType(DATA_TYPE.DECIMAL);
        studentRow.setMark2(new XRectangle("F7", 0, 0));
        studentRow.getMark2().setFormat(studentRow.getMark1().getFormat());
        studentRow.setMark3(new XRectangle("G7", 0, 0));
        studentRow.getMark3().setFormat(studentRow.getMark1().getFormat());
        studentRow.setMark4(new XRectangle("H7", 0, 0));
        studentRow.getMark4().setFormat(studentRow.getMark1().getFormat());
        studentRow.setAi(new XRectangle("I7", 0, 0));
        studentRow.getAi().setFormat(studentRow.getMark1().getFormat());
        
        
        setHeight(737.5f);
    }
    
}
