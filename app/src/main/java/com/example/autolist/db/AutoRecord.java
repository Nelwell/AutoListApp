package com.example.autolist.db;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.util.Date;

public class AutoRecord {

    @PrimaryKey(autoGenerate = true)
    private int autoId;
    @NonNull
    private String year;
    private String make;
    private String model;
    private String note;
    private String imageFilePath;
    private Date dateEntered;

    public AutoRecord(@NonNull String year, String make, String model,
                      String note, String imageFilePath, Date dateEntered) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.note = note;
        this.imageFilePath = imageFilePath;
        this.dateEntered = dateEntered;
    }
}
