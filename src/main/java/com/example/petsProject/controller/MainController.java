package com.example.petsProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping("/")
    public String main(){
        return "index";
    }

    @RequestMapping("/interceptorTest")
    public String test(){
        return "securityTest";
    }


}
