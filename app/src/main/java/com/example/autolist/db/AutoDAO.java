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

    @Query("SELECT * FROM AutoRecord ORDER BY dateEntered ASC")
    LiveData<List<AutoRecord>> getAllAutoRecords();

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore new record for an existing place
    void insert(AutoRecord... ar);

    @Delete
    void delete(AutoRecord... ar);

    @Query("SELECT * FROM AutoRecord WHERE autoId = :autoId LIMIT 1")
    LiveData<AutoRecord> getRecordForAutoId(int autoId);

//    @Query("SELECT * FROM AutoRecord WHERE year like '%' ) // TODO finish query
//    LiveData<AutoRecord> getRecordForAuto(String autoId);

//    @Query("DELETE FROM AutoRecord WHERE id = :id")
//    void delete(int id);

//    @Query("SELECT * from NOTE where text like '%' || :search || '%' or hashTags like '%' || :search || '%' ")
//    LiveData<List<AutoRecord>> searchNotes(String search);

}
