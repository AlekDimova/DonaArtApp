package org.softuni.DonaArtApp.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.DonaArtApp.model.enums.MaterialEnum;
import org.softuni.DonaArtApp.model.enums.StyleEnum;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "arts")
public class ArtEntity extends BaseEntity {

    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    @NotEmpty
    private String description;
    @NotNull
    @ManyToOne
    private TechniqueEntity technique;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StyleEnum style;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MaterialEnum material;

    @ManyToOne
    private UserEntity seller;
    @NotEmpty
    private String imageUrl;
    @Positive
    private String dimensions;
    @NotNull
    private BigDecimal price;
    //@Positive

    @NotEmpty
    private int year;

    public String getDescription() {
        return description;
    }

    public ArtEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public TechniqueEntity getTechnique() {
        return technique;
    }

    public ArtEntity setTechnique(TechniqueEntity technique) {
        this.technique = technique;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public ArtEntity setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }

    public MaterialEnum getMaterial() {
        return material;
    }

    public ArtEntity setMaterial(MaterialEnum material) {
        this.material = material;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArtEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDimensions() {
        return dimensions;
    }

    public ArtEntity setDimensions(String dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ArtEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getYear() {
        return year;
    }

    public ArtEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArtEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }
    public UserEntity getSeller() {
        return seller;
    }

    public ArtEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }
}
