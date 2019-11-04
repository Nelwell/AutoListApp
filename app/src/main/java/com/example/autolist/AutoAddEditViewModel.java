package com.example.autolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.autolist.db.AutoRecord;
import com.example.autolist.db.AutoRepository;

import java.util.List;

/** Passes messages from View to Repository
 */
public class AutoAddEditViewModel extends AndroidViewModel {

    private AutoRepository autoRepository;

    public AutoAddEditViewModel(@NonNull Application application) {
        super(application);
        autoRepository = new AutoRepository(application);
    }

    public LiveData<AutoRecord> getAutoRecord(int autoId) {
        return autoRepository.getRecordForAutoId(autoId);
    }

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
