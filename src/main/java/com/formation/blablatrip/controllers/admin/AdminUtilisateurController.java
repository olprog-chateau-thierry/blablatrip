package com.formation.blablatrip.controllers.admin;

import com.formation.blablatrip.entities.UtilisateurEntity;
import com.formation.blablatrip.services.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUtilisateurController extends AdminBaseController {

    private final UtilisateurService utilisateurService;

    public AdminUtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/utilisateur")
    public String index(Model model) {
        model.addAttribute("users", utilisateurService.findAll());
        model.addAttribute("content", "utilisateur/index.html");
        return "admin/index";
    }

    @GetMapping("/utilisateur/nouvel-utilisateur")
    public String nouvelUtilisateur(Model model) {
        model.addAttribute("user", new UtilisateurEntity());
        model.addAttribute("content", "utilisateur/nouvel-utilisateur.html");
        return "admin/index";
    }
}
