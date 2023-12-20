package org.softuni.DonaArtApp.service;


import org.softuni.DonaArtApp.model.dto.ReCaptchaResponseDTO;

import java.util.Optional;

public interface ReCaptchaService {
    Optional<ReCaptchaResponseDTO> verify(String token);
}
