package org.softuni.DonaArtApp.service.impl;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softuni.DonaArtApp.service.MonitoringService;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {
    private Logger LOGGER = LoggerFactory.getLogger(MonitoringServiceImpl.class);

    private final Counter artSearches;

    public MonitoringServiceImpl(MeterRegistry meterRegistry) {
        artSearches = Counter
                .builder("art_search_cnt")
                .description("How many arts searches we have performed")
                .register(meterRegistry);
    }

    @Override
    public void logArtSearch() {
        LOGGER.info("Art search was performed.");
        artSearches.increment();
    }
}
