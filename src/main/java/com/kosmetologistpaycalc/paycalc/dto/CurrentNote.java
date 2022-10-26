package com.kosmetologistpaycalc.paycalc.dto;

import com.kosmetologistpaycalc.paycalc.Models.Operation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CurrentNote {

    private String day;
    private String timeProperty;
    private String countDaysNotes;
}
