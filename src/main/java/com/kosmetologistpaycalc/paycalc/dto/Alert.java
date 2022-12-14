package com.kosmetologistpaycalc.paycalc.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Alert {
    String info;
    String title;
    String subtitle;
}
