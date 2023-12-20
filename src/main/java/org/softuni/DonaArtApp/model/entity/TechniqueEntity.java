package org.softuni.DonaArtApp.model.entity;



import jakarta.persistence.*;
import org.softuni.DonaArtApp.model.enums.TechniqueQualityEnum;


@Entity
@Table(name = "techniques")
public class TechniqueEntity extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private TechniqueQualityEnum technique;

    //vryzka s categorijata
    @ManyToOne
    private CategoryEntity category;

    public String getName() {
        return name;
    }

    public TechniqueEntity setName(String name) {
        this.name = name;
        return this;
    }

    public TechniqueQualityEnum getTechnique() {
        return technique;
    }

    public TechniqueEntity setTechnique(TechniqueQualityEnum technique) {
        this.technique = technique;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public TechniqueEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueEntity{" +
                "name='" + name + '\'' +
                ", technique=" + technique +
                ", category=" + category +
                '}';
    }
}
