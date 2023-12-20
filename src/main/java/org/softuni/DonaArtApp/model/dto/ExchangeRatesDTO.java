package org.softuni.DonaArtApp.model.dto;

import java.math.BigDecimal;
import java.util.Map;

//{
//  "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
//  "license": "https://openexchangerates.org/license",
//  "timestamp": 1702029600,
//  "base": "USD",
//  "rates": {
//    "BGN": 1.81464,
//    "EUR": 0.927899
//  }
//}

public record ExchangeRatesDTO(String base, Map<String, BigDecimal> rates) {
}
