package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.User;
import com.kosmetologistpaycalc.paycalc.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationVisit (Model model) {return "registration.html";}

    @GetMapping("/unsuccessfulregistration")
    public String unsuccessfulRegistration (Model model) {return  "unsuccessfulregistration.html";}

    @PostMapping("/registration")
    public String registrationProcess (User userBoby, Model model){
        boolean haveUser = false;

        User currentUser = new User(
                userBoby.getUserName(),
                passwordEncoder.encode(userBoby.getPassword())
        );

        Iterable<User> userList = userRepository.findAll();
        for (User user : userList){
            if(user.getUserName().equals(userBoby.getUserName())){
                haveUser = true;
            }
        }

        if (haveUser == false){
            userRepository.save(currentUser);
        }
        else {
            return "redirect:/unsuccessfulregistration";
        }
        return "redirect:/home";
    }
}
