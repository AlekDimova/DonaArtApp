package org.softuni.DonaArtApp.testutils;

import org.softuni.DonaArtApp.model.entity.UserEntity;
import org.softuni.DonaArtApp.model.enums.UserRoleEnum;
import org.softuni.DonaArtApp.repository.UserRepository;
import org.softuni.DonaArtApp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(UserRoleEnum.ADMIN));
    }

    private UserEntity createUser(String email, List<UserRoleEnum> roles) {

        var roleEntities = userRoleRepository.findAllByRoleIn(roles);

        UserEntity newUser = new UserEntity()
                .setActive(true)
                .setEmail(email)
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(
                        roleEntities
                );

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
