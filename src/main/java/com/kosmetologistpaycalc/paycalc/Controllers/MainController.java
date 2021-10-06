package com.kosmetologistpaycalc.paycalc.Controllers;

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

@Controller
public class MainController {

    @Autowired
    private PostRepositoryLastIncome postRepositoryCount;

    @Autowired
    private PostRepositoryLastExpenses expenses;

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "Kosmetologist calc");
        Iterable<LastIncome> lastIncomes = postRepositoryCount.findAll();
        model.addAttribute("lastIncomes", lastIncomes);
        Iterable<LastExpenses> lastExpenses = expenses.findAll();
        model.addAttribute("lastExpenses", lastExpenses);
        return "home.html";
    }
}