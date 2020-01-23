package com.example.mareu.controllers.adapters;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Amine K. on 07/01/20.
 */
public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;


    public MeetingsRecyclerViewAdapter(List<Meeting> items) {

        mMeetings = items;
    }

    /* 1. Crée un ViewHolder à partir du layout XML représentant chaque ligne de la RecyclerView.
     * Celle-ci est appelée pour les premières lignes visibles à l'écran de la RecyclerView.*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }


    /* 3. Appelée pour chacune des lignes visibles affichées de notre RecyclerView.
     * C'est généralement ici que l'on met à jour leur apparence.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mMeetingIconRoom.setText(meeting.getMeetingRoomName());
        holder.mMeetingSubject.setText(meeting.getSubject());
        holder.mMeetingDate.setText(meeting.getDate());
        holder.mMeetingTime.setText(meeting.getTime());
        holder.mMeetingEmails.setText(StringUtils.join(meeting.getEmails(), ", "));

        ((GradientDrawable) holder.mMeetingIconRoom.getBackground()).setColor(meeting.getMeetingRoomColor());

        holder.mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }


    /* Permet de retourner la taille de notre liste d'objet, et ainsi d'indiquer à l'Adapter le nombre
     * de lignes que peut contenir la RecyclerView
     */
    @Override
    public int getItemCount() {

        return mMeetings.size();
    }

    /* 2. Modélise en un objet View view la vue XML (fragment_meeting) de notre ligne représentant une réunion*/
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_icon_room) public TextView mMeetingIconRoom;
        @BindView(R.id.meeting_subject_txt) public TextView mMeetingSubject;
        @BindView(R.id.meeting_date_txt) public TextView mMeetingDate;
        @BindView(R.id.meeting_time_txt) public TextView mMeetingTime;
        @BindView(R.id.meeting_emails_txt) public TextView mMeetingEmails;
        @BindView(R.id.meeting_delete_btn) public ImageButton mDeleteBtn;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
