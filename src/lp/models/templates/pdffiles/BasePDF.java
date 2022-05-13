

package lp.models.templates.pdffiles;

import lp.models.templates.BaseFile;
import lp.util.FILE_TYPE;
import net.mdrassty.util.pdf.BasePDFFile;

public abstract class BasePDF extends BaseFile {
    protected BasePDFFile pdf;
    protected String tpl;
    protected final String tempaltesDir = "/lp/resources/templates/";
    
    public BasePDF(){
        extension = ".pdf";
        type = FILE_TYPE.PDF;
        pdf = new BasePDFFile();
    }
    
    @Override
    public final void reset(){
        pdf.reset(tpl);
        coreFile = pdf.getDoc();
    }
    
    @Override
    public boolean save(String path) {
        pdf.getDoc().removePage(0);
        return pdf.save(path);
    }
    
}
