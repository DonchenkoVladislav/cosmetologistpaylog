package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Datecalendar;
import com.kosmetologistpaycalc.paycalc.Models.DateAndSummDate;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/home")
    public String homeEdit (@RequestParam(defaultValue = "1") Long editeId, @RequestParam(defaultValue = "0") int editeSummary,
                            Model model) {
        Post oldPost = postRepository.findById(editeId).get();
        if (oldPost.getSummary() != editeSummary){
            Post editPost = new Post(editeSummary, oldPost.getProcedures(), oldPost.getSummary_type(), oldPost.getDay(), oldPost.getUsername());
            postRepository.save(editPost);
            postRepository.deleteById(editeId);
        }
    return "redirect:/home";
    }

    @DeleteMapping("/home")
    public  String homeDelete (Post post, Model model) {
        postRepository.deleteById(post.getId());
        return "redirect:/home";
    }
}