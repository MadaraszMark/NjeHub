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
import com.example.njehub.models.Section;

import java.util.ArrayList;
import java.util.List;

public class CreateSectionActivity extends AppCompatActivity {

    private TextView btnBack;
    private EditText inputSectionTitle, inputSectionSpeaker, inputSectionRoom;
    private EditText inputSectionTime, inputSectionDescription;
    private Spinner spinnerEvents;
    private Button btnSaveSection;

    private AppDatabase database;
    private List<Event> events;
    private int selectedEventId = 1;
    private int incomingEventId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_section);

        database = AppDatabase.getInstance(this);
        incomingEventId = getIntent().getIntExtra("event_id", -1);

        btnBack = findViewById(R.id.btnBack);
        inputSectionTitle = findViewById(R.id.inputSectionTitle);
        inputSectionSpeaker = findViewById(R.id.inputSectionSpeaker);
        inputSectionRoom = findViewById(R.id.inputSectionRoom);
        inputSectionTime = findViewById(R.id.inputSectionTime);
        inputSectionDescription = findViewById(R.id.inputSectionDescription);
        spinnerEvents = findViewById(R.id.spinnerEvents);
        btnSaveSection = findViewById(R.id.btnSaveSection);

        btnBack.setOnClickListener(v -> finish());

        setupEventSpinner();

        btnSaveSection.setOnClickListener(v -> saveSection());
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

    private void saveSection() {
        String title = inputSectionTitle.getText().toString().trim();
        String speaker = inputSectionSpeaker.getText().toString().trim();
        String room = inputSectionRoom.getText().toString().trim();
        String time = inputSectionTime.getText().toString().trim();
        String description = inputSectionDescription.getText().toString().trim();

        if (title.isEmpty() || room.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Cím, terem és időpont megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (events.isEmpty()) {
            Toast.makeText(this, "Előbb hozz létre egy eseményt!", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedIndex = spinnerEvents.getSelectedItemPosition();
        selectedEventId = events.get(selectedIndex).getId();

        if (speaker.isEmpty()) {
            speaker = "Nincs előadó megadva";
        }

        if (description.isEmpty()) {
            description = "Nincs részletes leírás megadva.";
        }

        Section section = new Section(
                selectedEventId,
                title,
                speaker,
                room,
                time,
                description
        );

        database.sectionDao().insert(section);

        Toast.makeText(this, "Szekció hozzáadva az eseményhez!", Toast.LENGTH_SHORT).show();
        finish();
    }
}