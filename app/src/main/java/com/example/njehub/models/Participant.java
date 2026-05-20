package com.example.njehub.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "participants")
public class Participant {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int eventId;
    private String name;
    private String email;
    private String role;
    private String qrCode;

    public Participant(int eventId, String name, String email, String role, String qrCode) {
        this.eventId = eventId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.qrCode = qrCode;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getQrCode() {
        return qrCode;
    }
}