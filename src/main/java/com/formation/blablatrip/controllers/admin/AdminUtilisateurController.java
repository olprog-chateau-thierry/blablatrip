package com.formation.blablatrip.controllers.admin;

import com.formation.blablatrip.entities.UtilisateurEntity;
import com.formation.blablatrip.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.oauth2.login.UserInfoEndpointDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

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
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors() || !utilisateur.getMdp().equals(utilisateur.getVerifMdp())) {
            model.addAttribute("content", "utilisateur/nouvel-utilisateur.html");
            model.addAttribute("errorSamePwd", "Les mots de passe ne sont pas identiques!!");
            return "admin/index";
        }
        log.info("Utilisateur prêt à être sauvegardé : {}", utilisateur);
        // TODO: chiffré le mot de passe
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPassword = bCryptPasswordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(hashPassword);

        log.info("Utilisateur prêt à être sauvegardé : {}", utilisateur);
        // TODO: save notre utilisateur
        try {
            utilisateur.setDateInscription(LocalDateTime.now());
            utilisateurService.save(utilisateur, false);
        } catch (Exception e) {
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/utilisateur";
    }

    @GetMapping("/utilisateur/delete/{id}")
    public String delete(@PathVariable Long id) {
        utilisateurService.deleteById(id);
        return "redirect:/admin/utilisateur";
    }


    @GetMapping("/utilisateur/detail/{id}")
    public String detail(@PathVariable Long id,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        try {
            Optional<UtilisateurEntity> utilisateur = utilisateurService.findById(id);
            if (utilisateur.isPresent()) {
                // model
                utilisateur.get().setMdp(null);
                model.addAttribute("user", utilisateur.get());
                model.addAttribute("content", "utilisateur/detail.html");
                return "admin/index";
            } else {
                // flashmessage
                redirectAttributes.addFlashAttribute("flashMessage", "Pas d'utilisateur à l'id : " + id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/utilisateur";
    }


    @GetMapping("/utilisateur/edition/{id}")
    public String getEdition(
            @PathVariable Long id,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            Optional<UtilisateurEntity> utilisateur = utilisateurService.findById(id);
            if (utilisateur.isPresent()) {
                session.setAttribute("user", utilisateur.get());
                // model
//                utilisateur.get().setMdp(null);
                model.addAttribute("user", utilisateur.get());
                model.addAttribute("content", "utilisateur/edition.html");
                return "admin/index";
            } else {
                // erreur
                redirectAttributes.addFlashAttribute("flashMessage", "Cet utilisateur n'existe pas.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }
        return "redirect:/admin/utilisateur";
    }

    @PostMapping("/utilisateur/edition/{id}")
    public String postEdition(@PathVariable Long id,
                              @Valid @ModelAttribute("user") UtilisateurEntity utilisateur,
                              BindingResult bindingResult,
                              Model model,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        UtilisateurEntity userSession = (UtilisateurEntity) session.getAttribute("user");

        if (
                bindingResult.hasFieldErrors("nom")
                        || bindingResult.hasFieldErrors("prenom")
                        || bindingResult.hasFieldErrors("email")
                        || bindingResult.hasFieldErrors("type")

        ) {
            model.addAttribute("content", "utilisateur/edition.html");
            return "admin/index";
        }

        utilisateur.setMdp(userSession.getMdp());
        utilisateur.setDateInscription(userSession.getDateInscription());
        utilisateur.setDateDesinscription(userSession.getDateDesinscription());

        try {
            utilisateurService.save(utilisateur, true);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/utilisateur";
    }

}
