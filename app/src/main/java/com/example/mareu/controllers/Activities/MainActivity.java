package com.example.mareu.controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mareu.R;
import com.example.mareu.controllers.Fragments.MeetingFragment;

public class MainActivity extends AppCompatActivity {


    private MeetingFragment meetingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureAndShowMainFragment();
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
