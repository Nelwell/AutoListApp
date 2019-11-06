package com.example.autolist.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/** Where app will access needed data */
// Define methods app will call to query database, provide clean API for rest of app.
// Can receive data from multiple resources
public class AutoRepository {

    private AutoDAO mAutoDAO;
//    private LiveData<List<AutoRecord>> mAllAutoRecords;

    public AutoRepository(Application application) {
        AutoDatabase db = AutoDatabase.getDatabase(application);
        mAutoDAO = db.autoDAO();
//        mAllAutoRecords = mAutoDAO.getAllAutoRecords();
    }

    // LiveData can wrap one item or a list of items inside it.
    // Let's you know if something in db changes
    public LiveData<List<AutoRecord>> getAllAutoRecords() {
        return mAutoDAO.getAllAutoRecords();
    }

    public LiveData<AutoRecord> getRecordForAutoId(int autoId) {
        return mAutoDAO.getRecordForAutoId(autoId);
    }

    public void insert(AutoRecord autoRecord) {
        // Insert record asynchronously in the background so other processes can continue seamlessly
        new InsertAutoAsyncTask(mAutoDAO).execute(autoRecord); // when called, calls doInBackground
        // method automatically and passes 'autoRecord' arg
    }

    public void delete(AutoRecord autoRecord) {
        // Update record asynchronously in the background
        new DeleteAutoAsyncTask(mAutoDAO).execute(autoRecord); // when called, calls doInBackground
        // method automatically and passes 'autoRecord' arg
    }

    public void deleteAllAutoRecords() {
        new DeleteAllAutosAsyncTask(mAutoDAO).execute();
    }

//    public void delete(int id) {
//        new DeleteAutoIDAsyncTask(mAutoDAO).execute(id); }


    // Database tasks must run the background, not on the UI thread
    private static class InsertAutoAsyncTask extends AsyncTask<AutoRecord, Void, Void> {

        private AutoDAO autoDAO;

        // Constructor
        private InsertAutoAsyncTask(AutoDAO autoDAO) {
            this.autoDAO = autoDAO;
        }

        @Override
        protected Void doInBackground(AutoRecord... autos) {
            autoDAO.insert(autos[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    private static class DeleteAutoAsyncTask extends AsyncTask<AutoRecord, Void, Void> {

        private AutoDAO dao;

        // Constructor
        private DeleteAutoAsyncTask(AutoDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AutoRecord... autos) {
            dao.delete(autos[0]);
            return null;
        }
    }

    // Database tasks must run in the background, not on the UI thread
    private static class DeleteAllAutosAsyncTask extends AsyncTask<Void, Void, Void> {

        private AutoDAO dao;

        // Constructor
        private DeleteAllAutosAsyncTask(AutoDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllAutoRecords();
            return null;
        }
    }

//    private static class DeleteAutoIDAsyncTask extends AsyncTask<Integer, Void, Void> {
//
//        AutoDAO autoDAO;
//
//        public DeleteAutoIDAsyncTask(AutoDAO autoDAO) {
//            this.autoDAO = autoDAO;
//        }
//
//        @Override
//        protected Void doInBackground(Integer... id) {
//            autoDAO.delete(id[0]);
//            return null;
//        }
//    }

}
