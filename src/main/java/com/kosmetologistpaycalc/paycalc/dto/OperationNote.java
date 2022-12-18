package com.kosmetologistpaycalc.paycalc.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OperationNote {

    private String day;
    private String time;
    private String duration;
    private List<String> durationList;
    private String name;
    private String phone;
    private String medicament;
    private Integer summury;
    private String clientName;
    private String comment;
    private Long clientId;
    private Long id;
}
