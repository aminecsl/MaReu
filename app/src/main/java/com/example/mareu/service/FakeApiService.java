package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Amine K. on 06/01/20.
 */
public class FakeApiService implements MeetingApiService {


    private List<Meeting> meetings = FakeApiServiceGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {

        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {

        meetings.remove(meeting);
    }
}
