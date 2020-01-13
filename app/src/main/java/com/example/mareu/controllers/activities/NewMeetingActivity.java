package com.example.mareu.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mareu.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_meeting_emails_input) public EditText mEmailsInput;
    @BindView(R.id.activity_meeting_add_chip_btn) public Button mAddChipTagBtn;
    @BindView(R.id.activity_meeting_chip_group) public ChipGroup mChipGroup;
    @BindView(R.id.save_new_meeting_btn) public Button mSaveNewMeetingBtn;
    @BindView(R.id.new_meeting_subject_input) public EditText mSubjectInput;
    @BindView(R.id.new_meeting_date_input) public EditText mDateInput;
    @BindView(R.id.new_meeting_time_input) public EditText mTimeInput;


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
                        mDateInput.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                        mTimeInput.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
        timePicker.show();
    }

    public void displayChipTag(){
        String chipTag = mEmailsInput.getText().toString();
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
    }

    public void addNewMeeting(){
        generateEmailsList();
    }

    public List<String> generateEmailsList(){
        List<String> emailsList = new ArrayList<>();
        for (int i=0; i < mChipGroup.getChildCount(); i++){
            Chip chip = (Chip) mChipGroup.getChildAt(i);
            emailsList.add(chip.getText().toString());
        }
        System.out.println(emailsList);
        return emailsList;
    }
}
