package com.example.njehub.utils;

import android.content.Context;

import com.example.njehub.database.AppDatabase;
import com.example.njehub.models.Event;
import com.example.njehub.models.Participant;
import com.example.njehub.models.Section;
import com.example.njehub.models.InfoItem;

public class DatabaseSeeder {

    public static void seedDatabase(Context context) {

        AppDatabase database = AppDatabase.getInstance(context);

        if (database.eventDao().getAllEvents().isEmpty()) {

            database.eventDao().insert(new Event(
                    "NJE Konferencia 2026",
                    "Egyetemi szakmai konferencia előadásokkal és workshopokkal.",
                    "2026. május 28.",
                    "Neumann János Egyetem - Aula",
                    "Konferencia",
                    "Aktív",
                    "#6366F1"
            ));

            database.eventDao().insert(new Event(
                    "Nyílt Nap",
                    "Interaktív campus bemutató középiskolásoknak.",
                    "2026. június 4.",
                    "Campus főépület",
                    "Nyílt nap",
                    "Tervezés alatt",
                    "#06B6D4"
            ));
        }

        if (database.participantDao().getAllParticipants().isEmpty()) {

            database.participantDao().insert(new Participant(
                    1,
                    "Kovács Anna",
                    "anna.kovacs@uni.hu",
                    "Szervező",
                    "NJEHUB-P001"
            ));

            database.participantDao().insert(new Participant(
                    1,
                    "Nagy Bence",
                    "bence.nagy@uni.hu",
                    "Előadó",
                    "NJEHUB-P002"
            ));

            database.participantDao().insert(new Participant(
                    1,
                    "Tóth Eszter",
                    "eszter.toth@student.uni.hu",
                    "Résztvevő",
                    "NJEHUB-P003"
            ));
        }
        if (database.sectionDao().getAllSections().isEmpty()) {

            database.sectionDao().insert(new Section(
                    1,
                    "Megnyitó és köszöntő",
                    "Dr. Egyetemi Péter",
                    "Aula",
                    "09:00 - 09:30",
                    "A konferencia hivatalos megnyitója."
            ));

            database.sectionDao().insert(new Section(
                    1,
                    "AI az oktatásban",
                    "Nagy Bence",
                    "B épület 204",
                    "10:00 - 10:45",
                    "Előadás a mesterséges intelligencia oktatási lehetőségeiről."
            ));

            database.sectionDao().insert(new Section(
                    1,
                    "Hallgatói projektbemutatók",
                    "Kovács Anna",
                    "Informatikai labor",
                    "11:00 - 12:30",
                    "Interaktív hallgatói projektprezentációk."
            ));

            database.sectionDao().insert(new Section(
                    1,
                    "Networking és zárás",
                    "Szervezői csapat",
                    "Aula",
                    "13:00 - 14:00",
                    "Kapcsolatépítés és kötetlen beszélgetés."
            ));
        }
        if (database.infoItemDao().getAllInfoItems().isEmpty()) {

            database.infoItemDao().insert(new InfoItem(
                    1,
                    "Wi-Fi hozzáférés",
                    "Hálózat: NJE-Guest, jelszó: event2026",
                    "📶"
            ));

            database.infoItemDao().insert(new InfoItem(
                    1,
                    "Regisztrációs pont",
                    "A regisztráció az Aula főbejáratánál található.",
                    "📝"
            ));

            database.infoItemDao().insert(new InfoItem(
                    1,
                    "Parkolás",
                    "Ingyenes parkolás a B épület mögötti parkolóban.",
                    "🅿️"
            ));

            database.infoItemDao().insert(new InfoItem(
                    1,
                    "Kapcsolattartó",
                    "Szervezői iroda: event@nje.hu",
                    "☎️"
            ));
        }
    }
}