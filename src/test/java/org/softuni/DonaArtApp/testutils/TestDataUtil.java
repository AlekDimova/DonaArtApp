package org.softuni.DonaArtApp.testutils;

import org.softuni.DonaArtApp.model.entity.*;
import org.softuni.DonaArtApp.model.enums.MaterialEnum;
import org.softuni.DonaArtApp.model.enums.StyleEnum;
import org.softuni.DonaArtApp.repository.ArtRepository;
import org.softuni.DonaArtApp.repository.CategoryRepository;
import org.softuni.DonaArtApp.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class TestDataUtil {
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private ArtRepository artRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRateEntity().setCurrency(currency).setRate(rate)
        );
    }


    public ArtEntity createTestArt(UserEntity owner) {

        // create test brand
        CategoryEntity categoryEntity = categoryRepository.save(new CategoryEntity()
                .setName("Test Category")
                .setTechniques(List.of(
                        new TechniqueEntity().setName("Test Technique"),
                        new TechniqueEntity().setName("Test Technique1")
                )));

        // create test art
        ArtEntity art = new ArtEntity()
                .setTechnique(categoryEntity.getTechniques().get(0))
                .setImageUrl("https://www.google.com")
                .setPrice(BigDecimal.valueOf(1000))
                .setYear(2020)
                .setUuid(UUID.randomUUID())
                .setDescription("Test Description")
                .setStyle(StyleEnum.Landscape)
                .setDimensions("34Wx35H")
                .setMaterial(MaterialEnum.Canvas)
                .setSeller(owner);

        return artRepository.save(art);
    }
    //
    public void cleanUp() {
        exchangeRateRepository.deleteAll();
        artRepository.deleteAll();
        categoryRepository.deleteAll();
    }
}
