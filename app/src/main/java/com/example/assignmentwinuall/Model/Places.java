package com.example.assignmentwinuall.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Places {

    @PrimaryKey(autoGenerate = true)
    private Integer Id;

    private String placeName;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    //constructor

    public Places(String placeName) {
        this.placeName = placeName;
    }

    public Places(){

    }
}
