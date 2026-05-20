package com.example.njehub.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Section;
import android.widget.TextView;

public class CreateSectionActivity extends AppCompatActivity {

    private EditText inputSectionTitle, inputSectionSpeaker, inputSectionRoom;
    private EditText inputSectionTime, inputSectionDescription;
    private Button btnSaveSection;

    private TextView btnBack;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_section);

        database = AppDatabase.getInstance(this);

        inputSectionTitle = findViewById(R.id.inputSectionTitle);
        inputSectionSpeaker = findViewById(R.id.inputSectionSpeaker);
        inputSectionRoom = findViewById(R.id.inputSectionRoom);
        inputSectionTime = findViewById(R.id.inputSectionTime);
        inputSectionDescription = findViewById(R.id.inputSectionDescription);
        btnSaveSection = findViewById(R.id.btnSaveSection);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        btnSaveSection.setOnClickListener(v -> saveSection());
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

        if (speaker.isEmpty()) {
            speaker = "Nincs előadó megadva";
        }

        if (description.isEmpty()) {
            description = "Nincs részletes leírás megadva.";
        }

        Section section = new Section(
                1,
                title,
                speaker,
                room,
                time,
                description
        );

        database.sectionDao().insert(section);

        Toast.makeText(this, "Szekció hozzáadva!", Toast.LENGTH_SHORT).show();
        finish();
    }
}