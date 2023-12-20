package org.softuni.DonaArtApp.web;

import org.softuni.DonaArtApp.model.dto.ArtSummaryDTO;
import org.softuni.DonaArtApp.service.ArtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arts")
public class ArtsController {
    private final ArtService artService;

    public ArtsController(ArtService artService) {
        this.artService = artService;
    }

    @GetMapping("/all")
    public String all(Model model,
                      @PageableDefault(
                              size = 3,
                              sort = "uuid"
                      ) Pageable pageable) {

        Page<ArtSummaryDTO> allArts = artService.getAllArts(pageable);

        model.addAttribute("arts", allArts);

        return "arts";
    }

}
