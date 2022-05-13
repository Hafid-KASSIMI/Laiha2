

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Absence5Metrics extends AbsenceListMetrics {
    
    public Absence5Metrics() {
        super();
        
        template = "ABSENCE_5";
        pager = new PDFRectangle();
        week = new PDFRectangle();
        bureau = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setFullRow(new PDFRectangle());
        
        schoolInfos.setFormat(11, PAvailableFonts.TIMES);
        title.setFormat(16, PAvailableFonts.TIMES, true);
        year.setFormat(11, PAvailableFonts.TIMES);
        week.setFormat(year.getFormat());
        bureau.setFormat(year.getFormat());
        group.setFormat(16, PAvailableFonts.TIMES, true);
        dayHeader.setFormat(12, PAvailableFonts.TIMES, true);
        halfDayHeader.setFormat(9, PAvailableFonts.TIMES);
        signature.setFormat(11, PAvailableFonts.TIMES);
        signature.getFormat().setHAlignment(HORIZONTAL_ALIGNMENT.LEFT);
        toAdmin.setFormat(signature.getFormat());
        pager.setFormat(10, PAvailableFonts.TIMES);
        pager.getFormat().setRotation(90f);
        
        studentRow.getNumero().setFormat(11, PAvailableFonts.TIMES);
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        
        schoolInfos.reset(428.197f, 18.882f, 152.906f, 45.71875f);
        title.reset(167.079f, 18.882f, 261.118f, 30.238f);
        week.reset(title.getX(), 49.120f, title.getWidth(), 15.480f);
        year.reset(14.173f, 18.882f, 152.906f, 13.518f);
        bureau.reset(14.173f, 32.290f, year.getWidth(), year.getHeight());
        group.reset(14.173f, 46.241f, year.getWidth(), 18.360f);
        signature.reset(418.623f, 718.07802f, 131.79067f, 60.445f);
        signature.applyMargin(5f, 5f);
        toAdmin.reset(418.623f, 778.52302f, 131.79067f, 43.88476f);
        toAdmin.applyMargin(5f, 5f);
        dayHeader.reset(0f, 69.178f, 133.417f, 14.763f);
        halfDayWidth = 66.008f;
        halfDayHeader.reset(0f, 84.541f, halfDayWidth, 20.158f);
        halfDayHeader.applyMargin(HALF_DAY_HEADER_MARGIN, HALF_DAY_HEADER_MARGIN);
        pager.reset(581.039f, 805.834f, 31.797f, 16.574f);

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
        studentRow.getNumero().setX(vDividersXs[25].x + DIVIDER_THICKNESSES[vDividersXs[25].thickness]);
        studentRow.getNumero().setWidth(vDividersXs[26].x - studentRow.getNumero().getX());
        
        studentRow.setFullRow(vDividersXs[0].x + DIVIDER_THICKNESSES[vDividersXs[0].thickness], vDividersXs[vDividersXs.length - 1].x);
        studentRow.setRowY(106.499f);
        
        setHeight(611.579f);
        halfDayBody.reset(0f, studentRow.getRowY(), halfDayWidth, height);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1].x + DIVIDER_THICKNESSES[vDividersXs[vDividersXs.length - 1].thickness] - vDividersXs[0].x);
        setX(vDividersXs[0].x);
    }
    
}
