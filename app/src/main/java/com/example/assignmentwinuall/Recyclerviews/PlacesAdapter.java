package com.example.assignmentwinuall.Recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignmentwinuall.Model.Places;
import com.example.assignmentwinuall.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHoler> {
    List<Places> places= new ArrayList<>();

    @NonNull
    @Override
    public PlacesViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.place_name_item_layout, viewGroup, false);
        return new PlacesViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHoler placesViewHoler, int i) {
        Places currentPlace = places.get(i);
        placesViewHoler.placeNameText.setText(currentPlace.getPlaceName());

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setPlaces(List<Places> places){
        this.places = places;
        notifyDataSetChanged();
    }

    class PlacesViewHoler extends RecyclerView.ViewHolder{

        private TextView placeNameText;

        public PlacesViewHoler(@NonNull View itemView) {
            super(itemView);
            placeNameText = itemView.findViewById(R.id.placeName);
        }
    }
}
