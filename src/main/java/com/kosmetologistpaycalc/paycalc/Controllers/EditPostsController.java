package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Datecalendar;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class EditPostsController extends Datecalendar {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/editposts")
    public String editPosts (Model model){
        Iterable<Post> usersPost = filterUser(iterableToArrayList(postRepository.findAll()));

        Iterable<Post> lastPosts = getLastPosts(usersPost, 10);
        model.addAttribute("lastPosts", lastPosts);
        return "editposts.html";
    }

    @PostMapping("/editposts")
    public String homeEdit (@RequestParam(defaultValue = "1") Long editeId, @RequestParam(defaultValue = "0") int editeSummary,
                            Model model) {
        Optional<Post> oldPost = postRepository.findById(editeId);
        if (oldPost.get().getSummary() != editeSummary & editeSummary != 0){
            Post editPost = new Post(editeSummary, oldPost.get().getProcedures(), oldPost.get().getSummary_type(), oldPost.get().getDay(),
                                     oldPost.get().getUsername());
            if (oldPost.get().getSummary_type().equals("Расход") & editeSummary > 0){
                editPost.setSummary(-editeSummary);
            }
            postRepository.save(editPost);
            postRepository.deleteById(editeId);
        }
        if (editeSummary == 0){
            postRepository.deleteById(editeId);
        }
    return "redirect:/editposts";
    }

//    @DeleteMapping("/editposts")
//    public  String homeDelete (Post post, Model model) {
//        postRepository.deleteById(post.getId());
//        return "redirect:/editposts";
//    }
}
