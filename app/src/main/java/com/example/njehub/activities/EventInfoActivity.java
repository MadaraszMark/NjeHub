package com.example.njehub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.adapters.InfoAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.InfoItem;

import java.util.List;

public class EventInfoActivity extends AppCompatActivity {

    private TextView btnBack, txtCount;
    private Button btnAddInfo;
    private RecyclerView recyclerInfo;
    private AppDatabase database;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        eventId = getIntent().getIntExtra("event_id", -1);
        database = AppDatabase.getInstance(this);

        btnBack = findViewById(R.id.btnBack);
        txtCount = findViewById(R.id.txtCount);
        btnAddInfo = findViewById(R.id.btnAddInfo);
        recyclerInfo = findViewById(R.id.recyclerInfo);

        btnBack.setOnClickListener(v -> finish());

        btnAddInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateInfoActivity.class);
            intent.putExtra("event_id", eventId);
            startActivity(intent);
        });

        loadInfoItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInfoItems();
    }

    private void loadInfoItems() {
        List<InfoItem> infoItems = database.infoItemDao().getInfoItemsByEventId(eventId);

        InfoAdapter adapter = new InfoAdapter(this, infoItems);
        recyclerInfo.setLayoutManager(new LinearLayoutManager(this));
        recyclerInfo.setAdapter(adapter);

        txtCount.setText(infoItems.size() + " információ ehhez az eseményhez");
    }
}