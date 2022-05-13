

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class List4Metrics extends ListMetrics {
    
    public List4Metrics() {
        super();
        template = "LIST_4";
        
        pager = new XRectangle("A1", 0, 0);
        kingdom = new XRectangle("C1", 0, 0);
        schoolInfos = new XRectangle("R1", 0, 0);
        title = new XRectangle("C4", 0, 0);
        year = new XRectangle("AC1", 0, 0);
        lastColumnLabel = new XRectangle("W6", 0, 0);
        
        firstRow = 6;
        
        studentRow = new XStudentRow();
        studentRow.setNumero(new XRectangle("A7", 0, 0, new Format(11, XAvailableFonts.TIMES)));
        studentRow.getNumero().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getNumero().getFormat().setDataType(DATA_TYPE.INTEGER);
        studentRow.setCode(new XRectangle("B7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getCode().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getCode().setColSpan(5);
        studentRow.setFirstName(new XRectangle("G7", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getFirstName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getFirstName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getFirstName().setColSpan(8);
        studentRow.setSecondName(new XRectangle("O7", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getSecondName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getSecondName().setColSpan(8);
        studentRow.setAdditional(new XRectangle("W7", 0, 0, new Format(12, XAvailableFonts.TIMES, true)));
        studentRow.getAdditional().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional().setColSpan(8);
        
        setHeight(724.5f);
    }
    
}
