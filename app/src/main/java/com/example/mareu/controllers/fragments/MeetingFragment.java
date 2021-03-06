package com.example.mareu.controllers.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.controllers.adapters.MeetingsRecyclerViewAdapter;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.events.RefreshFilteredListEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {


    private MeetingApiService mApiService;
    private RecyclerView mRecyclerView;
    private String dateFiltered;
    private String roomFiltered;


    public MeetingFragment() {
        // Required empty public constructor
    }


    public static MeetingFragment newInstance(String filteredDate, String filteredRoom) {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }


    /*  A la création du fragment, on crée une instance de l'API qui contient les méthodes pour gérer nos datas*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNewInstanceApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meetings_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    //On a déplacé initList dans onResume() de façon à regénérer (rafraîchir) nos listes en (re)venant sur la Main Activity
    @Override
    public void onResume() {
        super.onResume();
        initList();
    }


    /**
     * Init the List of meetings
     */
    private void initList() {

        List<Meeting> mMeetings;

        if (dateFiltered != null && roomFiltered != null) {

            mMeetings = mApiService.getFilteredMeetingsList(dateFiltered, roomFiltered);
        }else {
            mMeetings = mApiService.getMeetings();
        }

        mRecyclerView.setAdapter(new MeetingsRecyclerViewAdapter(mMeetings));
    }

    //Le fragment s'enregistre en tant que receveur d'un event auprès de EventBus
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //Le fragment doit se désinscrire en tant que receveur auprès de EventBus
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {

        mApiService.deleteMeeting(event.meeting);
        initList();
    }

    @Subscribe
    public void onRefreshFilteredList(RefreshFilteredListEvent event) {

        dateFiltered = event.getDate();
        roomFiltered = event.getRoom();
        initList();
    }

}
