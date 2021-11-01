package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.LastExpenses;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastExpenses;
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
public class ExpensesController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositoryLastExpenses expenses;

    @GetMapping("/expenses")
    public String incomeList(Model model) {
        return "expenses.html";
    }

    @PostMapping("/expenses")
    public String postIncomeLips(@RequestParam(defaultValue = "0") Integer prep10, @RequestParam(defaultValue = "0") Integer prep20,
                                 @RequestParam(defaultValue = "0") Integer prep30, @RequestParam(defaultValue = "0") Integer prepayment0,
                                 @RequestParam(defaultValue = "0") Integer prep11, @RequestParam(defaultValue = "0") Integer prep21,
                                 @RequestParam(defaultValue = "0") Integer prep31, @RequestParam(defaultValue = "0") Integer prepayment1,
                                 @RequestParam(defaultValue = "0") Integer prep12, @RequestParam(defaultValue = "0") Integer prep22,
                                 @RequestParam(defaultValue = "0") Integer prep32, @RequestParam(defaultValue = "0") Integer prepayment2,
                                 @RequestParam(defaultValue = "0") Integer prep13, @RequestParam(defaultValue = "0") Integer prep23,
                                 @RequestParam(defaultValue = "0") Integer prep33, @RequestParam(defaultValue = "0") Integer prepayment3,
                                 @RequestParam(defaultValue = "0") Integer prep14, @RequestParam(defaultValue = "0") Integer prep24,
                                 @RequestParam(defaultValue = "0") Integer prep34, @RequestParam(defaultValue = "0") Integer prepayment4,
                                 @RequestParam(defaultValue = "0") Integer prep15, @RequestParam(defaultValue = "0") Integer prep25,
                                 @RequestParam(defaultValue = "0") Integer prep35, @RequestParam(defaultValue = "0") Integer prepayment5,
                                 Principal principal, Model model) {

        String currentUsername = principal.getName();
        String typeOfSummary = "Расход";

        Integer[] sortmassive = {prep10, prep20, prep30, prepayment0,
                                prep11, prep21, prep31, prepayment1,
                                prep12, prep22, prep32, prepayment2,
                                prep13, prep23, prep33, prepayment3,
                                prep14, prep24, prep34, prepayment4,
                                prep15, prep25, prep35, prepayment5,
                                };
        LastExpenses[] countRecords = new LastExpenses[24];
        for (int i = 0; i < sortmassive.length; i++) {
            if ((i == 0 | i == 4 | i == 8 | i == 12 | i == 16 | i == 20) & sortmassive[i] > 0) {
                String procedures = "Stylage L";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);

                LastExpenses postWeek = new LastExpenses(summary, procedures, summary_type, day);
                countRecords[i] = postWeek;
            }
            if ((i == 1 | i == 5 | i == 9 | i == 13 | i == 17 | i == 21) & sortmassive[i] > 0) {
                String procedures = "Hyafilia";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);

                LastExpenses postWeek = new LastExpenses(summary, procedures, summary_type, day);
                countRecords[i] = postWeek;
            }
            if ((i == 2 | i == 6 | i == 10 | i == 14 | i == 18 | i == 22) & sortmassive[i] > 0) {
                String procedures = "Juvederm 4";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);

                LastExpenses postWeek = new LastExpenses(summary, procedures, summary_type, day);
                countRecords[i] = postWeek;
            }
            if ((i == 3 | i == 7 | i == 11 | i == 15 | i == 19 | i == 23) & sortmassive[i] > 0) {
                String procedures = "Расходники";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);

                LastExpenses postWeek = new LastExpenses(summary, procedures, summary_type, day);
                countRecords[i] = postWeek;
            }
        }
        expenses.deleteAll();
        for (int i = 0; i < countRecords.length; i++) {
            if (countRecords[i] != null) {
                expenses.save(countRecords[i]);
            }
        }
        return "redirect:/home";
    }
}
