package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Models.LastIncome;
import com.kosmetologistpaycalc.paycalc.Models.SelfNotes;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class DohodsController {

    private static final String ELEMENT_NAME = "Доход";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositoryLastIncome postRepositoryCount;

    @GetMapping("/income")
    public String incomeList(Model model) {
        return "income.html";
    }

    @PostMapping("/income")
    public String postIncomeLips(@RequestParam(defaultValue = ELEMENT_NAME) String elementName0,
                                 @RequestParam(defaultValue = "0") Integer element0,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName1,
                                 @RequestParam(defaultValue = "0") Integer element1,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName2,
                                 @RequestParam(defaultValue = "0") Integer element2,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName3,
                                 @RequestParam(defaultValue = "0") Integer element3,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName4,
                                 @RequestParam(defaultValue = "0") Integer element4,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName5,
                                 @RequestParam(defaultValue = "0") Integer element5,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName6,
                                 @RequestParam(defaultValue = "0") Integer element6,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName7,
                                 @RequestParam(defaultValue = "0") Integer element7,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName8,
                                 @RequestParam(defaultValue = "0") Integer element8,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String elementName9,
                                 @RequestParam(defaultValue = "0") Integer element9,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String selfProcedure0,
                                 @RequestParam(defaultValue = "0") Integer selfIncome0,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String selfProcedure1,
                                 @RequestParam(defaultValue = "0") Integer selfIncome1,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String selfProcedure2,
                                 @RequestParam(defaultValue = "0") Integer selfIncome2,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String selfProcedure3,
                                 @RequestParam(defaultValue = "0") Integer selfIncome3,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String selfProcedure4,
                                 @RequestParam(defaultValue = "0") Integer selfIncome4,
                                 @RequestParam(defaultValue = ELEMENT_NAME) String selfProcedure5,
                                 @RequestParam(defaultValue = "0") Integer selfIncome5,
                                 Principal principal, Model model) {

        String CURRENT_USER = principal.getName();
        String INCOME = "Доход";
        SelfNotes[] notes = {new SelfNotes(elementName0, element0),
                new SelfNotes(elementName1, element1),
                new SelfNotes(elementName2, element2),
                new SelfNotes(elementName3, element3),
                new SelfNotes(elementName4, element4),
                new SelfNotes(elementName5, element5),
                new SelfNotes(elementName6, element6),
                new SelfNotes(elementName7, element7),
                new SelfNotes(elementName8, element8),
                new SelfNotes(elementName9, element9),
                new SelfNotes(selfProcedure0, selfIncome0),
                new SelfNotes(selfProcedure1, selfIncome1),
                new SelfNotes(selfProcedure2, selfIncome2),
                new SelfNotes(selfProcedure3, selfIncome3),
                new SelfNotes(selfProcedure4, selfIncome4),
                new SelfNotes(selfProcedure5, selfIncome5),
                            };

        for (SelfNotes note : notes){
            if (note.getSumm() > 0){
                String procedures = note.getName();
                String summary_type = INCOME;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = note.getSumm();
                String username = CURRENT_USER;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
        }

        return "redirect:/home";
    }
}
