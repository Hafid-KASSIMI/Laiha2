

package lp.models.templates.pdffiles;

import lp.models.templates.pdffiles.metrics.Absence4Metrics;
import lp.Settings;
import lp.util.Calendar.WEEK;
import net.mdrassty.object.Group;
import org.apache.pdfbox.pdmodel.PDPage;

public class Absence4 extends AbsencePDF {
    
    public Absence4() {
        super();
        pageMetrics = new Absence4Metrics();
        tpl = tempaltesDir + pageMetrics.getTemplate() + extension;
    }

    @Override
    protected void add1PageGroup(Group g, PDPage page, WEEK week) {
        pdf.placeMultilineString(pageMetrics.getSchoolInfos(), page, g.getLevel().getSchool().getAcademy() + "\n" + g.getLevel().getSchool().getDirection() + "\n" + g.getLevel().getSchool().getSchool());
        pdf.placeString(pageMetrics.getTitle(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TITLE"));
        pdf.placeString(pageMetrics.getYear(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_YEAR") + " " + g.getLevel().getSchool().getYear());
        pdf.placeString(((Absence4Metrics) pageMetrics).getBureau(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SURVEILANCE_DESKTOP")
                                + Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SURVEILANCE_NUMERO"));
        pdf.wrapNResizeText(((Absence4Metrics) pageMetrics).getToAdmin(), page, Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_TO_ADMINISTRATION"));
        pdf.placeString(pageMetrics.getGroup(), page, g.getName());
        placeTableHeader(page, week);
    }
}
