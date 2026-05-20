package com.example.njehub.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.njehub.R;
import com.example.njehub.fragments.EventsFragment;
import com.example.njehub.fragments.InfoFragment;
import com.example.njehub.fragments.ParticipantsFragment;
import com.example.njehub.fragments.ProfileFragment;
import com.example.njehub.fragments.SectionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.njehub.utils.DatabaseSeeder;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseSeeder.seedDatabase(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        loadFragment(new EventsFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            int id = item.getItemId();

            if (id == R.id.nav_events) {
                selectedFragment = new EventsFragment();
            }
            else if (id == R.id.nav_participants) {
                selectedFragment = new ParticipantsFragment();
            }
            else if (id == R.id.nav_sections) {
                selectedFragment = new SectionsFragment();
            }
            else if (id == R.id.nav_info) {
                selectedFragment = new InfoFragment();
            }
            else if (id == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            return loadFragment(selectedFragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }

        return false;
    }
}