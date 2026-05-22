package com.example.njehub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.adapters.ParticipantAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Participant;

import java.util.List;

public class EventParticipantsActivity extends AppCompatActivity {

    private TextView btnBack, txtTitle, txtCount;
    private Button btnAddParticipant;
    private RecyclerView recyclerParticipants;

    private AppDatabase database;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_participants);

        eventId = getIntent().getIntExtra("event_id", -1);

        database = AppDatabase.getInstance(this);

        btnBack = findViewById(R.id.btnBack);
        txtTitle = findViewById(R.id.txtTitle);
        txtCount = findViewById(R.id.txtCount);
        btnAddParticipant = findViewById(R.id.btnAddParticipant);
        recyclerParticipants = findViewById(R.id.recyclerParticipants);

        btnBack.setOnClickListener(v -> finish());

        btnAddParticipant.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateParticipantActivity.class);
            intent.putExtra("event_id", eventId);
            startActivity(intent);
        });

        loadParticipants();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadParticipants();
    }

    private void loadParticipants() {
        List<Participant> participants = database.participantDao().getParticipantsByEventId(eventId);

        ParticipantAdapter adapter = new ParticipantAdapter(this, participants);
        recyclerParticipants.setLayoutManager(new LinearLayoutManager(this));
        recyclerParticipants.setAdapter(adapter);

        txtCount.setText(participants.size() + " résztvevő ehhez az eseményhez");
    }
}