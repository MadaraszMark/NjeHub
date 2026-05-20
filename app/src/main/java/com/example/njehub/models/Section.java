package com.example.njehub.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sections")
public class Section {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int eventId;
    private String title;
    private String speaker;
    private String room;
    private String time;
    private String description;

    public Section(int eventId, String title, String speaker, String room, String time, String description) {
        this.eventId = eventId;
        this.title = title;
        this.speaker = speaker;
        this.room = room;
        this.time = time;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}