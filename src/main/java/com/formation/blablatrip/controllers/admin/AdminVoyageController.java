package com.formation.blablatrip.controllers.admin;

import com.formation.blablatrip.entities.VoyageEntity;
import com.formation.blablatrip.services.DestinationService;
import com.formation.blablatrip.services.VoyageService;
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
public class AdminVoyageController extends AdminBaseController {

    private final VoyageService voyageService;
    private final DestinationService destinationService;

    public AdminVoyageController(VoyageService voyageService, DestinationService destinationService) {
        this.voyageService = voyageService;
        this.destinationService = destinationService;
    }

    @GetMapping("/voyage")
    public String index(Model model) {
        model.addAttribute("voyages", voyageService.findAll());
        model.addAttribute("content", "voyage/index.html");
        return "admin/index";
    }

    @GetMapping("/voyage/nouveau-voyage")
    public String newGet(Model model) {
        model.addAttribute("voyage", new VoyageEntity());
        model.addAttribute("destinations", destinationService.findAll());
        model.addAttribute("content", "voyage/nouveauVoyage.html");
        return "admin/index";
    }

    @PostMapping("/voyage/nouveau-voyage")
    public String newPost(
            Model model,
            @Valid @ModelAttribute(name = "voyage") VoyageEntity voyage,
            BindingResult binding,
            RedirectAttributes redirectAttributes
    ) {
        if (binding.hasErrors()) {
            model.addAttribute("destinations", destinationService.findAll());
            model.addAttribute("content", "voyage/nouveauVoyage.html");
            return "admin/index";
        }

        try {
            voyageService.save(voyage);
        } catch (Exception e) {
            model.addAttribute("flashMessage", e.getMessage());
            log.error("{}", e.getMessage());
            model.addAttribute("destinations", destinationService.findAll());
            model.addAttribute("content", "voyage/nouveauVoyage.html");
            return "admin/index";
        }
        redirectAttributes.addFlashAttribute("flashMessage", "Enregistrement du voyage ok!.");
        return "redirect:/admin/voyage";
    }

    @GetMapping("/voyage/delete/{id}")
    public String delete(@PathVariable Long id) {
        voyageService.deleteById(id);
        return "redirect:/admin/voyage";
    }


    @GetMapping("/voyage/detail/{id}")
    public String detail(@PathVariable Long id,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        try {
            Optional<VoyageEntity> voyage = voyageService.findById(id);
            if (voyage.isPresent()) {
                model.addAttribute("voyage", voyage.get());
                model.addAttribute("content", "voyage/detail.html");
                return "admin/index";
            } else {
                // flashmessage
                redirectAttributes.addFlashAttribute("flashMessage", "Pas de voyages à l'id : " + id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/voyage";
    }


    @GetMapping("/voyage/edition/{id}")
    public String getEdition(
            @PathVariable Long id,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            Optional<VoyageEntity> voyage = voyageService.findById(id);
            model.addAttribute("destinations", destinationService.findAll());
            if (voyage.isPresent()) {
                model.addAttribute("voyage", voyage.get());
                model.addAttribute("content", "voyage/edition.html");
                return "admin/index";
            } else {
                // erreur
                redirectAttributes.addFlashAttribute("flashMessage", "Ce voyage n'existe pas.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }
        return "redirect:/admin/voyage";
    }

    @PostMapping("/voyage/edition/{id}")
    public String postEdition(@PathVariable Long id,
                              @Valid @ModelAttribute("voyage") VoyageEntity voyage,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        model.addAttribute("destinations", destinationService.findAll());
        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "voyage/edition.html");
            return "admin/index";
        }

        try {
            voyageService.save(voyage);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
        }

        return "redirect:/admin/voyage";
    }
}
