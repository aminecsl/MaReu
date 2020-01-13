package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Amine K. on 06/01/20.
 */
public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a meeting
     * @param meeting
     */

    void deleteMeeting (Meeting meeting);

}
