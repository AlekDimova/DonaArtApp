package org.softuni.DonaArtApp.service.impl;

import org.softuni.DonaArtApp.model.dto.CategoryDTO;
import org.softuni.DonaArtApp.model.dto.TechniqueDTO;
import org.softuni.DonaArtApp.repository.CategoryRepository;
import org.softuni.DonaArtApp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.getAllCategories().stream()
                .map(category -> new CategoryDTO(
                        category.getName(),
                        category.getTechniques().stream()
                                .map(technique -> new TechniqueDTO(technique.getId(), technique.getName()))
                                .sorted(Comparator.comparing(TechniqueDTO::name))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(CategoryDTO::name))
                .collect(Collectors.toList());
    }
}
