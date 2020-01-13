package com.example.mareu.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mareu.R;
import com.example.mareu.controllers.fragments.MeetingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private MeetingFragment meetingFragment;

    @BindView(R.id.floatingActionButton) public FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureAndShowMainFragment();

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, NewMeetingActivity.class);
                startActivity(intent);
            }
        });

    }


    private void configureAndShowMainFragment(){
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        meetingFragment = (MeetingFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        if (meetingFragment == null) {
            // B - Create new main fragment
            meetingFragment = new MeetingFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_main, meetingFragment)
                    .commit();
        }
    }

}
