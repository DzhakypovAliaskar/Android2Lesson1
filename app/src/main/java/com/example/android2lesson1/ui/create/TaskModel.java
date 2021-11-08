package com.example.android2lesson1.ui.create;

import java.io.Serializable;

public class TaskModel implements Serializable {
    int color;
    String title;
    String time;
    String image;

    public TaskModel(int color, String title, String time, String image) {
        this.color = color;
        this.title = title;
        this.time = time;
        this.image = image;
    }
}
