package org.softuni.DonaArtApp.model.dto;

import jakarta.validation.constraints.*;
import org.softuni.DonaArtApp.model.enums.MaterialEnum;
import org.softuni.DonaArtApp.model.enums.StyleEnum;
import org.softuni.DonaArtApp.model.validations.YearNotInTheFuture;

public record CreateArtDTO(@NotEmpty @Size(min = 5, max = 512) String description,
                           @Positive @NotNull Long techniqueId, @NotNull StyleEnum style,
                           @NotNull MaterialEnum material, @NotEmpty String imageUrl,
                           @NotEmpty String dimensions,
                           @Positive @NotNull Integer price,
                           @YearNotInTheFuture(message = "The year should not be in the future!")
                           @NotNull(message = "Year must be provided!")
                           Integer year) {
    public static CreateArtDTO empty() {
        return new CreateArtDTO(null, null, null, null, null, null, null, null);
    }
}
