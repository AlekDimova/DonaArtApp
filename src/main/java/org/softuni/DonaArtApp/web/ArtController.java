package org.softuni.DonaArtApp.web;

import jakarta.validation.Valid;
import org.softuni.DonaArtApp.config.MailConfiguration;
import org.softuni.DonaArtApp.model.dto.ArtDetailDTO;
import org.softuni.DonaArtApp.model.dto.CreateArtDTO;
import org.softuni.DonaArtApp.model.enums.StyleEnum;
import org.softuni.DonaArtApp.service.ArtService;
import org.softuni.DonaArtApp.service.CategoryService;
import org.softuni.DonaArtApp.service.exceptions.ObjectNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;


@Controller
@RequestMapping("/art")
public class ArtController {

    private final ArtService artService;
    private final CategoryService categoryService;
    private final MailConfiguration mailConfiguration;

    public ArtController(ArtService artService,
                           CategoryService categoryService,
                           MailConfiguration mailConfiguration) {
        this.artService = artService;
        this.categoryService = categoryService;
        this.mailConfiguration = mailConfiguration;
    }

    @ModelAttribute("styles")
    public StyleEnum[] styles() {
        return StyleEnum.values();
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("createArtDTO")) {
            model.addAttribute("createArtDTO", CreateArtDTO.empty());
        }

        model.addAttribute("categories", categoryService.getAllCategories());

        return "category-add";
    }

    @PostMapping("/add")
    public String add(
            @Valid CreateArtDTO createArtDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt,
            @AuthenticationPrincipal UserDetails seller) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("createArtDTO", createArtDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createArtDTO", bindingResult);
            return "redirect:/art/add";
        }


        UUID newArtUUID = artService.createArt(createArtDTO, seller);

        return "redirect:/art/" + newArtUUID;
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model,
                          @AuthenticationPrincipal UserDetails viewer) {

        ArtDetailDTO artDetailDTO = artService
                .getArtDetail(uuid, viewer)
                .orElseThrow(() -> new ObjectNotFoundException("Art with uuid " + uuid + " not found!"));

        model.addAttribute("art", artDetailDTO);

        return "details";
    }

    @PreAuthorize("@offerServiceImpl.isOwner(#uuid, #principal.username)")
    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid,
                         @AuthenticationPrincipal UserDetails principal) {

        artService.deleteArt(uuid);

        return "redirect:/arts/all";
    }
}
