package org.softuni.DonaArtApp.repository;

import org.softuni.DonaArtApp.model.entity.UserRoleEntity;

import org.softuni.DonaArtApp.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);
}
