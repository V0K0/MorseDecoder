package com.morsedecoder.UI.Activities.Main;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.morsedecoder.Data.TranslationResultItem;
import com.morsedecoder.Data.TranslationsDatabase;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    private static TranslationsDatabase database;
    private LiveData<List<TranslationResultItem>> favouriteTranslations;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        database = TranslationsDatabase.getInstance(getApplication());
        favouriteTranslations = database.translationDao().getAllFavouriteTranslations();
    }

    public LiveData<List<TranslationResultItem>> getFavouriteTranslations() {
        return favouriteTranslations;
    }

    public void updateTranslation(TranslationResultItem translationResult){
        new UpdateTranslationTask().execute(translationResult);
    }


    private static class UpdateTranslationTask extends AsyncTask<TranslationResultItem, Void, Void> {
        @Override
        protected Void doInBackground(TranslationResultItem... translationResults) {
            if(translationResults!= null && translationResults.length != 0){
                database.translationDao().updateTranslation(translationResults[0]);
            }
            return null;
        }
    }

}
