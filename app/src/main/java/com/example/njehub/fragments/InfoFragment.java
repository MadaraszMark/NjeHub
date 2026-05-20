package com.example.njehub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njehub.R;
import com.example.njehub.adapters.InfoAdapter;
import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.InfoItem;
import android.content.Intent;
import android.widget.Button;
import com.example.njehub.activities.CreateInfoActivity;

import java.util.List;

public class InfoFragment extends Fragment {

    private RecyclerView recyclerInfo;
    private AppDatabase database;
    private List<InfoItem> infoItems;
    private Button btnAddInfo;

    public InfoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        recyclerInfo = view.findViewById(R.id.recyclerInfo);
        database = AppDatabase.getInstance(requireContext());
        btnAddInfo = view.findViewById(R.id.btnAddInfo);

        loadInfoItems();

        btnAddInfo.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), CreateInfoActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (database != null) {
            loadInfoItems();
        }
    }

    private void loadInfoItems() {
        infoItems = database.infoItemDao().getAllInfoItems();

        InfoAdapter adapter = new InfoAdapter(requireContext(), infoItems);
        recyclerInfo.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerInfo.setAdapter(adapter);
    }
}