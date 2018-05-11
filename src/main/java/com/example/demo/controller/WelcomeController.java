package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping("/index")
    public ModelAndView welcome(){
        return new ModelAndView("index");
    }
    @GetMapping("/login")
    public ModelAndView toLogin(){
        return new ModelAndView("login" );
    }
}
