package com.morsedecoder.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TranslationDao {

    @Query("SELECT * FROM translationResults ORDER BY id DESC")
    LiveData<List<TranslationResult>> getAlltranlationHistory();

    @Query("DELETE FROM translationResults")
    void clearAllTranslationHistory();

    @Delete
    void deleteTranslation(TranslationResult result);

    @Insert
    void insertUserTranslation(TranslationResult result);

}
