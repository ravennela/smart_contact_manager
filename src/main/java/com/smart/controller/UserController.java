package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class UserController {
    @RequestMapping("/index")
    public ModelAndView dashborAndView() {
        System.out.println("user called");
        ModelAndView mView = new ModelAndView("normal/welcome");
        return mView;
    }
}
