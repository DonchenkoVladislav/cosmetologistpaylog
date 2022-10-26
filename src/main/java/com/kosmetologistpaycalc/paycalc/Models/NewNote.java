package com.kosmetologistpaycalc.paycalc.Models;


import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewNote {

    private String noteData;
    private String notePhone;
    private String noteComment;

    private LocalDate noteDate;
    private LocalDate noteTime;
    private String noteOperation;
    private String noteMedication;
    private Integer noteIncome;

    public NewNote() {}
}
