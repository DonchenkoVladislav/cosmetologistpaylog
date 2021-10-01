package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Models.LastIncome;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepositoryLastIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositoryLastIncome postRepositoryCount;

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "Kosmetologist calc");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        Iterable<LastIncome> postWeeks = postRepositoryCount.findAll();
        model.addAttribute("postWeeks", postWeeks);
        return "home.html";
    }
}