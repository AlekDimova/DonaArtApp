package org.softuni.DonaArtApp.repository;

import org.softuni.DonaArtApp.model.entity.ArtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtRepository extends JpaRepository<ArtEntity, Long> {
    Optional<ArtEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

}
