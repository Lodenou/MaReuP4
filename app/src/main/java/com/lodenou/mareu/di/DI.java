package com.lodenou.mareu.di;

import com.lodenou.mareu.controller.DummyReunionApiService;
import com.lodenou.mareu.controller.ReunionApiService;


public class DI {
    private static ReunionApiService service = new DummyReunionApiService();


    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getNeighbourApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static ReunionApiService getNewInstanceApiService() {
        return new DummyReunionApiService();
    }
}

