package com.example.njehub.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String date;
    private String location;
    private String category;
    private String status;
    private String color;

    public Event(String name, String description, String date,
                 String location, String category, String status, String color) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.category = category;
        this.status = status;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getColor() {
        return color;
    }
}