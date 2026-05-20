package com.example.njehub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.adapters.SectionAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Section;
import android.content.Intent;
import android.widget.Button;
import com.example.njehub.activities.CreateSectionActivity;

import java.util.List;

public class SectionsFragment extends Fragment {

    private RecyclerView recyclerSections;
    private TextView txtSectionCount;

    private AppDatabase database;
    private List<Section> sections;

    public SectionsFragment() {}
    private Button btnAddSection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sections, container, false);

        recyclerSections = view.findViewById(R.id.recyclerSections);
        txtSectionCount = view.findViewById(R.id.txtSectionCount);
        btnAddSection = view.findViewById(R.id.btnAddSection);

        database = AppDatabase.getInstance(requireContext());

        loadSections();

        btnAddSection.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CreateSectionActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (database != null) {
            loadSections();
        }
    }

    private void loadSections() {
        sections = database.sectionDao().getAllSections();

        SectionAdapter adapter = new SectionAdapter(requireContext(), sections);
        recyclerSections.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerSections.setAdapter(adapter);

        txtSectionCount.setText(sections.size() + "\nSzekció");
    }
}