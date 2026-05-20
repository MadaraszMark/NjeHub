package com.example.njehub.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.activities.CreateEventActivity;
import com.example.njehub.adapters.EventAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;

import java.util.List;

public class EventsFragment extends Fragment {

    private RecyclerView recyclerEvents;
    private TextView txtEventCount, txtActiveCount;
    private Button btnAddEvent;

    private AppDatabase database;
    private List<Event> events;

    public EventsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerEvents = view.findViewById(R.id.recyclerEvents);
        txtEventCount = view.findViewById(R.id.txtEventCount);
        txtActiveCount = view.findViewById(R.id.txtActiveCount);
        btnAddEvent = view.findViewById(R.id.btnAddEvent);

        database = AppDatabase.getInstance(requireContext());

        loadEvents();

        btnAddEvent.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CreateEventActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (database != null) {
            loadEvents();
        }
    }

    private void loadEvents() {
        events = database.eventDao().getAllEvents();

        EventAdapter adapter = new EventAdapter(requireContext(), events);
        recyclerEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerEvents.setAdapter(adapter);

        txtEventCount.setText(String.valueOf(events.size()));

        int activeCount = 0;
        for (Event event : events) {
            if (event.getStatus().equalsIgnoreCase("Aktív")) {
                activeCount++;
            }
        }

        txtActiveCount.setText(String.valueOf(activeCount));
    }
}