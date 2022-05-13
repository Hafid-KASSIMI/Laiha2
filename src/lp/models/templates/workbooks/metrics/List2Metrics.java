

package lp.models.templates.workbooks.metrics;

import lp.Settings;
import net.mdrassty.icomoon.IcoMoon;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Format;
import net.mdrassty.util.Misc;
import net.mdrassty.util.excel.XAvailableFonts;
import net.mdrassty.util.excel.XRectangle;

public class List2Metrics extends ListMetrics {
    
    public List2Metrics() {
        super();
        template = "LIST_2";
        
        pager = new XRectangle("H1", 0, 0);
        schoolInfos = new XRectangle("A1", 0, 0);
        title = new XRectangle("D1", 0, 0);
        group = new XRectangle("D2", 0, 0);
        year = new XRectangle("G2", 0, 0);
        lastColumnLabel = new XRectangle("H4", 0, 0);
        
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
        studentRow.setFirstName(new XRectangle("D5", 0, 0, new Format(12, XAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"))));
        studentRow.getFirstName().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getFirstName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.setGender(new XRectangle("E5", 0, 0));
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
        studentRow.setBirthDate(new XRectangle("F5", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getBirthDate().getFormat().setBorders(0, 1, 1, 1);
        studentRow.getBirthDate().getFormat().setDataType(DATA_TYPE.DATE);
        studentRow.setBirthPlace(new XRectangle("G5", 0, 0, new Format(12, XAvailableFonts.TIMES)));
        studentRow.getBirthPlace().getFormat().setBorders(0, 1, 1, 1);
        studentRow.setAdditional(new XRectangle("H5", 0, 0, new Format(12, XAvailableFonts.TIMES, true)));
        studentRow.getAdditional().getFormat().setBorders(0, 1, 1, 1);
        
        setHeight(740.25f);
    }
    
}
