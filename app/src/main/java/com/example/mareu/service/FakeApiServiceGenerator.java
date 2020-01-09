package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Amine K. on 06/01/20.
 */
public abstract class FakeApiServiceGenerator {

    public static MeetingRoom mRoom1 = new MeetingRoom(1, "A");
    public static MeetingRoom mRoom2 = new MeetingRoom(2, "B");
    public static MeetingRoom mRoom3 = new MeetingRoom(3, "C");
    public static MeetingRoom mRoom4 = new MeetingRoom(4, "D");

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(

        new Meeting(
                mRoom3,
                "23/01/2020",
                "10:00",
                "Grève",
                Arrays.asList("paul@lamzone.com",
                        "julie@lamzone.com",
                        "samuel@lamzone.com",
                        "clara@lamzone.com")
        ),

        new Meeting(
                mRoom1,
                "29/01/2020",
                "14:00",
                "Congés",
                Arrays.asList("luc@lamzone.com",
                        "laura@lamzone.com",
                        "robert@lamzone.com",
                        "michel@lamzone.com")
        ),

        new Meeting(
                    mRoom4,
                "07/02/2020",
                "12:30",
                "Release",
                    Arrays.asList("antoine@lamzone.com",
                            "pierre@lamzone.com",
                            "sophie@lamzone.com",
                            "claude@lamzone.com")
        ),

        new Meeting(
                    mRoom2,
                "11/02/202",
                "15:00",
                "Résultats",
                    Arrays.asList("nicole@lamzone.com",
                            "henri@lamzone.com",
                            "eric@lamzone.com",
                            "thomas@lamzone.com")
        ),

        new Meeting(
                    mRoom1,
                "19/02/2020",
                "16:15",
                "Embauche",
                    Arrays.asList("valerie@lamzone.com",
                            "chloe@lamzone.com",
                            "etienne@lamzone.com",
                            "herve@lamzone.com")
            )
    );
}
