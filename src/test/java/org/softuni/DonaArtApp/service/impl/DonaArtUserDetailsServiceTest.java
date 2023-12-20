package org.softuni.DonaArtApp.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softuni.DonaArtApp.model.entity.UserEntity;
import org.softuni.DonaArtApp.model.entity.UserRoleEntity;
import org.softuni.DonaArtApp.model.enums.UserRoleEnum;
import org.softuni.DonaArtApp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonaArtUserDetailsServiceTest {
    private DonaArtUserDetailService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new DonaArtUserDetailService(
                mockUserRepository
        );
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("alex@softuni.bg")
        );
    }

    @Test
    void testUserFoundException() {
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
        // Act
        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getEmail());

        // Assert
        assertNotNull(userDetails);
        assertEquals(
                testUserEntity.getEmail(),
                userDetails.getUsername(),
                "Username is not mapped to email.");

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "The user is not an admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "The user is not a user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEntity createTestUser() {
        return new UserEntity()
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("alex@softuni.bg")
                .setActive(false)
                .setPassword("mysecret")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));
    }
}
