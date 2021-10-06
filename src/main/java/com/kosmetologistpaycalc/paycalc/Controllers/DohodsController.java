package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Models.LastIncome;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class DohodsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositoryLastIncome postRepositoryCount;

    @GetMapping("/income")
    public String incomeList(Model model) {
        return "income.html";
    }

    @PostMapping("/income")
    public String postIncomeLips(@RequestParam(defaultValue = "0") Integer lips0, @RequestParam(defaultValue = "0") Integer botox0,
                                 @RequestParam(defaultValue = "0") Integer clean0, @RequestParam(defaultValue = "0") Integer prepayment0,
                                 @RequestParam(defaultValue = "0") Integer lips1, @RequestParam(defaultValue = "0") Integer botox1,
                                 @RequestParam(defaultValue = "0") Integer clean1, @RequestParam(defaultValue = "0") Integer prepayment1,
                                 @RequestParam(defaultValue = "0") Integer lips2, @RequestParam(defaultValue = "0") Integer botox2,
                                 @RequestParam(defaultValue = "0") Integer clean2, @RequestParam(defaultValue = "0") Integer prepayment2,
                                 @RequestParam(defaultValue = "0") Integer lips3, @RequestParam(defaultValue = "0") Integer botox3,
                                 @RequestParam(defaultValue = "0") Integer clean3, @RequestParam(defaultValue = "0") Integer prepayment3,
                                 @RequestParam(defaultValue = "0") Integer lips4, @RequestParam(defaultValue = "0") Integer botox4,
                                 @RequestParam(defaultValue = "0") Integer clean4, @RequestParam(defaultValue = "0") Integer prepayment4,
                                 @RequestParam(defaultValue = "0") Integer lips5, @RequestParam(defaultValue = "0") Integer botox5,
                                 @RequestParam(defaultValue = "0") Integer clean5, @RequestParam(defaultValue = "0") Integer prepayment5,
                                 Model model) {
        Integer[] sortmassive = {lips0, botox0, clean0, prepayment0,
                lips1, botox1, clean1, prepayment1,
                lips2, botox2, clean2, prepayment2,
                lips3, botox3, clean3, prepayment3,
                lips4, botox4, clean4, prepayment4,
                lips5, botox5, clean5, prepayment5
        };
        LastIncome[] countRecords = new LastIncome[24];
        for (int i = 0; i < sortmassive.length; i++) {
            if ((i == 0 | i == 4 | i == 8 | i == 12 | i == 16 | i == 20) & sortmassive[i] > 0) {
                String procedures = "Губы";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);

                LastIncome postWeek = new LastIncome(summary, procedures, summary_type, day);
                countRecords[i] = postWeek;
            }
            if ((i == 1 | i == 5 | i == 9 | i == 13 | i == 17 | i == 21) & sortmassive[i] > 0) {
                String procedures = "Ботокс";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);

                LastIncome postWeek = new LastIncome(summary, procedures, summary_type, day);
                    countRecords[i] = postWeek;
            }
            if ((i == 2 | i == 6 | i == 10 | i == 14 | i == 18 | i == 22) & sortmassive[i] > 0) {
                String procedures = "Чистка";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);

                LastIncome postWeek = new LastIncome(summary, procedures, summary_type, day);
                    countRecords[i] = postWeek;
            }
            if ((i == 3 | i == 7 | i == 11 | i == 15 | i == 19 | i == 23) & sortmassive[i] > 0) {
                String procedures = "Предоплата";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);

                LastIncome postWeek = new LastIncome(summary, procedures, summary_type, day);
                    countRecords[i] = postWeek;
            }
        }
        postRepositoryCount.deleteAll();
        for (int i = 0; i < countRecords.length; i++) {
            if (countRecords[i] != null) {
                postRepositoryCount.save(countRecords[i]);
            }
        }
        return "redirect:/";
    }
}
