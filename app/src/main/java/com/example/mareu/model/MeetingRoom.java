package com.example.mareu.model;

import android.graphics.Color;

/**
 * Created by Amine K. on 08/01/20.
 */
public class MeetingRoom {

    private int mRoomNumber;
    private String mRoomName;
    private int mRoomColor;

    public MeetingRoom (int roomNumber, String roomName){
        mRoomNumber = roomNumber;
        mRoomName = roomName;
        mRoomColor = setRoomColor();
    }

    public int getRoomNum() {
        return mRoomNumber;
    }

    public void setRoomNum(int roomNumber) {
        mRoomNumber = roomNumber;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        mRoomName = roomName;
    }

    public int getRoomColor() {
        return mRoomColor;
    }

    public int setRoomColor() {

        int color = 0;
        switch (mRoomNumber){
            case 1 :
                color = Color.argb(255,38, 196, 236);
                break;
            case 2 :
                color = Color.argb(255,131, 166, 151);
                break;
            case 3 :
                color = Color.argb(255,232, 214, 48);
                break;
            case 4 :
                color = Color.argb(255,231, 62, 1);
                break;
            case 5 :
                color = Color.argb(255,157, 62, 12);
                break;
            case 6 :
                color = Color.argb(255,255, 0, 255);
                break;
            case 7 :
                color = Color.argb(255,254, 27, 0);
                break;
            case 8 :
                color = Color.argb(255,247, 35, 12);
                break;
            case 9 :
                color = Color.argb(255,20, 148, 20);
                break;
            case 10 :
                color = Color.argb(255,249, 66, 158);
                break;
        }

        return color;
    }

}
