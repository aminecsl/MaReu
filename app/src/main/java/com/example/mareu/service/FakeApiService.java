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

        if (date == "toutes dates" && roomName == "toutes salles") {

            return meetings;
        }
        if (date != "toutes dates" && roomName != "toutes salles") {

            for (Meeting meeting : meetings) {
                if (meeting.getDate().equals(date) && meeting.getMeetingRoomName().equals(roomName)) {
                    filteredMeetings.add(meeting);
                }
            }
        }
        if ((date == "toutes dates" && roomName != "toutes salles") || (date != "toutes dates" && roomName == "toutes salles")) {

            for (Meeting meeting : meetings) {
                if (meeting.getDate().equals(date) || meeting.getMeetingRoomName().equals(roomName)) {
                    filteredMeetings.add(meeting);
                }
            }
        }

        return filteredMeetings;
    }

    //Permet au spinner Dates du filtre de récupérer une liste contenant "toutes dates + une unique occurence des dates de réunions prévues"
    @Override
    public List<String> getAllMeetingsDates() {

        List<String> plannedMeetingDatesList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            plannedMeetingDatesList.add(meeting.getDate());
        }
        List<String> listDistinct = plannedMeetingDatesList.stream().distinct().collect(Collectors.toList());
        listDistinct.add(0, "toutes dates");
        return listDistinct;
    }

    //Permet au spinner Salles du filtre de récupérer une liste contenant "toutes salles + une unique occurence des salles de réunions prévus"
    @Override
    public List<String> getBookedRoomsForMeetings(){

        List<String> bookedRoomsList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            bookedRoomsList.add(meeting.getMeetingRoomName());
        }
        List<String> listDistinct = bookedRoomsList.stream().distinct().collect(Collectors.toList());
        listDistinct.add(0, "toutes salles");
        return listDistinct;
    }
}
