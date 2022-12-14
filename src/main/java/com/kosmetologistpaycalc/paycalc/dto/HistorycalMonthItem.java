package com.kosmetologistpaycalc.paycalc.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HistorycalMonthItem {

    private String monthName;
    private Integer summury;
}
