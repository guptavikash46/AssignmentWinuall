package com.example.assignmentwinuall.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.assignmentwinuall.MainActivity;
import com.example.assignmentwinuall.Model.Places;
import com.example.assignmentwinuall.Model.PlacesDao;
import com.example.assignmentwinuall.Model.PlacesDatabase;
import com.example.assignmentwinuall.ShowWeatherData;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PlaceRepository {
    private PlacesDao placesDao;
    private LiveData<List<Places>> allPlaces;

    public PlaceRepository(Application application){
        PlacesDatabase database = PlacesDatabase.getInstance(application);
        placesDao = database.placesDao();
        allPlaces =  placesDao.getAllPlaces();
    }

    public void insert(Places places){
        new InsertPlacesAsyncTask(placesDao).execute(places);
    }
    public void delete(Places places){
        new DeletePlacesAsyncTask(placesDao).execute(places);
    }
    public void deleteAll(){
        new DeleteAllPlacesAsyncTask(placesDao).execute();
    }

    public LiveData<List<Places>> getAllPlaces() {
        return allPlaces;
    }

    private static class InsertPlacesAsyncTask extends AsyncTask<Places, Void, Void>{
        private PlacesDao placesDao;

        private InsertPlacesAsyncTask(PlacesDao placesDao){
            this.placesDao = placesDao;
        }
        @Override
        protected Void doInBackground(Places... places) {
            placesDao.insert(places[0]);

            return null;
        }
    }
    private static class DeletePlacesAsyncTask extends AsyncTask<Places, Void, Void>{
        private PlacesDao placesDao;

        private DeletePlacesAsyncTask(PlacesDao placesDao){
            this.placesDao = placesDao;
        }
        @Override
        protected Void doInBackground(Places... places) {
            placesDao.delete(places[0]);

            return null;
        }
    }
    private static class DeleteAllPlacesAsyncTask extends AsyncTask<Void, Void, Void>{
        private PlacesDao placesDao;

        private DeleteAllPlacesAsyncTask(PlacesDao placesDao){
            this.placesDao = placesDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            placesDao.deleteAllPlaces();

            return null;
        }
    }
}
