package com.kosmetologistpaycalc.paycalc.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LastIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String procedures, summary_type, day;
    private Integer summary;

    public LastIncome(Integer summary, String procedures, String summary_type, String day) {
        this.procedures = procedures;
        this.summary_type = summary_type;
        this.summary = summary;
        this.day = day;
    }

    public String getSummary_type() {
        return summary_type;
    }

    public void setSummary_type(String summary_type) {
        this.summary_type = summary_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public LastIncome() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
