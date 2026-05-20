package com.example.njehub.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "info_items")
public class InfoItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int eventId;
    private String title;
    private String description;
    private String icon;

    public InfoItem(int eventId, String title, String description, String icon) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.icon = icon;
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

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}