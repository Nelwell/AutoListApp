package com.example.autolist.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/** Creates database on device */
// Implemented as a thread-safe Singleton
@Database(entities = {AutoRecord.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AutoDatabase extends RoomDatabase {

    private static volatile AutoDatabase INSTANCE;

    public abstract AutoDAO autoDAO(); // Abstract method

    static AutoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AutoDatabase.class) { // Only one thread can run this code at once
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AutoDatabase.class, "Automobile").build();
                }
            }
        }
        return INSTANCE;
    }

}
