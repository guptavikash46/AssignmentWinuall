package com.example.assignmentwinuall.Recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignmentwinuall.DataClass;
import com.example.assignmentwinuall.R;
import com.example.assignmentwinuall.ShowWeatherData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHoler> {
   private DataClass weatherData;

    @NonNull
    @Override
    public WeatherViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_weather_item_layout
        , viewGroup, false);
        return new WeatherViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHoler weatherViewHoler, int i) {
        weatherViewHoler.placeNameText.setText("City: "+weatherData.getCityName());
        weatherViewHoler.windText.setText("Wind speed: "+weatherData.getSpeed() + " km/hr");
        weatherViewHoler.tempText.setText("Temp: "+weatherData.getTemp()+" °C");
        weatherViewHoler.pressureText.setText("Pressure: "+weatherData.getPressure());
        weatherViewHoler.humidityText.setText("Humidity: "+weatherData.getHumidity()+"%");
        //weatherViewHoler.weatherDescText.setText(String.valueOf(weatherData.getWeather()));
    }

    @Override
    public int getItemCount() {
        return 1;
    }
//
    public void setWeatherData(DataClass dataClass) {
        this.weatherData = dataClass;
        notifyDataSetChanged();
    }


    class WeatherViewHoler extends RecyclerView.ViewHolder{

        private TextView placeNameText;
        private TextView windText;
        private TextView tempText;
        private TextView pressureText;
        private TextView humidityText;

        public WeatherViewHoler(@NonNull View itemView) {
            super(itemView);
            placeNameText = itemView.findViewById(R.id.cityName);
            windText = itemView.findViewById(R.id.windSpeed);
            tempText = itemView.findViewById(R.id.temperature);
            pressureText = itemView.findViewById(R.id.pressure);
            humidityText = itemView.findViewById(R.id.humidity);
        }
    }
}
