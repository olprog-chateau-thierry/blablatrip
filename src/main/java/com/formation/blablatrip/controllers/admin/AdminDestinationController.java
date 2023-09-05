package com.formation.blablatrip.controllers.admin;

import com.formation.blablatrip.entities.DestinationEntity;
import com.formation.blablatrip.entities.UtilisateurEntity;
import com.formation.blablatrip.services.DestinationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@Slf4j
public class AdminDestinationController extends AdminBaseController {

    private final DestinationService destinationService;

    public AdminDestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destination")
    public String index(Model model) {
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("content", "destination/index.html");
        return "admin/index";
    }

    @GetMapping("/destination/nouvelle-destination")
    public String newGet(Model model) {
        model.addAttribute("dest", new DestinationEntity());
        model.addAttribute("content", "destination/newDest.html");
        return "admin/index";
    }

    @PostMapping("/destination/nouvelle-destination")
    public String newPost(
            Model model,
            @Valid @ModelAttribute(name = "dest") DestinationEntity destination,
            BindingResult binding,
            RedirectAttributes redirectAttributes
    ) {
        if (binding.hasErrors()) {
            model.addAttribute("content", "destination/newDest.html");
            return "admin/index";
        }

        try {
            destinationService.save(destination);
        } catch (Exception e) {
            model.addAttribute("flashMessage", e.getMessage());
            log.error("{}", e.getMessage());
            model.addAttribute("content", "destination/newDest.html");
            return "admin/index";
        }
        redirectAttributes.addFlashAttribute("flashMessage", "Enregistrement de la destination ok!.");
        return "redirect:/admin/destination";
    }

    @GetMapping("/destination/delete/{id}")
    public String delete(@PathVariable Long id) {
        destinationService.deleteById(id);
        return "redirect:/admin/destination";
    }


    @GetMapping("/destination/detail/{id}")
    public String detail(@PathVariable Long id,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        try {
            Optional<DestinationEntity> destination = destinationService.findById(id);
            if (destination.isPresent()) {
                model.addAttribute("dest", destination.get());
                model.addAttribute("content", "destination/detail.html");
                return "admin/index";
            } else {
                // flashmessage
                redirectAttributes.addFlashAttribute("flashMessage", "Pas de destinations à l'id : " + id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/destination";
    }


    @GetMapping("/destination/edition/{id}")
    public String getEdition(
            @PathVariable Long id,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            Optional<DestinationEntity> destination = destinationService.findById(id);
            if (destination.isPresent()) {
                model.addAttribute("dest", destination.get());
                model.addAttribute("content", "destination/edition.html");
                return "admin/index";
            } else {
                // erreur
                redirectAttributes.addFlashAttribute("flashMessage", "Cette destination n'existe pas.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }
        return "redirect:/admin/destination";
    }

    @PostMapping("/destination/edition/{id}")
    public String postEdition(@PathVariable Long id,
                              @Valid @ModelAttribute("dest") DestinationEntity destination,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "destination/edition.html");
            return "admin/index";
        }

        try {
            destinationService.save(destination);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/destination";
    }
}
