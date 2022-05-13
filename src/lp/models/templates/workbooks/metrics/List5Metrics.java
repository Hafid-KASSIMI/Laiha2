

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class List5Metrics extends ListMetrics {
    
    private final XRectangle customLabel1, customLabel2, customLabel3;
    
    public List5Metrics() {
        super();
        template = "LIST_5";
        
        pager = new XRectangle("A1", 0, 0);
        kingdom = new XRectangle("C1", 0, 0);
        schoolInfos = new XRectangle("R1", 0, 0);
        title = new XRectangle("C4", 0, 0);
        year = new XRectangle("AC1", 0, 0);
        customLabel1 = new XRectangle("P6", 0, 0);
        customLabel2 = new XRectangle("U6", 0, 0);
        customLabel3 = new XRectangle("Z6", 0, 0);
        
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
        studentRow.setAdditional(new XRectangle("P7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getAdditional().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional().setColSpan(5);
        studentRow.setAdditional1(new XRectangle("U7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getAdditional1().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional1().setColSpan(5);
        studentRow.setAdditional2(new XRectangle("Z7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getAdditional2().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional2().setColSpan(5);
        
        
        setHeight(724.5f);
    }

    public XRectangle getCustomLabel1() {
        return customLabel1;
    }

    public XRectangle getCustomLabel2() {
        return customLabel2;
    }

    public XRectangle getCustomLabel3() {
        return customLabel3;
    }
    
}
