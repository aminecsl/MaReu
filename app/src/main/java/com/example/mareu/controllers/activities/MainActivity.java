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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.controllers.fragments.MeetingFragment;
import com.example.mareu.di.DI;
import com.example.mareu.events.RefreshFilteredListEvent;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.floatingActionButton) FloatingActionButton mFloatingActionButton;
    private MeetingFragment meetingFragment;
    private Spinner mDialogRoomsSpinner;
    private Spinner mDialogDatesSpinner;
    private String filteredDate;
    private String filteredRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configureAndShowMainFragment();

    }

    //onClickListener sur le Floating Action Button grâce à ButterKnife
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

    //Gère le click sur l'iône Filter de la toolbar
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
        //A bien placer avant pour pouvoir ensuite récupérer les vues positionnées dans l'AlertDialog
        alertDialog.show();

        mDialogDatesSpinner = (Spinner) alertDialog.findViewById(R.id.dialog_dates_spinner);
        configureDateSpinner();

        mDialogRoomsSpinner = (Spinner) alertDialog.findViewById(R.id.dialog_rooms_spinner);
        configureRoomSpinner();

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
                EventBus.getDefault().post(new RefreshFilteredListEvent(filteredDate, filteredRoom));
                alertDialog.dismiss();
            }
        });

    }

    public void configureDateSpinner() {

        MeetingApiService mApiService = DI.getMeetingApiService();
        List<String> plannedMeetingDates = mApiService.getAllMeetingsDates();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plannedMeetingDates);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDialogDatesSpinner.setAdapter(adapter);
        mDialogDatesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                filteredDate = plannedMeetingDates.get(pos);

            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                /*This automatic method may be used so that you can set which item will be selected given that the previous item is no
                longer available. This is instead of letting the spinner automatically select the next item in the list. */
            }
        });
        //Permet de positionner par défaut l'item précédemment choisi dans le spinner
        if (filteredDate != null) {
            int spinnerPosition = adapter.getPosition(filteredDate);
            mDialogDatesSpinner.setSelection(spinnerPosition);
        }
    }

    public void configureRoomSpinner() {

        MeetingApiService mApiService = DI.getMeetingApiService();
        List<String> bookedRoomsNames = mApiService.getBookedRoomsForMeetings();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bookedRoomsNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDialogRoomsSpinner.setAdapter(adapter);
        mDialogRoomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                filteredRoom = bookedRoomsNames.get(pos);

            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                /*This automatic method may be used so that you can set which item will be selected given that the previous item is no
                longer available. This is instead of letting the spinner automatically select the next item in the list. */
            }
        });
        //Permet de positionner par défaut l'item précédemment choisi dans le spinner
        if (filteredRoom != null) {
            int spinnerPosition = adapter.getPosition(filteredRoom);
            mDialogRoomsSpinner.setSelection(spinnerPosition);
        }
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