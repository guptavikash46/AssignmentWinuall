package com.example.assignmentwinuall.Model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PlacesDao {

    @Insert
    void insert(Places places);

    @Delete
    void delete(Places places);

    @Query("DELETE FROM Places")
    void deleteAllPlaces();

    @Query("SELECT *FROM Places ORDER BY Id")
    LiveData<List<Places>> getAllPlaces();
}
