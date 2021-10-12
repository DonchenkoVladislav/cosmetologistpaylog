package com.kosmetologistpaycalc.paycalc.Models;

import com.kosmetologistpaycalc.paycalc.DateParser;

import java.text.ParseException;

public class DateAndSummDate extends DateParser {

    private String day;
    private String mounth;
    private String mounthYear;
    private Integer summary;

    public DateAndSummDate(Integer summary, String day) throws ParseException {
        this.summary = summary;
        this.day = day;

        // Выводит месяц (только если day в формате MM.yyyy)
        // Выводит месяц и год (только если day в формате MM.yyyy)
        this.mounth = parserToMouth(day);
        this.mounthYear = parserToMouthYear(day);
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public DateAndSummDate() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMounth() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth = mounth;
    }

    public String getMounthYear() {
        return mounthYear;
    }

    public void setMounthYear(String mounthYear) {
        this.mounthYear = mounthYear;
    }
}
