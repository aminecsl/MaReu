package com.example.mareu.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by Amine K. on 06/01/20.
 */
public class Meeting {

    //Un compteur d'instance de façon à attribuer un id unique pour chaque objet Meeting créé
    private static int count = 0;

    private int id;
    private String mDate;
    private String mTime;
    private String mRoom;
    private String mSubject;
    private List<String> mEmails;

    public Meeting (String date, String time, String room, String subject, List<String> emails) {

        mDate = date;
        mTime = time;
        mRoom = room;
        mSubject = subject;
        mEmails = emails;
        id = count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public List<String> getEmails() {
        return mEmails;
    }

    public void setEmails(List<String> emails) {
        mEmails = emails;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
