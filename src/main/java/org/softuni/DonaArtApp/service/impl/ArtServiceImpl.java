package org.softuni.DonaArtApp.service.impl;

import org.softuni.DonaArtApp.model.dto.ArtDetailDTO;
import org.softuni.DonaArtApp.model.dto.ArtSummaryDTO;
import org.softuni.DonaArtApp.model.dto.CreateArtDTO;
import org.softuni.DonaArtApp.model.entity.ArtEntity;
import org.softuni.DonaArtApp.model.entity.TechniqueEntity;
import org.softuni.DonaArtApp.model.entity.UserEntity;
import org.softuni.DonaArtApp.model.entity.UserRoleEntity;
import org.softuni.DonaArtApp.model.enums.UserRoleEnum;
import org.softuni.DonaArtApp.repository.ArtRepository;
import org.softuni.DonaArtApp.repository.TechniqueRepository;
import org.softuni.DonaArtApp.repository.UserRepository;
import org.softuni.DonaArtApp.service.ArtService;
import org.softuni.DonaArtApp.service.MonitoringService;
import org.softuni.DonaArtApp.service.aop.WarnIfExecutionExceeds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class ArtServiceImpl implements ArtService {
    private final ArtRepository artRepository;
    private final TechniqueRepository techniqueRepository;
    private final MonitoringService monitoringService;
    private final UserRepository userRepository;

    public ArtServiceImpl(
            ArtRepository artRepository,
            TechniqueRepository techniqueRepository,
            MonitoringService monitoringService,
            UserRepository userRepository) {
        this.artRepository = artRepository;
        this.techniqueRepository = techniqueRepository;
        this.monitoringService = monitoringService;
        this.userRepository = userRepository;
    }
    @Override
    public UUID createArt(CreateArtDTO createArtDTO, UserDetails seller) {
        ArtEntity newArt = map(createArtDTO);
        TechniqueEntity techniqueEntity = techniqueRepository.findById(createArtDTO.techniqueId()).orElseThrow(() ->
                new IllegalArgumentException("Technique with id " + createArtDTO.techniqueId() + " not found!"));
        UserEntity sellerEntity = userRepository.findByEmail(seller.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("User with email " + seller.getUsername() + " not found!"));


        newArt.setTechnique(techniqueEntity);
        newArt.setSeller(sellerEntity);

        newArt = artRepository.save(newArt);

        return newArt.getUuid();
    }
    @WarnIfExecutionExceeds(
            timeInMillis = 1000L)

    @Override
    public Page<ArtSummaryDTO> getAllArts(Pageable pageable) {
        return artRepository
                .findAll(pageable)
                .map(ArtServiceImpl::mapAsSummary);
    }

    @WarnIfExecutionExceeds(
            timeInMillis = 500L
    )

    @Override
    public Optional<ArtDetailDTO> getArtDetail(UUID artUUID, UserDetails viewer) {

        return artRepository
                .findByUuid(artUUID)
                .map(a -> this.mapAsDetails(a, viewer));
    }

    @Override
    @Transactional
    public void deleteArt(UUID artUUID) {
        artRepository.deleteByUuid(artUUID);
    }

    private ArtDetailDTO mapAsDetails(ArtEntity artEntity, UserDetails viewer) {

        return new ArtDetailDTO(
                artEntity.getUuid().toString(),
                artEntity.getTechnique().getCategory().getName(),
                artEntity.getTechnique().getName(),
                artEntity.getYear(),
                artEntity.getDimensions(),
                artEntity.getPrice(),
                artEntity.getStyle(),
                artEntity.getMaterial(),
                artEntity.getImageUrl(),
                artEntity.getSeller().getFirstName(),
                isOwner(artEntity, viewer != null ? viewer.getUsername() : null));
    }

    @Override
    public boolean isOwner(UUID uuid, String userName) {
        return isOwner(
                artRepository.findByUuid(uuid).orElse(null),
                userName
        );
    }

    private boolean isOwner(ArtEntity artEntity, String userName) {
        if (artEntity == null || userName == null) {
            // anonymous users own no offers// missing offers are meaningless
            return false;
        }

        UserEntity viewerEntity =
                userRepository
                        .findByEmail(userName)
                        .orElseThrow(() -> new IllegalArgumentException("Unknown user..."));

        if (isAdmin(viewerEntity)) {
            // all admins own all offers
            return true;
        }

        return Objects.equals(
                artEntity.getSeller().getId(),
                viewerEntity.getId());
    }

    private boolean isAdmin(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(r -> UserRoleEnum.ADMIN == r);
    }


    private static ArtSummaryDTO mapAsSummary(ArtEntity artEntity) {
        return new ArtSummaryDTO(
                artEntity.getUuid().toString(),
                artEntity.getTechnique().getCategory().getName(),
                artEntity.getTechnique().getName(),
                artEntity.getYear(),
                artEntity.getDimensions(),
                artEntity.getPrice(),
                artEntity.getStyle(),
                artEntity.getMaterial(),
                artEntity.getImageUrl());
    }

    private static ArtEntity map(CreateArtDTO createArtDTO) {

        return new ArtEntity()
                .setUuid(UUID.randomUUID())
                .setDescription(createArtDTO.description())
                .setStyle(createArtDTO.style())
                .setMaterial(createArtDTO.material())
                .setImageUrl(createArtDTO.imageUrl())
                .setDimensions(createArtDTO.dimensions())
                .setPrice(BigDecimal.valueOf(createArtDTO.price()))
                .setYear(createArtDTO.year());
    }
}
