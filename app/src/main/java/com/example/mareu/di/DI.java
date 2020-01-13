package com.example.mareu.di;

import com.example.mareu.service.FakeApiService;
import com.example.mareu.service.MeetingApiService;

/**
 * Created by Amine K. on 07/01/20.
 */
public class DI {


    private static MeetingApiService service = new FakeApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {

        return service;
    }


    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {

        return new FakeApiService();
    }
}
