package com.example.njehub.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.njehub.models.Participant;

import java.util.List;

@Dao
public interface ParticipantDao {

    @Insert
    void insert(Participant participant);

    @Query("SELECT * FROM participants ORDER BY id DESC")
    List<Participant> getAllParticipants();

    @Query("SELECT * FROM participants WHERE eventId = :eventId ORDER BY id DESC")
    List<Participant> getParticipantsByEventId(int eventId);

    @Query("DELETE FROM participants")
    void deleteAllParticipants();
}