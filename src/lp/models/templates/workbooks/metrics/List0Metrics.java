

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class List0Metrics extends ListMetrics {
    
    private final XRectangle customLabel1, customLabel2, customLabel3;
    
    public List0Metrics() {
        super();
        template = "LIST_0";
        
        pager = new XRectangle("A1", 0, 0);
        schoolInfos = new XRectangle("B1", 0, 0);
        title = new XRectangle("A2", 0, 0);
        year = new XRectangle("G2", 0, 0);
        customLabel1 = new XRectangle("D4", 0, 0);
        customLabel2 = new XRectangle("F4", 0, 0);
        customLabel3 = new XRectangle("H4", 0, 0);
        
        firstRow = 4;
        
        studentRow = new XStudentRow();
        studentRow.setNumero(new XRectangle("A5", 0, 0, new Format(11, XAvailableFonts.TIMES)));
        studentRow.getNumero().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getNumero().getFormat().setDataType(DATA_TYPE.INTEGER);
        studentRow.setCode(new XRectangle("B5", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getCode().getFormat().setBorders(0, 1, 1, 1);
        studentRow.setSecondName(new XRectangle("C5", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getSecondName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.setAdditional(new XRectangle("D5", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getAdditional().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional().setColSpan(2);
        studentRow.setAdditional1(new XRectangle("F5", 0, 0));
        studentRow.getAdditional1().setFormat(studentRow.getAdditional().getFormat());
        studentRow.getAdditional1().setColSpan(2);
        studentRow.setAdditional2(new XRectangle("H5", 0, 0));
        studentRow.getAdditional2().setFormat(studentRow.getAdditional().getFormat());
        studentRow.getAdditional2().setColSpan(2);
        
        setHeight(737.5f);
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
