package com.kosmetologistpaycalc.paycalc.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperationNote {

    private String day;
    private String time;
    private String name;
    private String phone;
    private String medicament;
    private Integer summury;
    private String clientName;
}
