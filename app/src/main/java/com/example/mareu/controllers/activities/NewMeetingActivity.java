package com.example.mareu.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.FakeApiServiceGenerator;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.activity_meeting_emails_input) public EditText mEmailsInput;
    @BindView(R.id.activity_meeting_add_chip_btn) public Button mAddChipTagBtn;
    @BindView(R.id.activity_meeting_chip_group) public ChipGroup mChipGroup;
    @BindView(R.id.save_new_meeting_btn) public Button mSaveNewMeetingBtn;
    @BindView(R.id.new_meeting_subject_input) public EditText mSubjectInput;
    @BindView(R.id.new_meeting_date_input) public EditText mDateInput;
    @BindView(R.id.new_meeting_time_input) public EditText mTimeInput;
    @BindView(R.id.rooms_spinner) public Spinner mSpinner;

    private List<MeetingRoom> mMeetingRoomsList = FakeApiServiceGenerator.MEETING_ROOMS;
    List<String> roomsNames = new ArrayList<>();
    MeetingRoom chosenRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);
        ButterKnife.bind(this);

        mDateInput.setInputType(InputType.TYPE_NULL);
        mDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMeetingDate();
            }
        });

        mTimeInput.setInputType(InputType.TYPE_NULL);
        mTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMeetingTime();
            }
        });

        mAddChipTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayChipTag();
            }
        });

        configureAndShowRoomSpinner();

        mSaveNewMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMeeting();
            }
        });
    }

    public void setMeetingDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePicker = new DatePickerDialog(NewMeetingActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        int month = monthOfYear + 1;
                        String formattedMonth = "" + month;
                        String formattedDayOfMonth = "" + dayOfMonth;
                        if(month < 10){
                            formattedMonth = "0" + month;
                        }
                        if(dayOfMonth < 10){
                            formattedDayOfMonth = "0" + dayOfMonth;
                        }
                        mDateInput.setText(formattedDayOfMonth + "/" + formattedMonth + "/" + year);
                    }
                }, year, month, day);
        datePicker.show();
    }

    public void setMeetingTime() {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(NewMeetingActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        String formattedHour = "" + sHour;
                        String formattedMinute = "" + sMinute;
                        if(sHour < 10){
                            formattedHour = "0" + sHour;
                        }
                        if(sMinute < 10){
                            formattedMinute = "0" + sMinute;
                        }
                        mTimeInput.setText(formattedHour + ":" + formattedMinute);
                    }
                }, hour, minutes, true);
        timePicker.show();
    }

    public void displayChipTag(){
        String chipTag = mEmailsInput.getText().toString();
        if (isValidEmail(chipTag)) {
            LayoutInflater inflater = LayoutInflater.from(NewMeetingActivity.this);
            Chip chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
            chip.setText(chipTag);
            mChipGroup.addView(chip);
            mEmailsInput.getText().clear();
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChipGroup.removeView(v);
                }
            });
        }else {
            Toast.makeText(this, "Email non conforme", Toast.LENGTH_SHORT).show();
        }
    }

    public void configureAndShowRoomSpinner() {

        for (MeetingRoom room : mMeetingRoomsList) {
            roomsNames.add("Salle " + room.getRoomName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomsNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        chosenRoom = mMeetingRoomsList.get(position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        /*
         *Custom Code
         */
    }


    public void addNewMeeting(){
        List<String> finalEmailsList = generateEmailsList();
        Meeting newMeeting = new Meeting(
                chosenRoom,
                mDateInput.getText().toString(),
                mTimeInput.getText().toString(),
                mSubjectInput.getText().toString(),
                finalEmailsList
        );
        MeetingApiService mApiService = DI.getMeetingApiService();
        mApiService.addMeeting(newMeeting);
        finish();
    }

    public List<String> generateEmailsList(){
        List<String> emailsList = new ArrayList<>();
        for (int i=0; i < mChipGroup.getChildCount(); i++){
            Chip chip = (Chip) mChipGroup.getChildAt(i);
            emailsList.add(chip.getText().toString());
        }
        return emailsList;
    }

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}