package com.example.mareu.controllers.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amine K. on 07/01/20.
 */
public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;


    public MeetingsRecyclerViewAdapter(List<Meeting> items) {

        mMeetings = items;
    }

    /* Crée un ViewHolder à partir du layout XML représentant chaque ligne de la RecyclerView.
     * Celle-ci est appelée pour les premières lignes visibles à l'écran de la RecyclerView.*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }


    /* Appelée pour chacune des lignes visibles affichées de notre RecyclerView.
     * C'est généralement ici que l'on met à jour leur apparence.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mMeetingRoom.setText(meeting.getRoom());
        holder.mMeetingSubject.setText(meeting.getRoom());

    }


    /* Permet de retourner la taille de notre liste d'objet, et ainsi d'indiquer à l'Adapter le nombre
     * de lignes que peut contenir la RecyclerView
     */
    @Override
    public int getItemCount() {

        return mMeetings.size();
    }

    /* Modélise en un objet View view la vue XML (fragment_neighbour) de notre ligne représentant un voisin*/
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_item_txt_1) public TextView mMeetingRoom;
        @BindView(R.id.meeting_item_txt_2) public TextView mMeetingSubject;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
