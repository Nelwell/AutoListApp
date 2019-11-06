package com.example.autolist.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
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

    public String getTitle() {
        return String.format("%s %s %s", year, make, model);
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    @NonNull
    public String getYear() {
        return year;
    }

    public void setYear(@NonNull String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    @Override
    public String toString() {
        return "AutoRecord{" +
                "autoId=" + autoId +
                ", year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", note='" + note + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                ", dateEntered=" + dateEntered +
                '}';
    }
}
