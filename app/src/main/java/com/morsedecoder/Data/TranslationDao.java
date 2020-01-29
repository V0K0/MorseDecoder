package com.morsedecoder.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TranslationDao {

    @Query("SELECT * FROM translationResults ORDER BY timeCreated DESC")
    LiveData<List<TranslationResult>> getAllTranlationHistory();

    @Query("DELETE FROM translationResults")
    void clearAllTranslationHistory();

    @Update
    void updateTranslation(TranslationResult translation);

    @Delete
    void deleteTranslation(TranslationResult result);

    @Insert
    void insertUserTranslation(TranslationResult result);

}
