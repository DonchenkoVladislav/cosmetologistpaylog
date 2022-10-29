package com.kosmetologistpaycalc.paycalc.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

    @GetMapping("/")
    public String getLending (Model model){
        return "landing.html";
    }
}
