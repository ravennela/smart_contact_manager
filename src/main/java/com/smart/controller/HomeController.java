package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ModelAndView test() {

        ModelAndView v = new ModelAndView("home");
        return v;
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {

        ModelAndView v = new ModelAndView("about");
        return v;
    }

    @GetMapping("/signup")
    public ModelAndView signUpPage() {
        ModelAndView v = new ModelAndView("signup");
        v.addObject("user", new User());
        return v;
    }

    // Handler for Registering
    @PostMapping("/do_register")
    public ModelAndView submitRegister(@Valid @ModelAttribute("user") User user, BindingResult result,
            @RequestParam(value = "aggrement", defaultValue = "false") boolean aggrement, HttpSession session) {
        ModelAndView mView = new ModelAndView("signup");
        try {
            System.out.println("Agrement" + aggrement);
            System.out.println("User " + user);
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("defualt.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (!aggrement) {
                System.out.println("Please Accept the terms and conditions");
                throw new Exception("Please Accept the terms and conditions");
            }
            if (result.hasErrors()) {
                mView.addObject("user", user);
                return mView;
            }
            User user2 = this.userRepository.save(user);
            mView.addObject("user", user2);
            System.out.println("user2" + user2);
            System.out.println("session" + session);
            return mView;

        } catch (Exception e) {

            return mView;

        }

    }

}
