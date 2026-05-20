package com.example.njehub.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.activities.CreateEventActivity;
import com.example.njehub.adapters.EventAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private RecyclerView recyclerEvents;
    private TextView txtEventCount, txtActiveCount;
    private Button btnAddEvent;
    private EditText inputSearchEvent;

    private AppDatabase database;
    private List<Event> events;
    private EventAdapter adapter;

    public EventsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerEvents = view.findViewById(R.id.recyclerEvents);
        txtEventCount = view.findViewById(R.id.txtEventCount);
        txtActiveCount = view.findViewById(R.id.txtActiveCount);
        btnAddEvent = view.findViewById(R.id.btnAddEvent);
        inputSearchEvent = view.findViewById(R.id.inputSearchEvent);

        database = AppDatabase.getInstance(requireContext());

        loadEvents();
        setupSearch();

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

            if (inputSearchEvent != null) {
                inputSearchEvent.setText("");
            }
        }
    }

    private void loadEvents() {
        events = database.eventDao().getAllEvents();

        adapter = new EventAdapter(requireContext(), events);
        recyclerEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerEvents.setAdapter(adapter);

        updateStats(events);
    }

    private void setupSearch() {
        inputSearchEvent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEvents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filterEvents(String query) {
        List<Event> filteredEvents = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            filteredEvents.addAll(events);
        } else {
            String lowerQuery = query.toLowerCase().trim();

            for (Event event : events) {
                if (event.getName().toLowerCase().contains(lowerQuery)
                        || event.getLocation().toLowerCase().contains(lowerQuery)
                        || event.getCategory().toLowerCase().contains(lowerQuery)
                        || event.getStatus().toLowerCase().contains(lowerQuery)
                        || event.getDate().toLowerCase().contains(lowerQuery)) {

                    filteredEvents.add(event);
                }
            }
        }

        adapter = new EventAdapter(requireContext(), filteredEvents);
        recyclerEvents.setAdapter(adapter);

        updateStats(filteredEvents);
    }

    private void updateStats(List<Event> eventList) {
        txtEventCount.setText(String.valueOf(eventList.size()));

        int activeCount = 0;

        for (Event event : eventList) {
            if (event.getStatus().equalsIgnoreCase("Aktív")) {
                activeCount++;
            }
        }

        txtActiveCount.setText(String.valueOf(activeCount));
    }
}