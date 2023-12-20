package org.softuni.DonaArtApp.service;

import org.softuni.DonaArtApp.model.dto.ArtDetailDTO;
import org.softuni.DonaArtApp.model.dto.ArtSummaryDTO;
import org.softuni.DonaArtApp.model.dto.CreateArtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface ArtService {
    UUID createArt(CreateArtDTO createArtDTO, UserDetails seller);

    Page<ArtSummaryDTO> getAllArts(Pageable pageable);

    Optional<ArtDetailDTO> getArtDetail(UUID artUUID, UserDetails viewer);

    void deleteArt(UUID artUUID);

    boolean isOwner(UUID uuid, String userName);
}
