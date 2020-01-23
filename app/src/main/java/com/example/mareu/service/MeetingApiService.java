package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

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
     * Adds a meeting
     * @param meeting
     */

    void addMeeting(Meeting meeting);

    /**
     * Deletes a meeting
     * @param meeting
     */

    void deleteMeeting(Meeting meeting);

    /**
     * Filters a meeting
     * @params date, room
     */

    List<Meeting>  getFilteredMeetingsList(String date, String roomName);

}
