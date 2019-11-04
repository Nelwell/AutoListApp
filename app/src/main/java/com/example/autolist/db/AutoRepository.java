package com.example.autolist.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/** Where app will access needed data */
// Define methods app will call to query database
public class AutoRepository {

    private AutoDAO mAutoDAO;

    public AutoRepository(Application application) {
        AutoDatabase db = AutoDatabase.getDatabase(application);
        mAutoDAO = db.autoDAO();
    }

    // LiveData can wrap one item or a list of items inside it,
    // let's you know if something in db changes
    public LiveData<List<AutoRecord>> getAllAutoRecords() {
        return mAutoDAO.getAllAutoRecords();
    }

    public LiveData<AutoRecord> getRecordForAutoId(int autoId) {
        return mAutoDAO.getRecordForAutoId(autoId);
    }

    public void insert(AutoRecord autoRecord) {
        // Insert record asynchronously in the background so other processes can continue seamlessly
        new InsertAutoAsync(mAutoDAO).execute(autoRecord); // when called, calls doInBackground
        // method automatically and passes 'autoRecord' arg
    }

    public void delete(AutoRecord autoRecord) {
        // Update record asynchronously in the background
        new DeleteAutoAsync(mAutoDAO).execute(autoRecord); // when called, calls doInBackground
        // method automatically and passes 'autoRecord' arg
    }

//    public void delete(int id) {
//        new DeleteAutoIDAsyncTask(mAutoDAO).execute(id); }


    // Database tasks must run the background, not on the UI thread
    static class InsertAutoAsync extends AsyncTask<AutoRecord, Void, Void> {

        private AutoDAO dao;

        // Constructor
        InsertAutoAsync(AutoDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AutoRecord... autos) {
            dao.insert(autos[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    static class DeleteAutoAsync extends AsyncTask<AutoRecord, Void, Void> {

        private AutoDAO dao;

        // Constructor
        DeleteAutoAsync(AutoDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AutoRecord... autos) {
            dao.delete(autos[0]);
            return null;
        }
    }

//    private static class DeleteAutoIDAsyncTask extends AsyncTask<Integer, Void, Void> {
//
//        AutoDAO dao;
//
//        public DeleteAutoIDAsyncTask(AutoDAO dao) {
//            this.dao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(Integer... id) {
//            dao.delete(id[0]);
//            return null;
//        }
//    }

}
