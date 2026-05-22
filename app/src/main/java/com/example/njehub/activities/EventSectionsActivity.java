package com.example.njehub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.adapters.SectionAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Section;

import java.util.List;

public class EventSectionsActivity extends AppCompatActivity {

    private TextView btnBack, txtCount;
    private Button btnAddSection;
    private RecyclerView recyclerSections;
    private AppDatabase database;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_sections);

        eventId = getIntent().getIntExtra("event_id", -1);
        database = AppDatabase.getInstance(this);

        btnBack = findViewById(R.id.btnBack);
        txtCount = findViewById(R.id.txtCount);
        btnAddSection = findViewById(R.id.btnAddSection);
        recyclerSections = findViewById(R.id.recyclerSections);

        btnBack.setOnClickListener(v -> finish());

        btnAddSection.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateSectionActivity.class);
            intent.putExtra("event_id", eventId);
            startActivity(intent);
        });

        loadSections();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSections();
    }

    private void loadSections() {
        List<Section> sections = database.sectionDao().getSectionsByEventId(eventId);

        SectionAdapter adapter = new SectionAdapter(this, sections);
        recyclerSections.setLayoutManager(new LinearLayoutManager(this));
        recyclerSections.setAdapter(adapter);

        txtCount.setText(sections.size() + " szekció ehhez az eseményhez");
    }
}