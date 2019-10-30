package com.example.autolist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AutoDAO {

    @Query("SELECT * FROM AutoRecord ORDER BY name ASC")
    LiveData<List<AutoRecord>> getAllPlaceRecords();

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore new record for an existing place
    void insert(AutoRecord... pr);

    @Delete
    void delete(AutoRecord... pr);

    @Query("SELECT * FROM AutoRecord WHERE name = :name LIMIT 1")
    LiveData<AutoRecord> getRecordForName(String name);

    @Query("DELETE FROM AutoRecord WHERE id = :id")
    void delete(int id);

//    @Query("SELECT * FROM PlaceRecord")
//    LiveData<List<PlaceRecord>> getAllRecords();
}
