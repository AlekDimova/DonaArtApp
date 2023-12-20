package org.softuni.DonaArtApp.service;

public interface EmailService {
    void sendRegistrationEmail(
            String userEmail,
            String userName,
            String activationCode);
}
