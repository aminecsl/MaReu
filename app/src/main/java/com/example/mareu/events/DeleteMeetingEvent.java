package com.example.mareu.events;

import com.example.mareu.model.Meeting;

/**
 * Created by Amine K. on 14/01/20.
 */
public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
