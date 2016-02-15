package com.ginzsa.showcase.config;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 * Created by santiago.ginzburg on 2/12/16.
 */
public class ApplicationInitializer {
    @Inject
    ApplicationInitializer(PersistService persistenceService) {
        // start JPA
        persistenceService.start();
    }
}
