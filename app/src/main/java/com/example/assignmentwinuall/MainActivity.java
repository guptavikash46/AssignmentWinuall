package com.example.assignmentwinuall;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.assignmentwinuall.Model.Places;

import com.example.assignmentwinuall.Model.PlacesDao;
import com.example.assignmentwinuall.Model.PlacesDatabase;
import com.example.assignmentwinuall.Recyclerviews.PlacesAdapter;
import com.example.assignmentwinuall.ViewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends FragmentActivity {
    private ViewModel viewModel;

    private EditText editText;
    RecyclerView placeRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.placeNameEditText);
        placeRecyclerView = findViewById(R.id.mainActivityRecyclerView);
        placeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeRecyclerView.setHasFixedSize(true);
        final PlacesAdapter placesAdapter = new PlacesAdapter();
        placeRecyclerView.setAdapter(placesAdapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllPlaces().observe((LifecycleOwner) this, new Observer<List<Places>>() {
            @Override
            public void onChanged(List<Places> places) {
                placesAdapter.setPlaces(places);
            }
        });
    }
    public void checkWeather(View view){
        String placeText = editText.getText().toString();
        viewModel.insertPlace(new Places(placeText));
        Intent intent = new Intent(this, ShowWeatherData.class);
        intent.putExtra("name", placeText);
        startActivity(intent);
    }
}
