package org.softuni.DonaArtApp.service;

import org.softuni.DonaArtApp.model.dto.ConvertRequestDTO;
import org.softuni.DonaArtApp.model.dto.ExchangeRatesDTO;
import org.softuni.DonaArtApp.model.dto.MoneyDTO;

public interface CurrencyService {
    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);
    MoneyDTO convert(ConvertRequestDTO convertRequestDTO);

}
