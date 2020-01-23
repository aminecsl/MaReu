package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //Retourne une liste de réunions en fonction de la date et/ou de la salle selectionnées dans les spinners du filtre
    @Override
    public List<Meeting> getFilteredMeetingsList(String date, String roomName) {

        List<Meeting> filteredMeetings = new ArrayList<>();

        for (Meeting meeting : meetings) {
            if (roomName.equals("toutes salles") || meeting.getMeetingRoomName().equals(roomName)) {
                if (date.equals("toutes dates") || meeting.getDate().equals(date)) {
                    filteredMeetings.add(meeting);
                }
            }
        }
//
//        if (date == "toutes dates" && roomName == "toutes salles") {
//
//            return meetings;
//        }
//        if (date != "toutes dates" && roomName != "toutes salles") {
//
//            for (Meeting meeting : meetings) {
//                if (meeting.getDate().equals(date) && meeting.getMeetingRoomName().equals(roomName)) {
//                    filteredMeetings.add(meeting);
//                }
//            }
//        }
//        if ((date == "toutes dates" && roomName != "toutes salles") || (date != "toutes dates" && roomName == "toutes salles")) {
//
//            for (Meeting meeting : meetings) {
//                if (meeting.getDate().equals(date) || meeting.getMeetingRoomName().equals(roomName)) {
//                    filteredMeetings.add(meeting);
//                }
//            }
//        }

        return filteredMeetings;
    }

}
