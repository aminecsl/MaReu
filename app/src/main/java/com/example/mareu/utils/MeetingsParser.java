package com.example.mareu.utils;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Amine K. on 23/01/20.
 */
public class MeetingsParser {


    //Permet au spinner Dates du filtre des réunions de récupérer une liste contenant "toutes dates + une unique occurence des
    // dates de réunions prévues"
    public static List<String> getAllMeetingsDates(List<Meeting> meetings) {

        List<String> plannedMeetingDatesList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            plannedMeetingDatesList.add(meeting.getDate());
        }
        //On passe notre liste de dates dans un HashSet de façon à ne garder qu'une seule occurence de chaque date et on
        //re-transfrome ce HashSet en une List
        List<String> listDistinct = new ArrayList<>(new HashSet<>(plannedMeetingDatesList));
        listDistinct.add(0, "toutes dates");
        return listDistinct;
    }

    //Permet au spinner Salles du filtre des réunions de récupérer une liste contenant "toutes salles + une unique occurence des
    // salles de réunions prévus"
    public static List<String> getBookedRoomsForMeetings(List<Meeting> meetings){

        List<String> bookedRoomsList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            bookedRoomsList.add(meeting.getMeetingRoomName());
        }
        //On passe notre liste de salles dans un HashSet de façon à ne garder qu'une seule occurence de chaque salle et on
        //re-transfrome ce HashSet en une List
        List<String> listDistinct = new ArrayList<>(new HashSet<>(bookedRoomsList));
        listDistinct.add(0, "toutes salles");
        return listDistinct;
    }
}
