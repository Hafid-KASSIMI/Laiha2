

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class List5Metrics extends ListMetrics {
    
    private final PDFRectangle customLabel1, customLabel2, customLabel3;
    
    public List5Metrics() {
        super();
        template = "LIST_5";
        pager = new PDFRectangle();
        customLabel1 = new PDFRectangle();
        customLabel2 = new PDFRectangle();
        customLabel3 = new PDFRectangle();
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
        customLabel1.setFormat(12, PAvailableFonts.TIMES, true);
        customLabel2.setFormat(customLabel1.getFormat());
        customLabel3.setFormat(customLabel1.getFormat());
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setCode(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        
        pager.reset(538.44142f, 20.76172f, 38.27735f, 10.110942f);
        schoolInfos.reset(57.840f, 20.762f, 203.398f, 51.719f);
        kingdom.reset(335.039f, 20.762f, 203.398f, 51.719f);
        title.reset(57.840f, 75.719f, 480.602f, 21.359f);
        year.reset(57.961f, 59.996f, 76.316f, 37.082f);
        customLabel1.reset(207.12111f, 100.79883f, 91.44141f, 16.5625f);
        customLabel2.reset(114.71875f, 100.79883f, 91.44141f, 16.5625f);
        customLabel3.reset(22.320313f, 100.79883f, 91.44141f, 16.5625f);
        
        studentRow.getNumero().setXW(558.238f, 17.523f);
        studentRow.getCode().setXW(465.83982f, 91.44141f);
        studentRow.getSecondName().setXW(299.52347f, 165.35542f);
        studentRow.getSecondName().applyMargin(3, 3);

        
        studentRow.setRowY(119.160f);
        
        vDividersXs = new float[] { 21.359f, 113.762f, 206.160f, 298.563f, 464.879f, 557.281f, 575.762f };
        
        setHeight(702.887f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }

    public PDFRectangle getCustomLabel1() {
        return customLabel1;
    }

    public PDFRectangle getCustomLabel2() {
        return customLabel2;
    }

    public PDFRectangle getCustomLabel3() {
        return customLabel3;
    }
    
}
