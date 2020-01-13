package com.example.mareu.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mareu.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewMeetingActivity extends AppCompatActivity {



    @BindView(R.id.activity_meeting_emails_input) public EditText mEmailsInput;
    @BindView(R.id.activity_meeting_add_chip_btn) public Button mAddChipTagBtn;
    @BindView(R.id.activity_meeting_chip_group) public ChipGroup mChipGroup;
    @BindView(R.id.save_new_meeting_btn) public Button mSaveNewMeetingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting);
        ButterKnife.bind(this);

        mAddChipTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        mSaveNewMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMeeting();
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
        return emailsList;
    }
}
