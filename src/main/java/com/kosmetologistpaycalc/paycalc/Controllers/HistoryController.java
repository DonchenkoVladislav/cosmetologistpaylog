package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Datecalendar;
import com.kosmetologistpaycalc.paycalc.Models.DateAndSummDate;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.text.ParseException;

@Controller
public class HistoryController extends Datecalendar {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/history")
    public String home (Principal principal, Model model) throws ParseException {

        Iterable<Post> posts = filterUser(iterableToArrayList(postRepository.findAll()));
        Iterable<String> datePosts = ListToIterable(getListPostsDate(iterableToArrayListDay(postRepository.findAll())));
        Iterable<DateAndSummDate> summPosts = ListToIterableInt(getListPostDateSumm(
                iterableToArrayList(posts),
                getListPostsDate(iterableToArrayListDay(posts))));

        model.addAttribute("posts", posts);
        model.addAttribute("datePosts", datePosts);
        model.addAttribute("summPosts", summPosts);
        return "history.html";
    }
}