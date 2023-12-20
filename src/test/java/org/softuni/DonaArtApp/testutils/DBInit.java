package org.softuni.DonaArtApp.testutils;


import org.softuni.DonaArtApp.model.entity.UserRoleEntity;
import org.softuni.DonaArtApp.model.enums.UserRoleEnum;
import org.softuni.DonaArtApp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    new UserRoleEntity().setRole(UserRoleEnum.USER),
                    new UserRoleEntity().setRole(UserRoleEnum.ADMIN)
            ));
        }
    }
}
