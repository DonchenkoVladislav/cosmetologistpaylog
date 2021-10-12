package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Datecalendar;
import com.kosmetologistpaycalc.paycalc.Models.DateAndSummDate;
import com.kosmetologistpaycalc.paycalc.Models.LastExpenses;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Models.LastIncome;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastExpenses;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
public class MainController extends Datecalendar {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositoryLastIncome postRepositoryCount;

    @Autowired
    private PostRepositoryLastExpenses expenses;

    @GetMapping("/")
    public String home (Model model) throws ParseException {
        Iterable<LastIncome> lastIncomes = postRepositoryCount.findAll();
        Iterable<LastExpenses> lastExpenses = expenses.findAll();
        String curentMouthSumm = getCurrentMounthSummary(iterableToArrayListSumm(postRepository.findAll()));
        String curentMouthSummPlus = getCurrentMounthSummaryPlus(iterableToArrayListSumm(postRepository.findAll()));
        String curentMouthSummMinus = getCurrentMounthSummaryMinus(iterableToArrayListSumm(postRepository.findAll()));
        Iterable<DateAndSummDate> mouthCalendar = ListToIterableInt(getHomeCalendar(
                iterableToArrayListSumm(postRepository.findAll()),
                getListPostsDateMouth(iterableToArrayListMouth(postRepository.findAll()))));

        model.addAttribute("title", "Kosmetologist calc");
        model.addAttribute("lastIncomes", lastIncomes);
        model.addAttribute("lastExpenses", lastExpenses);
        model.addAttribute("curentMouthSumm", curentMouthSumm);
        model.addAttribute("curentMouthSummPlus", curentMouthSummPlus);
        model.addAttribute("curentMouthSummMinus", curentMouthSummMinus);
        model.addAttribute("mouthCalendar", mouthCalendar);

        return "home.html";
    }
}