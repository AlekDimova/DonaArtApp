package org.softuni.DonaArtApp.model.dto;

import java.util.List;

public record CategoryDTO(String name, List<TechniqueDTO> techniques) {
}
