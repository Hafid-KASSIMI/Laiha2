

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class List0Metrics extends ListMetrics {
    
    private final PDFRectangle customLabel1, customLabel2, customLabel3;
    
    public List0Metrics() {
        super();
        template = "LIST_0";
        pager = new PDFRectangle();
        customLabel1 = new PDFRectangle();
        customLabel2 = new PDFRectangle();
        customLabel3 = new PDFRectangle();
        
        schoolInfos.setFormat(12, PAvailableFonts.TIMES);
        title.setFormat(18, PAvailableFonts.TAHOMA);
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
        
        schoolInfos.reset(22.442f, 20.281f, 553.078f, 29.04f);
        title.reset(188.520f, 49.321f, 387f, 29.039f);
        year.reset(22.441f, 49.320f, 166.078f, 29.039f);
        pager.reset(555.798f, 20.281f, 19.722f, 9.756f);
        
        vDividersXs = new float[] { 
            21.359f, 
            113.762f, 
            206.160f, 
            298.563f, 
            464.879f, 
            557.281f, 
            575.762f 
        };
        
        customLabel3.setYH(85.202f, 16.798f);
        customLabel3.setX(vDividersXs[0] + DIVIDER_THICKNESS);
        customLabel3.setWidth(vDividersXs[1] - customLabel3.getX());
        customLabel2.setYH(customLabel3.getInkscapeY(), customLabel3.getHeight());
        customLabel2.setX(vDividersXs[1] + DIVIDER_THICKNESS);
        customLabel2.setWidth(vDividersXs[2] - customLabel2.getX());
        customLabel1.setYH(customLabel3.getInkscapeY(), customLabel3.getHeight());
        customLabel1.setX(vDividersXs[2] + DIVIDER_THICKNESS);
        customLabel1.setWidth(vDividersXs[3] - customLabel1.getX());
        studentRow.getSecondName().setX(vDividersXs[3] + DIVIDER_THICKNESS);
        studentRow.getSecondName().setWidth(vDividersXs[4] - studentRow.getSecondName().getX());
        studentRow.getSecondName().applyMargin(3, 3);
        studentRow.getCode().setX(vDividersXs[4] + DIVIDER_THICKNESS);
        studentRow.getCode().setWidth(vDividersXs[5] - studentRow.getCode().getX());
        studentRow.getNumero().setX(vDividersXs[5] + DIVIDER_THICKNESS);
        studentRow.getNumero().setWidth(vDividersXs[6] - studentRow.getNumero().getX());

        
        studentRow.setRowY(103.922f);
        
        setHeight(713.762f);
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
