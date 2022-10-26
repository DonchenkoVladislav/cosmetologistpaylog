package com.kosmetologistpaycalc.paycalc.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DayInfo {

    private String date;
    private String countNotes;
    private List<OperationNote> operationNotes;
}
