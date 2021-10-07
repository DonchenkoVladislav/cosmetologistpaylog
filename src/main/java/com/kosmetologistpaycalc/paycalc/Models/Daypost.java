package com.kosmetologistpaycalc.paycalc.Models;

public class Daypost {

    private String day;
    private Integer summary;

    public Daypost(Integer summary, String day) {
        this.summary = summary;
        this.day = day;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public Daypost() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
