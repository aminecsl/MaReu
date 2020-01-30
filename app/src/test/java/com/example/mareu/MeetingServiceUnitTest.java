package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class MeetingServiceUnitTest {


    private MeetingApiService mService;

    @Before
    public void setup() {

        mService = DI.getNewInstanceApiService();
    }

    @Test
    public void addAndGetMeetingWithSuccess() {

        MeetingRoom room = new MeetingRoom(99, "hello");
        Meeting mMeeting = new Meeting(room,
                "07/02/2020",
                "12:30",
                "Release",
                Arrays.asList("antoine@lamzone.com",
                        "pierre@lamzone.com",
                        "sophie@lamzone.com",
                        "claude@lamzone.com"));
        mService.addMeeting(mMeeting);
        assertTrue(mService.getMeetings().size() == 1);
        assertTrue(mService.getMeetings().get(0).equals(mMeeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {

        MeetingRoom room = new MeetingRoom(77, "world");
        Meeting mMeeting = new Meeting(room,
                "18/03/2020",
                "14:00",
                "Pause",
                Arrays.asList("sam@lamzone.com",
                        "louis@lamzone.com",
                        "pascal@lamzone.com",
                        "julia@lamzone.com"));
        mService.addMeeting(mMeeting);
        assertTrue(mService.getMeetings().size() == 1);
        mService.deleteMeeting(mMeeting);
        assertTrue(mService.getMeetings().size() == 0);
    }

    @Test
    public void getFilteredMeetingsWithSuccess() {

        MeetingRoom room1 = new MeetingRoom(55, "a");
        MeetingRoom room2 = new MeetingRoom(44, "b");
        Meeting mMeeting1 = new Meeting(room1,
                "25/04/2020",
                "13:00",
                "test1",
                Arrays.asList("eric@lamzone.com",
                        "paul@lamzone.com"));
        Meeting mMeeting2 = new Meeting(room2,
                "03/02/2020",
                "16:00",
                "2",
                Arrays.asList("jean@lamzone.com",
                        "carole@lamzone.com"));
        mService.addMeeting(mMeeting1);
        mService.addMeeting(mMeeting2);
        List<Meeting> filteredMeetings = mService.getFilteredMeetingsList("03/02/2020", "b");
        assertTrue(filteredMeetings.size() == 1);
        assertEquals(mMeeting2, filteredMeetings.get(0));
    }

}