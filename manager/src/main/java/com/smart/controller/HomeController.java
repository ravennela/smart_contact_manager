package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smart.dao.UserRepository;

@RestController
public class HomeController {
    @Autowired
    private UserRepository userRepository;

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
        return v;
    }

}
