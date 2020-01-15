package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amine K. on 06/01/20.
 */
public class FakeApiService implements MeetingApiService {


    private List<Meeting> meetings = new ArrayList<>();

    @Override
    public List<Meeting> getMeetings() {

        return meetings;
    }

    @Override
    public void addMeeting (Meeting meeting) {

        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {

        meetings.remove(meeting);
    }

    @Override
    public List<Meeting> getFilteredMeetingsList(String date, MeetingRoom room) {

        List<Meeting> filteredMeetings = new ArrayList<>();

        if (date != null && room != null) {
            for (Meeting meeting : meetings) {
                if (meeting.getDate().equals(date) && meeting.getMeetingRoom().equals(room)) {
                    filteredMeetings.add(meeting);
                }
            }
        }

        if (date == null || room == null) {
            for (Meeting meeting : meetings) {
                if (meeting.getDate().equals(date) || meeting.getMeetingRoom().equals(room)) {
                    filteredMeetings.add(meeting);
                }
            }
        }

        return filteredMeetings;
    }
}
