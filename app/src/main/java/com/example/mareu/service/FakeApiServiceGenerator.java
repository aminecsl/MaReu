package com.example.mareu.service;

import com.example.mareu.model.MeetingRoom;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Amine K. on 06/01/20.
 */
public abstract class FakeApiServiceGenerator {


    public static List<MeetingRoom> MEETING_ROOMS = Arrays.asList(

            new MeetingRoom(1, "A"),
            new MeetingRoom(2, "B"),
            new MeetingRoom(3, "C"),
            new MeetingRoom(4, "D"),
            new MeetingRoom(5, "E"),
            new MeetingRoom(6, "F"),
            new MeetingRoom(7, "G"),
            new MeetingRoom(8, "H"),
            new MeetingRoom(9, "I"),
            new MeetingRoom(10, "J")
    );


    /*
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(

        new Meeting(
                MEETING_ROOMS.get(2),
                "23/01/2020",
                "10:00",
                "Grève",
                Arrays.asList("paul@lamzone.com",
                        "julie@lamzone.com",
                        "samuel@lamzone.com",
                        "clara@lamzone.com")
        ),

        new Meeting(
                MEETING_ROOMS.get(0),
                "29/01/2020",
                "14:00",
                "Congés",
                Arrays.asList("luc@lamzone.com",
                        "laura@lamzone.com",
                        "robert@lamzone.com",
                        "michel@lamzone.com")
        ),

        new Meeting(
                MEETING_ROOMS.get(3),
                "07/02/2020",
                "12:30",
                "Release",
                    Arrays.asList("antoine@lamzone.com",
                            "pierre@lamzone.com",
                            "sophie@lamzone.com",
                            "claude@lamzone.com")
        ),

        new Meeting(
                MEETING_ROOMS.get(1),
                "11/02/202",
                "15:00",
                "Résultats",
                    Arrays.asList("nicole@lamzone.com",
                            "henri@lamzone.com",
                            "eric@lamzone.com",
                            "thomas@lamzone.com")
        ),

        new Meeting(
                MEETING_ROOMS.get(0),
                "19/02/2020",
                "16:15",
                "Embauche",
                    Arrays.asList("valerie@lamzone.com",
                            "chloe@lamzone.com",
                            "etienne@lamzone.com",
                            "herve@lamzone.com")
            )
    );*/

}
