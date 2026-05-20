package com.example.njehub.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;

public class CreateEventActivity extends AppCompatActivity {

    private EditText inputName, inputDescription, inputDate, inputLocation, inputCategory, inputStatus;
    private Button btnSaveEvent;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        database = AppDatabase.getInstance(this);

        inputName = findViewById(R.id.inputName);
        inputDescription = findViewById(R.id.inputDescription);
        inputDate = findViewById(R.id.inputDate);
        inputLocation = findViewById(R.id.inputLocation);
        inputCategory = findViewById(R.id.inputCategory);
        inputStatus = findViewById(R.id.inputStatus);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);

        btnSaveEvent.setOnClickListener(v -> saveEvent());
    }

    private void saveEvent() {
        String name = inputName.getText().toString().trim();
        String description = inputDescription.getText().toString().trim();
        String date = inputDate.getText().toString().trim();
        String location = inputLocation.getText().toString().trim();
        String category = inputCategory.getText().toString().trim();
        String status = inputStatus.getText().toString().trim();

        if (name.isEmpty() || date.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Név, dátum és helyszín megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (description.isEmpty()) {
            description = "Nincs részletes leírás megadva.";
        }

        if (category.isEmpty()) {
            category = "Egyetemi esemény";
        }

        if (status.isEmpty()) {
            status = "Tervezés alatt";
        }

        Event event = new Event(
                name,
                description,
                date,
                location,
                category,
                status,
                "#6366F1"
        );

        database.eventDao().insert(event);

        Toast.makeText(this, "Esemény sikeresen létrehozva!", Toast.LENGTH_SHORT).show();
        finish();
    }
}