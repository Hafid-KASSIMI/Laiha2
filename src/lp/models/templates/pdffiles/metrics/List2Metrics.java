

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.icomoon.IcoMoon;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class List2Metrics extends ListMetrics {
    
    public List2Metrics() {
        super();
        template = "LIST_2";
        pager = new PDFRectangle();
        lastColumnLabel = new PDFRectangle();
        
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(18, PAvailableFonts.TAHOMA);
        group.setFormat(18, PAvailableFonts.TAHOMA, true);
        year.setFormat(12, PAvailableFonts.TIMES);
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        lastColumnLabel.setFormat(12, PAvailableFonts.TIMES, true);
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setFirstName(new PDFRectangle());
        studentRow.setGender(new PDFRectangle());
        studentRow.setBirthDate(new PDFRectangle());
        studentRow.setBirthPlace(new PDFRectangle());
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getFirstName().setFormat(studentRow.getSecondName().getFormat());
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
        
        pager.reset(18.359375f, 20.28076f, 38.034444f, 10.435755f);
        schoolInfos.reset(384.84f, 20.281f, 194.282f, 58.078f);
        title.reset(184.912f, 20.281f, 199.928f, 29.039f);
        group.reset(184.912f, 49.32f, 199.928f, 29.039f);
        year.reset(18.359f, 49.32f, 166.552f, 29.039f);
        lastColumnLabel.reset(19.320f, 83.559f, 82.088f, 18.917f);

        
        studentRow.getNumero().setXW(558.602f, 20.039f);
        studentRow.getCode().setXW(475.559f, 82.082f);
        studentRow.getSecondName().setXW(385.320f, 89.281f);
        studentRow.getSecondName().applyMargin(2, 2);
        studentRow.getFirstName().setXW(295.078f, 89.281f);
        studentRow.getFirstName().applyMargin(2, 2);
        studentRow.getGender().setXW(257.160f, 36.961f);
        studentRow.getBirthDate().setXW(185.398f, 70.801f);
        studentRow.getBirthPlace().setXW(102.359f, 82.082f);
        studentRow.getBirthPlace().applyMargin(1.5f, 1.5f);
        studentRow.setRow(104.398f, 17.883f);
        
        vDividersXs = new float[] { 18.359f, 101.398f, 184.441f, 256.199f, 294.121f, 384.359f, 474.602f, 557.641f, 578.641f };
        
        setHeight(714.723f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }
    
}
