package com.example.assignmentwinuall.Model.WeatherModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Main {


    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    public Double getTemp() {
        return temp - 273.15;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return temp+" "+pressure+" "+humidity;
    }


}
