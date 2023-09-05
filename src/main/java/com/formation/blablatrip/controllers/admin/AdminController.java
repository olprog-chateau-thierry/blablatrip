package com.formation.blablatrip.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController extends AdminBaseController{

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("content", "accueil.html");
        return "admin/index";
    }

}
