package com.example.android2lesson1.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android2lesson1.ui.create.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM taskmodel")
    List<TaskModel> getAll();

    @Insert
    void insert(TaskModel taskModel);

    @Delete
    void delete(TaskModel model);
}
