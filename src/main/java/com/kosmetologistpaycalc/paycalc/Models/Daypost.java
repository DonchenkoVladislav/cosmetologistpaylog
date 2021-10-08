package com.kosmetologistpaycalc.paycalc.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Daypost {

    private Date date;
    private Integer summary;
    private String day;

    public Daypost(Integer summary, Date date) {
        this.summary = summary;
        this.date = date;
        this.day = new SimpleDateFormat("dd.MM.yyyy").format(date);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
