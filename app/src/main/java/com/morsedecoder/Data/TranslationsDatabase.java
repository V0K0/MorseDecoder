package com.morsedecoder.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TranslationResult.class}, version = 1, exportSchema = false)
public abstract class TranslationsDatabase extends RoomDatabase {

    private static TranslationsDatabase database;
    private static final String DB_NAME = "history.db";
    private static final Object LOCK = new Object();

    public static TranslationsDatabase getInstance(Context context){
        synchronized (LOCK){
            if (database == null){
                database = Room.databaseBuilder(context, TranslationsDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }


    public abstract TranslationDao translationDao();

}
