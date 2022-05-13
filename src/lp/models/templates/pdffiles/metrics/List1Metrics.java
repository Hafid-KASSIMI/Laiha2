

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.icomoon.IcoMoon;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class List1Metrics extends ListMetrics {
    
    public List1Metrics() {
        super();
        template = "LIST_1";
        pager = new PDFRectangle();
        lastColumnLabel = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setGender(new PDFRectangle());
        studentRow.setBirthDate(new PDFRectangle());
        studentRow.setBirthPlace(new PDFRectangle());
        
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(18, PAvailableFonts.TAHOMA);
        year.setFormat(12, PAvailableFonts.TIMES);
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        lastColumnLabel.setFormat(12, PAvailableFonts.TIMES, true);
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        if ( Settings.PREF_TEMPORARY.get(template + "_USE_GENDER_ICONS").equals("Y") ) {
            studentRow.getGender().setFormat(12, PAvailableFonts.ICOMOON);
            genderFunc = (stu) -> {
                return stu.isFemale() ? IcoMoon.female : IcoMoon.male;
            };
        }
        else {
            studentRow.getGender().setFormat(12, PAvailableFonts.TIMES );
            genderFunc = (stu) -> {
                return stu.getGender();
            };
        }
        studentRow.getBirthDate().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getBirthPlace().setFormat(12, PAvailableFonts.TIMES);
        
        schoolInfos.reset(22.442f, 20.281f, 553.078f, 29.04f);
        title.reset(188.520f, 49.321f, 387f, 29.039f);
        year.reset(22.441f, 49.320f, 166.078f, 29.039f);
        pager.reset(555.798f, 20.281f, 19.722f, 9.756f);
        lastColumnLabel.reset(22.922f, 85.559f, 82.078f, 18.359f);
        
        studentRow.getNumero().setXW(554.519f, 21f);
        studentRow.getCode().setXW(471.48f, 83.038f);
        studentRow.getSecondName().setXW(298.199f, 173.281f);
        studentRow.getSecondName().applyMargin(3, 3);
        studentRow.getGender().setXW(260.281f, 37.917f);
        studentRow.getBirthDate().setXW(188.52f, 71.761f);
        studentRow.getBirthPlace().setXW(105.48f, 83.039f);
        studentRow.getBirthPlace().applyMargin(1.5f, 1.5f);
        studentRow.setRow(104.398f, 17.883f);
        
        vDividersXs = new float[] { 21.961f, 105f, 188.039f, 259.801f, 297.719f, 471f, 554.039f, 575.039f };
        
        setHeight(713.762f);
        setFirstRowY(105.359f);
        setWidth(553.079f);
        setX(21.961f);
    }
    
}
