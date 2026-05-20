package com.example.njehub.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.njehub.models.InfoItem;

import java.util.List;

@Dao
public interface InfoItemDao {

    @Insert
    void insert(InfoItem infoItem);

    @Query("SELECT * FROM info_items ORDER BY id DESC")
    List<InfoItem> getAllInfoItems();

    @Query("SELECT * FROM info_items WHERE eventId = :eventId ORDER BY id DESC")
    List<InfoItem> getInfoItemsByEventId(int eventId);

    @Query("DELETE FROM info_items")
    void deleteAllInfoItems();
}