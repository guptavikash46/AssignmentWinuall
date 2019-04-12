package com.example.assignmentwinuall.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Places.class}, version = 1)
public abstract class PlacesDatabase extends RoomDatabase {

    private static PlacesDatabase instance;

    public abstract PlacesDao placesDao();

    public static synchronized PlacesDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlacesDatabase.class, "places_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private PlacesDao placesDao;

        private PopulateDbAsyncTask(PlacesDatabase database){
            placesDao = database.placesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            placesDao.insert(new Places("Dortmund"));
            placesDao.insert(new Places("Frankfurt"));
            placesDao.insert(new Places("London"));
            placesDao.insert(new Places("New Delhi"));
            placesDao.insert(new Places("Mumbai"));
            return null;
        }
    }
}
