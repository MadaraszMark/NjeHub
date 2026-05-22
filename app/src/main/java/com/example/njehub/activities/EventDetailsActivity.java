package com.example.njehub.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;
import com.example.njehub.activities.EventParticipantsActivity;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView btnBack;
    private TextView txtDetailName, txtDetailCategory, txtDetailDate;
    private TextView txtDetailLocation, txtDetailStatus, txtDetailDescription;

    private Button btnOpenParticipants, btnOpenSections, btnOpenInfo, btnDeleteEvent;

    private AppDatabase database;
    private Event event;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        database = AppDatabase.getInstance(this);

        btnBack = findViewById(R.id.btnBack);

        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailCategory = findViewById(R.id.txtDetailCategory);
        txtDetailDate = findViewById(R.id.txtDetailDate);
        txtDetailLocation = findViewById(R.id.txtDetailLocation);
        txtDetailStatus = findViewById(R.id.txtDetailStatus);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);

        btnOpenParticipants = findViewById(R.id.btnOpenParticipants);
        btnOpenSections = findViewById(R.id.btnOpenSections);
        btnOpenInfo = findViewById(R.id.btnOpenInfo);
        btnDeleteEvent = findViewById(R.id.btnDeleteEvent);

        eventId = getIntent().getIntExtra("event_id", -1);

        if (eventId == -1) {
            Toast.makeText(this, "Esemény nem található.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadEvent();
        setupActions();
    }

    private void loadEvent() {
        event = database.eventDao().getEventById(eventId);

        if (event == null) {
            Toast.makeText(this, "Esemény nem található az adatbázisban.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtDetailName.setText(event.getName());
        txtDetailCategory.setText(event.getCategory());
        txtDetailDate.setText("📅 " + event.getDate());
        txtDetailLocation.setText("📍 " + event.getLocation());
        txtDetailStatus.setText(event.getStatus());
        txtDetailDescription.setText(event.getDescription());
    }

    private void setupActions() {
        btnBack.setOnClickListener(v -> finish());

        btnOpenParticipants.setOnClickListener(v -> {
            Intent intent = new Intent(this, EventParticipantsActivity.class);
            intent.putExtra("event_id", eventId);
            startActivity(intent);
        });

        btnOpenSections.setOnClickListener(v -> {
            Intent intent = new Intent(this, EventSectionsActivity.class);
            intent.putExtra("event_id", eventId);
            startActivity(intent);
        });

        btnOpenInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, EventInfoActivity.class);
            intent.putExtra("event_id", eventId);
            startActivity(intent);
        });

        btnDeleteEvent.setOnClickListener(v -> confirmDelete());
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Esemény törlése")
                .setMessage("Biztosan törölni szeretnéd ezt az eseményt?")
                .setPositiveButton("Törlés", (dialog, which) -> {
                    database.eventDao().delete(event);
                    Toast.makeText(this, "Esemény törölve.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Mégse", null)
                .show();
    }
    private void openMainTab(String tabName) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("open_tab", tabName);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}