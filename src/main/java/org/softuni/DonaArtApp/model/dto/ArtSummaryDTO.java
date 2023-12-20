package org.softuni.DonaArtApp.model.dto;


import org.softuni.DonaArtApp.model.enums.MaterialEnum;
import org.softuni.DonaArtApp.model.enums.StyleEnum;

import java.math.BigDecimal;

public record ArtSummaryDTO(
        String id,
        String category,
        String technique,
        int year,
        String dimensions,
        BigDecimal price,
        StyleEnum style,
        MaterialEnum material,
        String imageUrl
) {
    public String summary() {
        return category + " " + technique + ", " + year;
    }
}
