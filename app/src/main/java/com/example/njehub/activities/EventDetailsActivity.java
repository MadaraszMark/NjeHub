package com.example.njehub.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView txtDetailName, txtDetailCategory, txtDetailDate;
    private TextView txtDetailLocation, txtDetailStatus, txtDetailDescription;

    private AppDatabase database;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        database = AppDatabase.getInstance(this);

        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailCategory = findViewById(R.id.txtDetailCategory);
        txtDetailDate = findViewById(R.id.txtDetailDate);
        txtDetailLocation = findViewById(R.id.txtDetailLocation);
        txtDetailStatus = findViewById(R.id.txtDetailStatus);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);

        int eventId = getIntent().getIntExtra("event_id", -1);

        if (eventId == -1) {
            Toast.makeText(this, "Esemény nem található.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        event = database.eventDao().getEventById(eventId);

        if (event == null) {
            Toast.makeText(this, "Esemény nem található az adatbázisban.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        showEventDetails();
    }

    private void showEventDetails() {
        txtDetailName.setText(event.getName());
        txtDetailCategory.setText(event.getCategory());
        txtDetailDate.setText("📅 " + event.getDate());
        txtDetailLocation.setText("📍 " + event.getLocation());
        txtDetailStatus.setText(event.getStatus());
        txtDetailDescription.setText(event.getDescription());
    }
}