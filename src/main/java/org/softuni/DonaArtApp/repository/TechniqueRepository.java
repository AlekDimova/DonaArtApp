package org.softuni.DonaArtApp.repository;

import org.softuni.DonaArtApp.model.entity.TechniqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechniqueRepository extends JpaRepository<TechniqueEntity, Long> {
    List<TechniqueEntity> findAllByCategoryId(Long id);
}
