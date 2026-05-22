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
import android.content.Intent;
import android.widget.Button;
import com.example.njehub.activities.CreateParticipantActivity;

import java.util.List;

public class ParticipantsFragment extends Fragment {

    private RecyclerView recyclerParticipants;
    private TextView txtParticipantCount, txtOrganizerCount;

    private AppDatabase database;
    private List<Participant> participants;

    public ParticipantsFragment() {}
    private Button btnAddParticipant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_participants, container, false);

        recyclerParticipants = view.findViewById(R.id.recyclerParticipants);
        txtParticipantCount = view.findViewById(R.id.txtParticipantCount);
        txtOrganizerCount = view.findViewById(R.id.txtOrganizerCount);
        btnAddParticipant = view.findViewById(R.id.btnAddParticipant);

        database = AppDatabase.getInstance(requireContext());

        loadParticipants();

        btnAddParticipant.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CreateParticipantActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (database != null) {
            loadParticipants();
        }
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