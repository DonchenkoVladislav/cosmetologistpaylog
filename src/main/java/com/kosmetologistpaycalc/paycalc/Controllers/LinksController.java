package com.kosmetologistpaycalc.paycalc.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinksController {

    @GetMapping("/picjs")
    public String getAirDatePickerJs() {
        return "air-datepicker.js";
    }

    @GetMapping("/piccss")
    public String getAirDatePickerCss() {
        return "air_datepicker.css";
    }

}
