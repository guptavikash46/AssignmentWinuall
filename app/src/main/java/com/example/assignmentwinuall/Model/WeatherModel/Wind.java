package com.example.assignmentwinuall.Model.WeatherModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;

    public double getSpeed() {
        return speed * 3.6;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return " "+speed;
    }
}
