package com.example.autolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.autolist.db.AutoRecord;
import com.example.autolist.db.AutoRepository;

import java.util.List;

/** Passes messages from View to Repository and vice versa
 */
public class AutoListViewModel extends AndroidViewModel {

    private AutoRepository autoRepository;
    // Common to cache a copy of results of common queries here
    private LiveData<List<AutoRecord>> autoListRecords;

    public AutoListViewModel(@NonNull Application application) {
        super(application);
        autoRepository = new AutoRepository(application);
        autoListRecords = autoRepository.getAllAutoRecords();
    }

    public LiveData<List<AutoRecord>> getAllAutoRecords() {
        return autoListRecords;
    }

//    public LiveData<AutoRecord> getRecordForName(String name) { //TODO what to do?
//        return autoRepository.getRecordForName(name);
//    }

    public void insert(AutoRecord auto) {
        autoRepository.insert(auto);
    }

    public void delete(AutoRecord autoRecord) {
        autoRepository.delete(autoRecord);
    }

//    public void delete(int id) {
//        autoRepository.delete(id);
//    }
}
