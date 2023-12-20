package org.softuni.DonaArtApp.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.softuni.DonaArtApp.model.validations.FieldMatch;
import org.softuni.DonaArtApp.model.validations.UniqueUserEmail;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords does not match."
)
public record UserRegistrationDTO(@NotEmpty String firstName,
                                  @NotEmpty String lastName,
                                  @NotNull @Email @UniqueUserEmail String email,
                                  String password,
                                  String confirmPassword) {
    public String fullName() {
        return firstName + " " + lastName;
    }

}
