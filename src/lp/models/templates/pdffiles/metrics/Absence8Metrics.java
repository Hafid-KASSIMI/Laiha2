

package lp.models.templates.pdffiles.metrics;

import lp.Settings;
import net.mdrassty.util.HORIZONTAL_ALIGNMENT;
import net.mdrassty.util.Misc;
import net.mdrassty.util.pdf.PAvailableFonts;
import net.mdrassty.util.pdf.PDFRectangle;

public class Absence8Metrics extends AbsenceListMetrics {
    
    public Absence8Metrics() {
        super();
        
        template = "ABSENCE_8";
        pager = new PDFRectangle();
        week = new PDFRectangle();
        bureau = new PDFRectangle();
        kingdom = new PDFRectangle();
        
        studentRow.setNumero(new PDFRectangle());
        studentRow.setSecondName(new PDFRectangle());
        studentRow.setAdditional(new PDFRectangle());
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
        
        studentRow.getNumero().setFormat(10, PAvailableFonts.TIMES);
        studentRow.getAdditional().setFormat(studentRow.getNumero().getFormat());
        studentRow.getSecondName().setFormat(12, PAvailableFonts.TIMES, Settings.PREF_TEMPORARY.get(template + "_BOLDER_NAMES").equals("Y"));
        studentRow.getSecondName().getFormat().setHAlignment(Misc.parseHAlignment(Settings.PREF_TEMPORARY.get(template + "_NAMES_ALIGN")));
        
        kingdom.reset(498.66481f, 18.882f, 82.4375f, 45.71875f);
        schoolInfos.reset(353.03911f, 18.882f, 96.60157f, 45.71875f);
        title.reset(143.407f, 18.882f, 209.160f, 30.238f);
        week.reset(143.407f, 49.120f, 209.160f, 15.480f);
        year.reset(14.173f, 18.882f, 128.762f, 13.518f);
        bureau.reset(14.173f, 32.290f, 128.762f, 13.518f);
        group.reset(14.173f, 46.241f, 128.762f, 18.360f);
        signature.reset(431.22298f, 718.07802f, 131.79067f, 60.445f);
        signature.applyMargin(6, 3);
        toAdmin.reset(431.22298f, 778.52302f, 131.79067f, 43.88476f);
        toAdmin.applyMargin(6, 3);
        dayHeader.reset(0f, 69.178f, 65.538f, 17.763f);
        halfDayWidth = 32.169f;
        halfDayHeader.reset(0f, 87.541f, halfDayWidth - 2 * HALF_DAY_HEADER_MARGIN, 17.158f);
        pager.reset(581.039f, 805.833f, 31.797f, 16.574f);

        vDividersXs = new VDivider[] { 
            new VDivider(0, 14.173f),
            new VDivider(0, 22.365f),
            new VDivider(0, 30.558f),
            new VDivider(0, 38.750f),
            new VDivider(1, 46.942f),
            new VDivider(0, 55.734f),
            new VDivider(0, 63.927f),
            new VDivider(0, 72.119f),
            new VDivider(2, 80.311f),
            new VDivider(0, 89.703f),
            new VDivider(0, 97.896f),
            new VDivider(0, 106.088f),
            new VDivider(1, 114.280f),
            new VDivider(0, 123.072f),
            new VDivider(0, 131.265f),
            new VDivider(0, 139.457f),
            new VDivider(2, 147.649f),
            new VDivider(0, 157.041f),
            new VDivider(0, 165.233f),
            new VDivider(0, 173.426f),
            new VDivider(1, 181.618f),
            new VDivider(0, 190.410f),
            new VDivider(0, 198.602f),
            new VDivider(0, 206.795f),
            new VDivider(0, 214.987f),
            new VDivider(0, 229.809f),
            new VDivider(0, 238.002f),
            new VDivider(0, 246.194f),
            new VDivider(0, 254.386f),
            new VDivider(1, 262.578f),
            new VDivider(0, 271.371f),
            new VDivider(0, 279.563f),
            new VDivider(0, 287.755f),
            new VDivider(2, 295.947f),
            new VDivider(0, 305.339f),
            new VDivider(0, 313.532f),
            new VDivider(0, 321.724f),
            new VDivider(1, 329.916f),
            new VDivider(0, 338.708f),
            new VDivider(0, 346.901f),
            new VDivider(0, 355.093f),
            new VDivider(2, 363.285f),
            new VDivider(0, 372.677f),
            new VDivider(0, 380.869f),
            new VDivider(0, 389.062f),
            new VDivider(1, 397.254f),
            new VDivider(0, 406.046f),
            new VDivider(0, 414.238f),
            new VDivider(0, 422.431f),
            new VDivider(0, 430.623f),
            new VDivider(0, 564.059f),
            new VDivider(0, 580.502f)
        };
        
        daysXsIndices = new int[] {
            41,
            33,
            25,
            16,
            8,
            0
        };
        studentRow.getAdditional().setX(vDividersXs[24].x + DIVIDER_THICKNESSES[vDividersXs[24].thickness]);
        studentRow.getAdditional().setWidth(vDividersXs[25].x - studentRow.getAdditional().getX());
        studentRow.getSecondName().setX(vDividersXs[49].x + DIVIDER_THICKNESSES[vDividersXs[49].thickness]);
        studentRow.getSecondName().setWidth(vDividersXs[50].x - studentRow.getSecondName().getX());
        studentRow.getSecondName().applyMargin(2, 2);
        studentRow.getNumero().setX(vDividersXs[50].x + DIVIDER_THICKNESSES[vDividersXs[50].thickness]);
        studentRow.getNumero().setWidth(vDividersXs[51].x - studentRow.getNumero().getX());
        
        studentRow.setFullRow(vDividersXs[0].x + DIVIDER_THICKNESSES[vDividersXs[0].thickness], vDividersXs[vDividersXs.length - 1].x);
        studentRow.setRowY(106.499f);
        
        setHeight(611.579f);
        halfDayBody.reset(0f, studentRow.getRowY(), halfDayWidth, height);
        setFirstRowY(studentRow.getRowY());
        setWidth(vDividersXs[vDividersXs.length - 1].x + DIVIDER_THICKNESSES[vDividersXs[vDividersXs.length - 1].thickness] - vDividersXs[0].x);
        setX(vDividersXs[0].x);
    }
    
}
