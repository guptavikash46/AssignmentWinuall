package com.example.assignmentwinuall.Model.WeatherModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherInfo {

//    @SerializedName("weather")
//    @Expose
//    private Weather weather;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("name")
    @Expose
    private String cityName;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    @Override
    public String toString() {
        return "WeatherInfo{" +
                ", main=" + main +
                ", cityName='" + cityName + '\'' +
                ", wind=" + wind +
                '}';
    }
    public WeatherInfo(){

    }

}
