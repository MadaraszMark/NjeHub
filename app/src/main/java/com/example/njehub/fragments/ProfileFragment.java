package com.example.njehub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;

public class ProfileFragment extends Fragment {

    private TextView txtProfileEvents, txtProfileParticipants, txtProfileSections, txtProfileInfo;
    private TextView txtDatabaseStatus;

    private AppDatabase database;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtProfileEvents = view.findViewById(R.id.txtProfileEvents);
        txtProfileParticipants = view.findViewById(R.id.txtProfileParticipants);
        txtProfileSections = view.findViewById(R.id.txtProfileSections);
        txtProfileInfo = view.findViewById(R.id.txtProfileInfo);
        txtDatabaseStatus = view.findViewById(R.id.txtDatabaseStatus);

        database = AppDatabase.getInstance(requireContext());

        loadStats();

        return view;
    }

    private void loadStats() {
        int eventCount = database.eventDao().getAllEvents().size();
        int participantCount = database.participantDao().getAllParticipants().size();
        int sectionCount = database.sectionDao().getAllSections().size();
        int infoCount = database.infoItemDao().getAllInfoItems().size();

        txtProfileEvents.setText(eventCount + "\nEsemény");
        txtProfileParticipants.setText(participantCount + "\nRésztvevő");
        txtProfileSections.setText(sectionCount + "\nSzekció");
        txtProfileInfo.setText(infoCount + "\nInfó");

        txtDatabaseStatus.setText("Room adatbázis aktív · " +
                (eventCount + participantCount + sectionCount + infoCount) +
                " rekord betöltve");
    }
}