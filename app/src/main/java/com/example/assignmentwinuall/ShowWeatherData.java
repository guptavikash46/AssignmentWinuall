package com.example.assignmentwinuall;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.assignmentwinuall.API.WeatherAPIs;
import com.example.assignmentwinuall.Model.Places;
import com.example.assignmentwinuall.Model.PlacesDao;
import com.example.assignmentwinuall.Model.PlacesDatabase;
import com.example.assignmentwinuall.Model.WeatherModel.WeatherInfo;
import com.example.assignmentwinuall.Recyclerviews.WeatherAdapter;

import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowWeatherData extends AppCompatActivity {
    private Retrofit retrofit;
    public String place;
    private WeatherAdapter weatherAdapter;
    private WeatherAdapter weatherAdapter2;
    private SharedPreferences weatherData;
    private SharedPreferences placeStamp;
    private SharedPreferences timeStamp;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editorTimeStamp;
    private SharedPreferences.Editor editorPlaceStamp;
    private WeatherInfo weatherInfo;
    Boolean check = false;
    List<Places> placesList;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        weatherData = getSharedPreferences("weatherDataFile", Context.MODE_PRIVATE);
        timeStamp = getSharedPreferences("timeStampFile", Context.MODE_PRIVATE);
        placeStamp = getSharedPreferences("placeStampFile", Context.MODE_PRIVATE);
        editor = weatherData.edit();
        editorTimeStamp = timeStamp.edit();
        editorPlaceStamp = placeStamp.edit();
        setContentView(R.layout.activity_show_weather_data);

        place  = getIntent().getStringExtra("name");
        editorPlaceStamp.putString("placeName", place);

        Calendar calendar  = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Long currentTime = calendar.getTimeInMillis();
        //Getting the current time stamp here.
        Long lastTime = timeStamp.getLong("timeStampinMillis", calendar.getTimeInMillis());
        Long timeDifference = currentTime - lastTime;

        PlacesDatabase database = PlacesDatabase.getInstance(getApplicationContext());
        PlacesDao placesDao1 = database.placesDao();
        new GetSomePlacesAsyncTask(placesDao1).execute(place);

        //This case represents case when the call is made within 24 hours, hence it will show data from Shared preferences.
        //There are 86400000 milli-seconds in a day. -Vikas
        if (check && timeDifference < 86400000){
            RecyclerView recyclerView = findViewById(R.id.showDataRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(ShowWeatherData.this));
            recyclerView.setHasFixedSize(true);
            weatherAdapter2 = new WeatherAdapter();
            recyclerView.setAdapter(weatherAdapter2);

            DataClass dataClassOld = new DataClass();
            String city = weatherData.getString("cityName", "problem?");
            Double windSpeed =  Double.parseDouble(weatherData.getString("windSpeed", "-1.32"));
            Double temp =  Double.parseDouble(weatherData.getString("temp", "-3000.09"));
            Double pressure =  Double.parseDouble(weatherData.getString("pressure", "1000000.00"));
            Integer humidity = Integer.parseInt(weatherData.getString("humidity", "-1"));

            dataClassOld.setCityName(city);
            dataClassOld.setSpeed(windSpeed);
            dataClassOld.setTemp(temp);
            dataClassOld.setPressure(pressure);
            dataClassOld.setHumidity(humidity);

            weatherAdapter2.setWeatherData(dataClassOld);

        }
        //Or else make a new network request.
        else {
            fetchWeatherDetails();
        }

    }

    //I love asyncTask, its the godfather of performing tasks in the background. Some things never change haha. - Vikas Gupta
    public class GetSomePlacesAsyncTask extends AsyncTask<String, Void, List<Places>>{
        private PlacesDao placesDao;
        private List<Places> placesList1;

        private GetSomePlacesAsyncTask(PlacesDao placesDao){
            this.placesDao = placesDao;
        }
        @Override
        protected List<Places> doInBackground(String... strings) {
            placesList1 = placesDao.getplace(strings[0]);
            return placesList1;
        }

        @Override
        protected void onPostExecute(List<Places> places) {
            super.onPostExecute(places);
            for(Places places1: places){
                String cityName = places1.getPlaceName();
                if (cityName.equals(place))
                    check = true;
                break;
            }
        }
    }

    public Retrofit universalRetrofitCode() {
        final String BASE_URL = "http://api.openweathermap.org";
        if (retrofit == null) {
            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build();
        }
        return retrofit;
    }

    public void fetchWeatherDetails() {
        Retrofit retrofit = universalRetrofitCode();
        WeatherAPIs weatherAPIs = retrofit.create(WeatherAPIs.class);
        Call<WeatherInfo> call = weatherAPIs.getWeatherByCity(place, "bf39317d7e1e90fb370cbe555ccd71ab");

        //Getting the time stamp at the time of making the network request. -Vikas Gupta
        Calendar calendar2 = Calendar.getInstance();
        editorTimeStamp.putLong("timeStampinMillis", calendar2.getTimeInMillis());

        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if (response.body() != null) {
                    WeatherInfo wResponse = response.body();
                    weatherInfo = wResponse;
                    DataClass dataClass = new DataClass();

                    dataClass.setCityName(wResponse.getCityName());
                    dataClass.setSpeed(wResponse.getWind().getSpeed());
                    dataClass.setTemp(wResponse.getMain().getTemp());
                    dataClass.setPressure(wResponse.getMain().getPressure());
                    dataClass.setHumidity(wResponse.getMain().getHumidity());

                    RecyclerView recyclerView = findViewById(R.id.showDataRecyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ShowWeatherData.this));
                    recyclerView.setHasFixedSize(true);
                    weatherAdapter = new WeatherAdapter();
                    recyclerView.setAdapter(weatherAdapter);
                    weatherAdapter.setWeatherData(dataClass);

                    new backgroundStorage().execute();
                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class backgroundStorage extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            editor.putString("cityName", weatherInfo.getCityName());
            editor.putString("windSpeed", String.valueOf(weatherInfo.getWind().getSpeed()));
            editor.putString("temp", String.valueOf(weatherInfo.getMain().getTemp()));
            editor.putString("pressure", String.valueOf(weatherInfo.getMain().getPressure()));
            editor.putString("humidity", String.valueOf(weatherInfo.getMain().getHumidity()));
            editor.commit();

            return null;
        }
    }
}
