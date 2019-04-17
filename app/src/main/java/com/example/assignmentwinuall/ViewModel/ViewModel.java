package com.example.assignmentwinuall.ViewModel;

import android.app.Application;

import com.example.assignmentwinuall.Model.Places;
import com.example.assignmentwinuall.Repository.PlaceRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {
    private PlaceRepository placeRepository;
    private LiveData<List<Places>> allPlaces;

    public ViewModel(@NonNull Application application) {
        super(application);
        placeRepository = new PlaceRepository(application);
        allPlaces = placeRepository.getAllPlaces();
    }

    public void insertPlace(Places places){
        placeRepository.insert(places);
    }
    public void deletePlaces(Places places){
        placeRepository.delete(places);
    }
    public void deleteAllPlaces(){
        placeRepository.deleteAll();
    }

    public LiveData<List<Places>> getAllPlaces(){
        return allPlaces;
    }


}
