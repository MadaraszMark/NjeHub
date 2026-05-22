package com.example.njehub.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njehub.R;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.InfoItem;
import android.widget.TextView;

public class CreateInfoActivity extends AppCompatActivity {

    private EditText inputInfoTitle, inputInfoDescription, inputInfoIcon;
    private Button btnSaveInfo;
    private AppDatabase database;
    private TextView btnBack;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_info);

        database = AppDatabase.getInstance(this);

        inputInfoTitle = findViewById(R.id.inputInfoTitle);
        inputInfoDescription = findViewById(R.id.inputInfoDescription);
        inputInfoIcon = findViewById(R.id.inputInfoIcon);
        btnSaveInfo = findViewById(R.id.btnSaveInfo);
        btnBack = findViewById(R.id.btnBack);
        eventId = getIntent().getIntExtra("event_id", 1);

        btnBack.setOnClickListener(v -> finish());

        btnSaveInfo.setOnClickListener(v -> saveInfo());
    }

    private void saveInfo() {
        String title = inputInfoTitle.getText().toString().trim();
        String description = inputInfoDescription.getText().toString().trim();
        String icon = inputInfoIcon.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Cím és leírás megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (icon.isEmpty()) {
            icon = "ℹ️";
        }

        InfoItem infoItem = new InfoItem(
                eventId,
                title,
                description,
                icon
        );

        database.infoItemDao().insert(infoItem);

        Toast.makeText(this, "Információ hozzáadva!", Toast.LENGTH_SHORT).show();
        finish();
    }
}