package com.kosmetologistpaycalc.paycalc.Models;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Getter
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long clientId;

    private Calendar date;
    private String name;
    private String medicament;
    private Integer summury;

    private String userName;

    public Operation() {}

    public Operation(Long clientId, Calendar date, String name, String medicament, Integer summury, String userName) {
        this.clientId = clientId;
        this.date = date;
        this.name = name;
        this.medicament = medicament;
        this.summury = summury;
        this.userName = userName;
    }
}
