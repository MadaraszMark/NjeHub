package com.example.njehub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.adapters.ParticipantAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Participant;

import java.util.List;

public class ParticipantsFragment extends Fragment {

    private RecyclerView recyclerParticipants;
    private TextView txtParticipantCount, txtOrganizerCount;

    private AppDatabase database;
    private List<Participant> participants;

    public ParticipantsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_participants, container, false);

        recyclerParticipants = view.findViewById(R.id.recyclerParticipants);
        txtParticipantCount = view.findViewById(R.id.txtParticipantCount);
        txtOrganizerCount = view.findViewById(R.id.txtOrganizerCount);

        database = AppDatabase.getInstance(requireContext());

        loadParticipants();

        return view;
    }

    private void loadParticipants() {
        participants = database.participantDao().getAllParticipants();

        ParticipantAdapter adapter = new ParticipantAdapter(requireContext(), participants);
        recyclerParticipants.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerParticipants.setAdapter(adapter);

        updateStats();
    }

    private void updateStats() {
        txtParticipantCount.setText(participants.size() + "\nRésztvevő");

        int organizerCount = 0;

        for (Participant participant : participants) {
            if (participant.getRole().equalsIgnoreCase("Szervező")) {
                organizerCount++;
            }
        }

        txtOrganizerCount.setText(organizerCount + "\nSzervező");
    }
}