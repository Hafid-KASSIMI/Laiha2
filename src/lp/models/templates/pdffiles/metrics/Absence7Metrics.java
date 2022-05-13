

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Absence7Metrics extends AbsenceListMetrics {
    
    public Absence7Metrics() {
        super();
        
        template = "ABSENCE_7";
        pager = new PDFRectangle();
        week = new PDFRectangle();
        bureau = new PDFRectangle();
        kingdom = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setFullRow(new PDFRectangle());
        
        kingdom.setFormat(8, PAvailableFonts.MAGHRIBI);
        kingdom.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        schoolInfos.setFormat(8, PAvailableFonts.MAGHRIBI);
        schoolInfos.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.RIGHT);
        title.setFormat(16, PAvailableFonts.TIMES, true);
        week.setFormat(year.getFormat());
        year.setFormat(11, PAvailableFonts.TIMES);
        bureau.setFormat(year.getFormat());
        group.setFormat(16, PAvailableFonts.TIMES, true);
        dayHeader.setFormat(12, PAvailableFonts.TIMES, true);
        halfDayHeader.setFormat(10, PAvailableFonts.TIMES);
        signature.setFormat(11, PAvailableFonts.TIMES);
        signature.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        toAdmin.setFormat(signature.getFormat());
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setRotation(90);
        
        studentRow.getNumero().setFormat(11, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        
        kingdom.reset(498.66481f, 18.882f, 82.4375f, 45.71875f);
        schoolInfos.reset(353.03911f, 18.882f, 96.60157f, 45.71875f);
        title.reset(143.407f, 18.882f, 209.160f, 30.238f);
        week.reset(143.407f, 49.120f, 209.160f, 15.480f);
        year.reset(14.173f, 18.882f, 128.762f, 13.518f);
        bureau.reset(14.173f, 32.290f, 128.762f, 13.518f);
        group.reset(14.173f, 46.241f, 128.762f, 18.360f);
        signature.reset(434.525f, 718.07802f, 128.488f, 60.445f);
        toAdmin.reset(434.525f, 778.52302f, 128.488f, 43.88476f);
        pager.reset(581.039f, 805.833f, 31.797f, 16.574f);
        dayHeader.reset(0f, 69.178f, 133.217f, 14.763f);
        halfDayWidth = 66.008f;
        halfDayHeader.reset(0f, 84.541f, halfDayWidth, 20.158f);

        vDividersXs = new VDivider[] { 
            new VDivider(0, 14.173f),
            new VDivider(0, 30.825f),
            new VDivider(0, 47.477f),
            new VDivider(0, 64.129f),
            new VDivider(1, 80.781f),
            new VDivider(0, 98.034f),
            new VDivider(0, 114.686f),
            new VDivider(0, 131.338f),
            new VDivider(2, 147.990f),
            new VDivider(0, 165.842f),
            new VDivider(0, 182.494f),
            new VDivider(0, 199.146f),
            new VDivider(1, 215.798f),
            new VDivider(0, 233.050f),
            new VDivider(0, 249.702f),
            new VDivider(0, 266.354f),
            new VDivider(2, 283.006f),
            new VDivider(0, 300.858f),
            new VDivider(0, 317.510f),
            new VDivider(0, 334.163f),
            new VDivider(1, 350.815f),
            new VDivider(0, 368.067f),
            new VDivider(0, 384.719f),
            new VDivider(0, 401.371f),
            new VDivider(0, 418.023f),
            new VDivider(0, 561.059f),
            new VDivider(0, 580.502f)
        };
        
        daysXsIndices = new int[] { 16, 8, 0 };
        studentRow.getSecondName().setX(vDividersXs[24].x + DIVIDER_THICKNESSES[vDividersXs[24].thickness]);
        studentRow.getSecondName().setWidth(vDividersXs[25].x - studentRow.getSecondName().getX());
        studentRow.getSecondName().applyMargin(2, 2);
        studentRow.getNumero().setX(vDividersXs[25].x + DIVIDER_THICKNESSES[vDividersXs[25].thickness]);
        studentRow.getNumero().setWidth(vDividersXs[26].x - studentRow.getNumero().getX());
        
        studentRow.setFullRow(vDividersXs[0].x + DIVIDER_THICKNESSES[vDividersXs[0].thickness], vDividersXs[vDividersXs.length - 1].x);
        studentRow.setRowY(106.499f);
        
        setHeight(611.579f);
        halfDayBody.reset(0f, studentRow.getRowY(), halfDayHeader.getWidth(), height);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1].x + DIVIDER_THICKNESSES[vDividersXs[vDividersXs.length - 1].thickness] - vDividersXs[0].x);
        setX(vDividersXs[0].x);
    }
    
}
