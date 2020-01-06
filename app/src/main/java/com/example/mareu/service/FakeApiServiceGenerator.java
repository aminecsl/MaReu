package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Amine K. on 06/01/20.
 */
public abstract class FakeApiServiceGenerator {

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(

        new Meeting(
                "27/01/2020",
                "10:00",
                "Salle C",
                "Grève",
                Arrays.asList("paul@lamzone.com",
                        "julie@lamzone.com",
                        "samuel@lamzone.com",
                        "clara@lamzone.com")
        ),

        new Meeting(
                "29/01/2020",
                        "14:00",
                        "Salle A",
                        "Congés",
                Arrays.asList("luc@lamzone.com",
                        "laura@lamzone.com",
                        "robert@lamzone.com",
                        "michel@lamzone.com")
        ),

        new Meeting(
                    "02/02/2020",
                            "11:00",
                            "Salle D",
                            "Release",
                    Arrays.asList("antoine@lamzone.com",
                            "pierre@lamzone.com",
                            "sophie@lamzone.com",
                            "claude@lamzone.com")
        ),

        new Meeting(
                    "07/02/2020",
                            "16:00",
                            "Salle E",
                            "Résultats",
                    Arrays.asList("nicole@lamzone.com",
                            "henri@lamzone.com",
                            "eric@lamzone.com",
                            "thomas@lamzone.com")
        )

    );
}
