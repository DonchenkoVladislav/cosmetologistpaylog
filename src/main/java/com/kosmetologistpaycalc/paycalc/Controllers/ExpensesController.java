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

    @GetMapping("/expenses")
    public String incomeList(Model model) {
        return "expenses.html";
    }

    @PostMapping("/expenses")
    public String postIncomeLips(@RequestParam(defaultValue = "0") Integer prep1_0, @RequestParam(defaultValue = "0") Integer prep1_1,
                                 @RequestParam(defaultValue = "0") Integer prep1_2, @RequestParam(defaultValue = "0") Integer prep1_3,
                                 @RequestParam(defaultValue = "0") Integer prep1_4, @RequestParam(defaultValue = "0") Integer prep1_5,

                                 @RequestParam(defaultValue = "0") Integer prep2_0, @RequestParam(defaultValue = "0") Integer prep2_1,
                                 @RequestParam(defaultValue = "0") Integer prep2_2, @RequestParam(defaultValue = "0") Integer prep2_3,
                                 @RequestParam(defaultValue = "0") Integer prep2_4, @RequestParam(defaultValue = "0") Integer prep2_5,

                                 @RequestParam(defaultValue = "0") Integer prep3_0, @RequestParam(defaultValue = "0") Integer prep3_1,
                                 @RequestParam(defaultValue = "0") Integer prep3_2, @RequestParam(defaultValue = "0") Integer prep3_3,
                                 @RequestParam(defaultValue = "0") Integer prep3_4, @RequestParam(defaultValue = "0") Integer prep3_5,

                                 @RequestParam(defaultValue = "0") Integer prep4_0, @RequestParam(defaultValue = "0") Integer prep4_1,
                                 @RequestParam(defaultValue = "0") Integer prep4_2, @RequestParam(defaultValue = "0") Integer prep4_3,
                                 @RequestParam(defaultValue = "0") Integer prep4_4, @RequestParam(defaultValue = "0") Integer prep4_5,

                                 @RequestParam(defaultValue = "0") Integer prep5_0, @RequestParam(defaultValue = "0") Integer prep5_1,
                                 @RequestParam(defaultValue = "0") Integer prep5_2, @RequestParam(defaultValue = "0") Integer prep5_3,
                                 @RequestParam(defaultValue = "0") Integer prep5_4, @RequestParam(defaultValue = "0") Integer prep5_5,

                                 @RequestParam(defaultValue = "0") Integer prep6_0, @RequestParam(defaultValue = "0") Integer prep6_1,
                                 @RequestParam(defaultValue = "0") Integer prep6_2, @RequestParam(defaultValue = "0") Integer prep6_3,
                                 @RequestParam(defaultValue = "0") Integer prep6_4, @RequestParam(defaultValue = "0") Integer prep6_5,

                                 @RequestParam(defaultValue = "0") Integer prep7_0, @RequestParam(defaultValue = "0") Integer prep7_1,
                                 @RequestParam(defaultValue = "0") Integer prep7_2, @RequestParam(defaultValue = "0") Integer prep7_3,
                                 @RequestParam(defaultValue = "0") Integer prep7_4, @RequestParam(defaultValue = "0") Integer prep7_5,

                                 @RequestParam(defaultValue = "0") Integer prep8_0, @RequestParam(defaultValue = "0") Integer prep8_1,
                                 @RequestParam(defaultValue = "0") Integer prep8_2, @RequestParam(defaultValue = "0") Integer prep8_3,
                                 @RequestParam(defaultValue = "0") Integer prep8_4, @RequestParam(defaultValue = "0") Integer prep8_5,

                                 @RequestParam(defaultValue = "0") Integer prep9_0, @RequestParam(defaultValue = "0") Integer prep9_1,
                                 @RequestParam(defaultValue = "0") Integer prep9_2, @RequestParam(defaultValue = "0") Integer prep9_3,
                                 @RequestParam(defaultValue = "0") Integer prep9_4, @RequestParam(defaultValue = "0") Integer prep9_5,

                                 @RequestParam(defaultValue = "0") Integer prep10_0, @RequestParam(defaultValue = "0") Integer prep10_1,
                                 @RequestParam(defaultValue = "0") Integer prep10_2, @RequestParam(defaultValue = "0") Integer prep10_3,
                                 @RequestParam(defaultValue = "0") Integer prep10_4, @RequestParam(defaultValue = "0") Integer prep10_5,

                                 @RequestParam(defaultValue = "0") Integer prep11_0, @RequestParam(defaultValue = "0") Integer prep11_1,
                                 @RequestParam(defaultValue = "0") Integer prep11_2, @RequestParam(defaultValue = "0") Integer prep11_3,
                                 @RequestParam(defaultValue = "0") Integer prep11_4, @RequestParam(defaultValue = "0") Integer prep11_5,
                                 Principal principal, Model model) {

        String currentUsername = principal.getName();
        String typeOfSummary = "Расход";

        Integer[] sortmassive = {prep1_0, prep1_1, prep1_2, prep1_3, prep1_4, prep1_5,
                                prep2_0, prep2_1, prep2_2, prep2_3, prep2_4, prep2_5,
                                prep3_0, prep3_1, prep3_2, prep3_3, prep3_4, prep3_5,
                                prep4_0, prep4_1, prep4_2, prep4_3, prep4_4, prep4_5,
                                prep5_0, prep5_1, prep5_2, prep5_3, prep5_4, prep5_5,
                                prep6_0, prep6_1, prep6_2, prep6_3, prep6_4, prep6_5,
                                prep7_0, prep7_1, prep7_2, prep7_3, prep7_4, prep7_5,
                                prep8_0, prep8_1, prep8_2, prep8_3, prep8_4, prep8_5,
                                prep9_0, prep9_1, prep9_2, prep9_3, prep9_4, prep9_5,
                                prep10_0, prep10_1, prep10_2, prep10_3, prep10_4, prep10_5,
                                prep11_0, prep11_1, prep11_2, prep11_3, prep11_4, prep11_5,
                                };
        LastExpenses[] countRecords = new LastExpenses[24];
        for (int i = 0; i < sortmassive.length; i++) {
            if (i >= 0 & i <= 5 & sortmassive[i] > 0) {
                String procedures = "Stylage L";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 6 & i <= 11 & sortmassive[i] > 0) {
                String procedures = "Stylage M";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 12 & i <= 17 & sortmassive[i] > 0) {
                String procedures = "Hyafilia";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 18 & i <= 23 & sortmassive[i] > 0) {
                String procedures = "Princess Filler";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);

            }
            if (i >= 24 & i <= 29 & sortmassive[i] > 0) {
                String procedures = "Princess Volume";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 30 & i <= 35 & sortmassive[i] > 0) {
                String procedures = "Juvederm 3";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 36 & i <= 41 & sortmassive[i] > 0) {
                String procedures = "Juvederm 4";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 42 & i <= 47 & sortmassive[i] > 0) {
                String procedures = "Juvederm Ultra Smile";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 48 & i <= 53 & sortmassive[i] > 0) {
                String procedures = "Belotero Balance";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 54 & i <= 59 & sortmassive[i] > 0) {
                String procedures = "Аренда";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
            if (i >= 60 & i <= 65 & sortmassive[i] > 0) {
                String procedures = "Расходники";
                String summary_type = typeOfSummary;
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = -sortmassive[i];
                String username = currentUsername;
                Post post = new Post(summary, procedures, summary_type, day, username);
                postRepository.save(post);
            }
        }
        return "redirect:/home";
    }
}
