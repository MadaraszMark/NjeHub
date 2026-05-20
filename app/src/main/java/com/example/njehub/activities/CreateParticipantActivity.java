package com.example.njehub.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Participant;
import android.widget.TextView;

public class CreateParticipantActivity extends AppCompatActivity {

    private EditText inputParticipantName, inputParticipantEmail, inputParticipantRole;
    private Button btnSaveParticipant;
    private AppDatabase database;
    private TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_participant);

        database = AppDatabase.getInstance(this);

        inputParticipantName = findViewById(R.id.inputParticipantName);
        inputParticipantEmail = findViewById(R.id.inputParticipantEmail);
        inputParticipantRole = findViewById(R.id.inputParticipantRole);
        btnSaveParticipant = findViewById(R.id.btnSaveParticipant);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        btnSaveParticipant.setOnClickListener(v -> saveParticipant());
    }

    private void saveParticipant() {
        String name = inputParticipantName.getText().toString().trim();
        String email = inputParticipantEmail.getText().toString().trim();
        String role = inputParticipantRole.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Név és email megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (role.isEmpty()) {
            role = "Résztvevő";
        }

        String qrCode = "NJEHUB-" + System.currentTimeMillis();

        Participant participant = new Participant(
                1,
                name,
                email,
                role,
                qrCode
        );

        database.participantDao().insert(participant);

        Toast.makeText(this, "Résztvevő hozzáadva!", Toast.LENGTH_SHORT).show();
        finish();
    }
}