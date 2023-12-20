package org.softuni.DonaArtApp.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
@NamedEntityGraph(
        name = "categoryWithTechniques",
        attributeNodes = @NamedAttributeNode("techniques")
)
public class CategoryEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "category"
    )
    private List<TechniqueEntity> techniques;

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public List<TechniqueEntity> getTechniques() {
        return techniques;
    }

    public CategoryEntity setTechniques(List<TechniqueEntity> techniques) {
        this.techniques = techniques;
        return this;
    }
}
