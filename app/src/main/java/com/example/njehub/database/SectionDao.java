package com.example.njehub.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.njehub.models.Section;

import java.util.List;

@Dao
public interface SectionDao {

    @Insert
    void insert(Section section);

    @Query("SELECT * FROM sections ORDER BY id DESC")
    List<Section> getAllSections();

    @Query("SELECT * FROM sections WHERE eventId = :eventId ORDER BY id DESC")
    List<Section> getSectionsByEventId(int eventId);

    @Query("DELETE FROM sections")
    void deleteAllSections();
}