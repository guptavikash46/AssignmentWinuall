package com.example.assignmentwinuall.API;

import com.example.assignmentwinuall.Model.WeatherModel.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIs {

    @GET("/data/2.5/weather")
    Call<WeatherInfo> getWeatherByCity(@Query("q") String city, @Query("APPID") String apikey);
}
