package com.example.android2lesson1.utils;

import android.app.Application;
import android.widget.Toast;

import androidx.room.Room;

import com.example.android2lesson1.room.DateBase;

public class App extends Application {
    public static App instance;
    private DateBase dateBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dateBase = Room.databaseBuilder(this, DateBase.class, "tododatabase").allowMainThreadQueries().build();
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public static App getInstance() {
        return instance;
    }

    public DateBase getDateBase() {
        return dateBase;
    }
}
