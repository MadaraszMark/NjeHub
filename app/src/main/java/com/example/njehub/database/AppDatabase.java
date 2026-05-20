package com.example.njehub.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.njehub.models.Event;
import com.example.njehub.models.Participant;
import com.example.njehub.models.Section;
import com.example.njehub.models.InfoItem;

@Database(entities = {Event.class, Participant.class, Section.class, InfoItem.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract EventDao eventDao();

    public abstract ParticipantDao participantDao();

    public abstract SectionDao sectionDao();

    public abstract InfoItemDao infoItemDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "njehub_database"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}