package org.softuni.DonaArtApp.service;

import org.softuni.DonaArtApp.model.events.UserRegisteredEvent;

public interface UserActivationService {
    void userRegistered(UserRegisteredEvent event);

    void cleanUpObsoleteActivationLinks();

    String createActivationCode(String userEmail);

}
