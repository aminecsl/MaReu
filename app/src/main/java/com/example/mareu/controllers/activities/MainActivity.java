package com.example.mareu.controllers.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mareu.R;
import com.example.mareu.controllers.fragments.MeetingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private MeetingFragment meetingFragment;

    @BindView(R.id.floatingActionButton) FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configureAndShowMainFragment();

    }

    @OnClick (R.id.floatingActionButton)
    public void openNewMeetingActivity(){
        Intent intent = new Intent (MainActivity.this, NewMeetingActivity.class);
        startActivity(intent);
    }


    //Ajoute les entrées de notre menu à la toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    //Gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_activity_main_filter:
                showCustomDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showCustomDialog() {

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_new_meeting_filters_dialog, viewGroup, false);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        //setting the view of the builder to our custom view that we already inflated
        dialogBuilder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = dialogBuilder.create();

        Button mDialogCancelBtn = (Button) alertDialog.findViewById(R.id.dialog_cancel_button);
        mDialogCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        Button mDialogConfirmBtn = (Button) alertDialog.findViewById(R.id.dialog_confirm_button);
        mDialogConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alertDialog.show();
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
