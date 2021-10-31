package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Datecalendar;
import com.kosmetologistpaycalc.paycalc.Models.DateAndSummDate;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastExpenses;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController extends Datecalendar {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/home")
    public String home (Model model) throws ParseException {

        Iterable<Post> usersPost = filterUser(iterableToArrayList(postRepository.findAll()));
        ArrayList<Post> usersPostList = iterableToArrayList(filterUser(iterableToArrayList(postRepository.findAll())));
        List<String> uniqueDate = getListPostsDateMouth(iterableToArrayListMouth(usersPost));

        Iterable<Post> lastPosts = getLastPosts(usersPost);
        String curentMouthSumm = getCurrentMounthSummary(usersPostList);
        String curentMouthSummPlus = getCurrentMounthSummaryPlus(usersPostList);
        String curentMouthSummMinus = getCurrentMounthSummaryMinus(usersPostList);
        Iterable<DateAndSummDate> mouthCalendar = ListToIterableInt(
                getHomeCalendar(usersPostList, uniqueDate));

        model.addAttribute("title", "Kosmetologist calc");
        model.addAttribute("lastPosts", lastPosts);
        model.addAttribute("curentMouthSumm", curentMouthSumm);
        model.addAttribute("curentMouthSummPlus", curentMouthSummPlus);
        model.addAttribute("curentMouthSummMinus", curentMouthSummMinus);
        model.addAttribute("mouthCalendar", mouthCalendar);

        return "home.html";
    }
}