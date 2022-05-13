

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class List4Metrics extends ListMetrics {
    
    public List4Metrics() {
        super();
        template = "LIST_4";
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
        studentRow.setFirstName(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        
        studentRow.getNumero().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getCode().setFormat(12, PAvailableFonts.TIMES);
        studentRow.getFirstName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getFirstName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        studentRow.getSecondName().setFormat(studentRow.getFirstName().getFormat());
        
        pager.reset(538.44142f, 20.76172f, 38.27735f, 10.110942f);
        schoolInfos.reset(57.840f, 20.762f, 203.398f, 51.719f);
        kingdom.reset(335.039f, 20.762f, 203.398f, 51.719f);
        title.reset(57.840f, 75.719f, 480.602f, 21.359f);
        year.reset(57.961f, 59.996f, 76.316f, 37.082f);
        lastColumnLabel.reset(22.32f, 100.676f, 146.8805f, 16.563f);
        
        studentRow.getNumero().setXW(558.238f, 17.523f);
        studentRow.getCode().setXW(465.83982f, 91.44141f);
        studentRow.getFirstName().setXW(317.99864f, 146.88025f);
        studentRow.getSecondName().setXW(170.15744f, 146.88025f);
        studentRow.getSecondName().applyMargin(3, 3);

        
        studentRow.setRowY(119.160f);
        
        vDividersXs = new float[] { 21.359f, 169.201f, 317.038f, 464.879f, 557.281f, 575.762f };
        
        setHeight(702.887f);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1] - vDividersXs[0]);
        setX(vDividersXs[0]);
    }
    
}
