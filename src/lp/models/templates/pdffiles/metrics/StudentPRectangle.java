

package lp.models.templates.pdffiles.metrics;

import javafx.beans.property.SimpleFloatProperty;
import lp.models.templates.StudentMetrics;
import net.mdrassty.util.pdf.PDFRectangle;

public class StudentPRectangle extends StudentMetrics<PDFRectangle> {
    
    private final SimpleFloatProperty ROW_Y = new SimpleFloatProperty(0);
    private final SimpleFloatProperty ROW_H = new SimpleFloatProperty(0);
    
    public StudentPRectangle() {
        super();
        ROW_Y.addListener((obs, old, cur) -> {
            childs.forEach(c -> {
                c.setY(cur.floatValue());
            });
        });
        ROW_H.addListener((obs, old, cur) -> {
            childs.forEach(c -> {
                c.setHeight(cur.floatValue());
            });
        });
    }

    public void setRow(float y, float h) {
        ROW_H.set(h);
        ROW_Y.set(y);
    }

    public void setRowY(float y) {
        ROW_Y.set(y);        
    }

    public float getRowY() {
        return ROW_Y.get();
    }

    public void setRowHeight(float h) {
        ROW_H.set(h);
    }

    public float getRowHeight() {
        return ROW_H.get();
    }
    
    public void setNumero(float x, float w) {
        numero.setXW(x, w);
    }

    public void setNumero(float x, float w, int fontSize, String font) {
        setNumero(x, w);
        numero.setFormat(fontSize, font);
    }

    public void setCode(float x, float w) {
        code.setXW(x, w);
    }

    public void setCode(float x, float w, int fontSize, String font) {
        code.setXW(x, w);
        code.setFormat(fontSize, font);
    }
    
    public void setFirstName(float x, float w) {
        firstName.setXW(x, w);
    }

    public void setFirstName(float x, float w, int fontSize, String font) {
        setFirstName(x, w);
        firstName.setFormat(fontSize, font);
    }

    public void setSecondName(float x, float w) {
        secondName.setXW(x, w);
    }

    public void setSecondName(float x, float w, int fontSize, String font) {
        setSecondName(x, w);
        secondName.setFormat(fontSize, font);
    }

    public void setGender(float x, float w) {
        gender.setXW(x, w);
    }

    public void setGender(float x, float w, int fontSize, String font) {
        setGender(x, w);
        gender.setFormat(fontSize, font);
    }

    public void setBirthDate(float x, float w) {
        birthDate.setXW(x, w);
    }

    public void setBirthDate(float x, float w, int fontSize, String font) {
        setBirthDate(x, w);
        birthDate.setFormat(fontSize, font);
    }

    public void setBirthPlace(float x, float w) {
        birthPlace.setXW(x, w);
    }

    public void setBirthPlace(float x, float w, int fontSize, String font) {
        setBirthPlace(x, w);
        birthPlace.setFormat(fontSize, font);
    }

    public void setMark1(float x, float w) {
        mark1.setXW(x, w);
    }

    public void setMark1(float x, float w, int fontSize, String font) {
        setMark1(x, w);
        mark1.setFormat(fontSize, font);
    }

    public void setMark2(float x, float w) {
        mark2.setXW(x, w);
    }

    public void setMark2(float x, float w, int fontSize, String font) {
        setMark2(x, w);
        mark2.setFormat(fontSize, font);
    }

    public void setMark3(float x, float w) {
        mark3.setXW(x, w);
    }

    public void setMark3(float x, float w, int fontSize, String font) {
        setMark3(x, w);
        mark3.setFormat(fontSize, font);
    }

    public void setMark4(float x, float w) {
        mark4.setXW(x, w);
    }

    public void setMark4(float x, float w, int fontSize, String font) {
        setMark4(x, w);
        mark4.setFormat(fontSize, font);
    }

    public void setAi(float x, float w) {
        ai.setXW(x, w);
    }

    public void setAi(float x, float w, int fontSize, String font) {
        setAi(x, w);
        ai.setFormat(fontSize, font);
    }

    public void setFullRow(float x1, float x2) {
        fullRow.setXW(x1, x2 - x1);
    }
}
