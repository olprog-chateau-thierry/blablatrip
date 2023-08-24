package com.formation.blablatrip.controllers.admin;

import com.formation.blablatrip.entities.UtilisateurEntity;
import com.formation.blablatrip.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
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

    @PostMapping("/utilisateur/nouvel-utilisateur")
    public String postNouvelUtilisateur(
            Model model,
            @Valid @ModelAttribute(name = "user") UtilisateurEntity utilisateur,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors() || !utilisateur.getMdp().equals(utilisateur.getVerifMdp())) {
            model.addAttribute("content", "utilisateur/nouvel-utilisateur.html");
            return "admin/index";
        }
        // TODO: chiffré le mot de passe
        // TODO: save notre utilisateur
        log.info("Utilisateur prêt à être sauvegardé : {}", utilisateur);
        return "redirect:/admin/utilisateur";
    }
}
