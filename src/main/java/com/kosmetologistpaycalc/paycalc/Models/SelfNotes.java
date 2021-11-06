package com.kosmetologistpaycalc.paycalc.Models;


public class SelfNotes {
    String name;
    int summ;

    public SelfNotes(String name, int summ) {
        this.name = name;
        this.summ = summ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }
}
