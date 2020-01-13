package com.example.mareu.di;

import com.example.mareu.service.FakeApiService;
import com.example.mareu.service.MeetingApiService;

/**
 * Created by Amine K. on 07/01/20.
 */
public class DI {


    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {

        return new FakeApiService();
    }
}
