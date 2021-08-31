package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
public class MainController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("title", "Kosmetologist calc");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home.html";
    }

    @PostMapping("/income")
    public String postIncomeLips(@RequestParam(defaultValue = "0") Integer lips, @RequestParam(defaultValue = "0") Integer botox, @RequestParam(defaultValue = "0") Integer clean, @RequestParam(defaultValue = "0") Integer prepayment, Model model){
        if(lips>0){
            String procedures = "Губы";
            String summary_type = "Доход";
            String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
            Integer summary = lips;
            Post post = new Post(summary, procedures, summary_type, day);
            postRepository.save(post);
        }
        if(botox>0){
            String procedures = "Ботокс";
            String summary_type = "Доход";
            String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
            Integer summary = botox;
            Post post = new Post(summary, procedures, summary_type, day);
            postRepository.save(post);
        }
        if(clean>0){
            String procedures = "Чистка";
            String summary_type = "Доход";
            String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
            Integer summary = clean;
            Post post = new Post(summary, procedures, summary_type, day);
            postRepository.save(post);
        }
        if(prepayment>0){
            String procedures = "Предоплата";
            String summary_type = "Доход";
            String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
            Integer summary = prepayment;
            Post post = new Post(summary, procedures, summary_type, day);
            postRepository.save(post);
        }

        return "redirect:/";
    }


}