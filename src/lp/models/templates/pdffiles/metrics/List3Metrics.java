

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.icomoon.IcoMoon;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class List3Metrics extends ListMetrics {
    
    public List3Metrics() {
        super();
        template = "LIST_3";
        pager = new PDFRectangle();
        lastColumnLabel = new PDFRectangle();
        kingdom = new PDFRectangle();
        
        kingdom.setFormat(8, PAvailableFonts.MAGHRIBI);
        kingdom.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        schoolInfos.setFormat(8, PAvailableFonts.MAGHRIBI);
        schoolInfos.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        title.setFormat(18, PAvailableFonts.TIMES, true);
        year.setFormat(11, PAvailableFonts.TIMES);
        year.getFormat().setRotation(90);
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        lastColumnLabel.setFormat(12, PAvailableFonts.TIMES, true);
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setGender(new PDFRectangle());
        studentRow.setBirthDate(new PDFRectangle());
        studentRow.setBirthPlace(new PDFRectangle());
        
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
        
        pager.reset(538.44142f, 20.76172f, 38.27735f, 10.110942f);
        kingdom.reset(335.039f, 20.762f, 203.398f, 51.719f);
        schoolInfos.reset(57.840f, 20.762f, 203.398f, 51.719f);
        title.reset(57.840f, 75.719f, 480.602f, 21.359f);
        year.reset(57.961f, 59.996f, 76.316f, 37.082f);
        lastColumnLabel.reset(22.31835f, 100.80078f, 72.960956f, 16.43755f);

        
        studentRow.getNumero().setXW(558.238f, 17.523f);
        studentRow.getCode().setXW(465.83982f, 91.44141f);
        studentRow.getSecondName().setXW(281.03907f, 183.83982f);
        studentRow.getSecondName().applyMargin(3, 3);
        studentRow.getGender().setXW(244.07812f, 36.00002f);
        studentRow.getBirthDate().setXW(170.16017f, 72.96091f);
        studentRow.getBirthPlace().setXW(96.238286f, 72.960954f);
        studentRow.getBirthPlace().applyMargin(1.5f, 1.5f);
        studentRow.setRowY(119.160f);
        
        vDividersXs = new float[] { 21.359f, 95.279f, 169.199f, 243.119f, 280.078f, 464.879f, 557.279f, 575.760f };
        
        setHeight(702.887f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }
    
}
