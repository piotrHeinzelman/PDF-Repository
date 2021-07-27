package com.heinzelman.pdf_repo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","","/index","index"})
    public String homeController( Model model){
        model.addAttribute("error","Error !");
        model.addAttribute("success","Success !");
        return "index";
    }

}
