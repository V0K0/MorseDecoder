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
    LiveData<List<TranslationResultItem>> getAllTranslationHistory();

    @Query("SELECT * FROM translationResults WHERE isFavourite != 0 ORDER BY timeCreated DESC")
    LiveData<List<TranslationResultItem>> getAllFavouriteTranslations();

    @Query("DELETE FROM translationResults")
    void clearAllTranslationHistory();

    @Update
    void updateTranslation(TranslationResultItem translation);

    @Delete
    void deleteTranslation(TranslationResultItem result);

    @Insert
    void insertUserTranslation(TranslationResultItem result);

}
