

package lp.models.templates;

import java.util.ArrayList;

public abstract class StudentMetrics<T> {
    
    protected T numero, code, firstName, secondName, gender, birthDate, birthPlace;
    protected T additional, additional1, additional2, additional3;
    protected T mark1, mark2, mark3, mark4, ai;
    protected T fullRow;
    protected int rowIndex;
    protected final ArrayList<T> childs = new ArrayList();

    public StudentMetrics() {
        rowIndex = 0;
    }
    
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public T getNumero() {
        return numero;
    }

    public void setNumero(T numero) {
        this.numero = numero;
        childs.add(numero);
    }

    public T getAdditional() {
        return additional;
    }

    public void setAdditional(T additional) {
        this.additional = additional;
        childs.add(additional);
    }

    public T getCode() {
        return code;
    }

    public void setCode(T code) {
        this.code = code;
        childs.add(code);
    }

    public T getFirstName() {
        return firstName;
    }

    public void setFirstName(T firstName) {
        this.firstName = firstName;
        childs.add(firstName);
    }

    public T getSecondName() {
        return secondName;
    }

    public void setSecondName(T secondName) {
        this.secondName = secondName;
        childs.add(secondName);
    }

    public T getGender() {
        return gender;
    }

    public void setGender(T gender) {
        this.gender = gender;
        childs.add(gender);
    }

    public T getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(T birthDate) {
        this.birthDate = birthDate;
        childs.add(birthDate);
    }

    public T getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(T birthPlace) {
        this.birthPlace = birthPlace;
        childs.add(birthPlace);
    }

    public T getAdditional1() {
        return additional1;
    }

    public void setAdditional1(T additional1) {
        this.additional1 = additional1;
        childs.add(additional1);
    }

    public T getAdditional2() {
        return additional2;
    }

    public void setAdditional2(T additional2) {
        this.additional2 = additional2;
        childs.add(additional2);
    }

    public T getAdditional3() {
        return additional3;
    }

    public void setAdditional3(T additional3) {
        this.additional3 = additional3;
        childs.add(additional3);
    }

    public T getMark1() {
        return mark1;
    }

    public void setMark1(T mark1) {
        this.mark1 = mark1;
        childs.add(mark1);
    }

    public T getMark2() {
        return mark2;
    }

    public void setMark2(T mark2) {
        this.mark2 = mark2;
        childs.add(mark2);
    }

    public T getMark3() {
        return mark3;
    }

    public void setMark3(T mark3) {
        this.mark3 = mark3;
        childs.add(mark3);
    }

    public T getMark4() {
        return mark4;
    }

    public void setMark4(T mark4) {
        this.mark4 = mark4;
        childs.add(mark4);
    }

    public T getMark(int i) {
        switch ( i ) {
            case 0:
                return mark1;
            case 1:
                return mark2;
            case 2:
                return mark3;
            case 3:
                return mark4;
        }
        return mark1;
    }

    public T getAi() {
        return ai;
    }

    public void setAi(T ai) {
        this.ai = ai;
        childs.add(ai);
    }

    public T getFullRow() {
        return fullRow;
    }

    public void setFullRow(T fullRow) {
        this.fullRow = fullRow;
        childs.add(fullRow);
    }

    public ArrayList<T> getChilds() {
        return childs;
    }
    
}
