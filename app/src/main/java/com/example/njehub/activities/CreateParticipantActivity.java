package com.example.njehub.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;
import com.example.njehub.models.Participant;

import java.util.ArrayList;
import java.util.List;

public class CreateParticipantActivity extends AppCompatActivity {

    private TextView btnBack;
    private EditText inputParticipantName, inputParticipantEmail, inputParticipantRole;
    private Spinner spinnerEvents;
    private Button btnSaveParticipant;

    private AppDatabase database;
    private List<Event> events;
    private int selectedEventId = 1;
    private int incomingEventId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_participant);

        database = AppDatabase.getInstance(this);
        incomingEventId = getIntent().getIntExtra("event_id", -1);

        btnBack = findViewById(R.id.btnBack);
        inputParticipantName = findViewById(R.id.inputParticipantName);
        inputParticipantEmail = findViewById(R.id.inputParticipantEmail);
        inputParticipantRole = findViewById(R.id.inputParticipantRole);
        spinnerEvents = findViewById(R.id.spinnerEvents);
        btnSaveParticipant = findViewById(R.id.btnSaveParticipant);

        btnBack.setOnClickListener(v -> finish());

        setupEventSpinner();

        btnSaveParticipant.setOnClickListener(v -> saveParticipant());
    }

    private void setupEventSpinner() {
        events = database.eventDao().getAllEvents();

        List<String> eventNames = new ArrayList<>();

        if (events.isEmpty()) {
            eventNames.add("Nincs elérhető esemény");
        } else {
            for (Event event : events) {
                eventNames.add(event.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                eventNames
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvents.setAdapter(adapter);

        if (incomingEventId != -1) {
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getId() == incomingEventId) {
                    spinnerEvents.setSelection(i);
                    break;
                }
            }
        }
    }

    private void saveParticipant() {
        String name = inputParticipantName.getText().toString().trim();
        String email = inputParticipantEmail.getText().toString().trim();
        String role = inputParticipantRole.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Név és email megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (events.isEmpty()) {
            Toast.makeText(this, "Előbb hozz létre egy eseményt!", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedIndex = spinnerEvents.getSelectedItemPosition();
        selectedEventId = events.get(selectedIndex).getId();

        if (role.isEmpty()) {
            role = "Résztvevő";
        }

        String qrCode = "NJEHUB-" + System.currentTimeMillis();

        Participant participant = new Participant(
                selectedEventId,
                name,
                email,
                role,
                qrCode
        );

        database.participantDao().insert(participant);

        Toast.makeText(this, "Résztvevő hozzáadva az eseményhez!", Toast.LENGTH_SHORT).show();
        finish();
    }
}