

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.icomoon.IcoMoon;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class List3Metrics extends ListMetrics {
    
    public List3Metrics() {
        super();
        template = "LIST_3";
        
        pager = new XRectangle("A1", 0, 0);
        kingdom = new XRectangle("C1", 0, 0);
        schoolInfos = new XRectangle("R1", 0, 0);
        title = new XRectangle("C4", 0, 0);
        year = new XRectangle("AC1", 0, 0);
        lastColumnLabel = new XRectangle("AA6", 0, 0);
        
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
        studentRow.getSecondName().setColSpan(10);
        studentRow.setGender(new XRectangle("Q7", 0, 0));
        if ( Settings.PREF_TEMPORARY.get(template + "_USE_GENDER_ICONS").equals("Y") ) {
            studentRow.getGender().setFormat(12, XAvailableFonts.ICOMOON);
            genderFunc = (stu) -> {
                return stu.isFemale() ? IcoMoon.female : IcoMoon.male;
            };
        }
        else {
            studentRow.getGender().setFormat(12, XAvailableFonts.TIMES );
            genderFunc = (stu) -> {
                return stu.getGender();
            };
        }
        studentRow.getGender().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getGender().setColSpan(2);
        studentRow.setBirthDate(new XRectangle("S7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getBirthDate().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getBirthDate().getFormat().setDataType(DATA_TYPE.DATE);
        studentRow.getBirthDate().setColSpan(4);
        studentRow.setBirthPlace(new XRectangle("W7", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getBirthPlace().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getBirthPlace().setColSpan(4);
        studentRow.setAdditional(new XRectangle("AA7", 0, 0, new Format(12, XAvailableFonts.TIMES, true)));
        studentRow.getAdditional().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getAdditional().setColSpan(4);
        
        setHeight(724.5f);
    }
    
}
