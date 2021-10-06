package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Datecalendar;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController extends Datecalendar {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/history")
    public String home (Model model) {
        model.addAttribute("title", "Kosmetologist calc");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        Iterable<String> datePosts = ListToIterable(getListPostsDate(iterableToArrayListDay(postRepository.findAll())));
        model.addAttribute("datePosts", datePosts);

        return "history.html";
    }
}