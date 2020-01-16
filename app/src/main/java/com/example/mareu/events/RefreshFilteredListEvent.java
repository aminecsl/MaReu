package com.example.mareu.events;

/**
 * Created by Amine K. on 16/01/20.
 */
public class RefreshFilteredListEvent {

    private String date;
    private String room;

    public RefreshFilteredListEvent(String date, String room){
        this.date = date;
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public String getRoom() {
        return room;
    }
}
