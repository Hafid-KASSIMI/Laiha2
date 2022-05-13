package lp.models.templates;

import java.text.DecimalFormat;

/**
 *
 * @author Sicut
 */
public class TemplateInfos {
//    private final Integer preferencesIndex;
    private final String label, className;
    private Integer numero;
    protected final DecimalFormat INTEGER = new DecimalFormat();

    public TemplateInfos(/*Integer index, */String label, String className) {
//        this.preferencesIndex = index;
        this.label = label;
        this.className = className;
        INTEGER.applyPattern("00");
    }

//    public Integer getPreferencesIndex() {
//        return preferencesIndex;
//    }

    public String getLabel() {
        return label;
    }

    public String getClassName() {
        return className;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getStrNumero() {
        return INTEGER.format(numero);
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
}
