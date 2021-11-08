package com.example.android2lesson1.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android2lesson1.ui.create.TaskModel;

@Database(entities = {TaskModel.class},version = 1)
public abstract class DateBase extends RoomDatabase{
    public abstract TaskDao taskDao();
}
