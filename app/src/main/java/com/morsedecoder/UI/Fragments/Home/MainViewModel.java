package com.morsedecoder.UI.Fragments.Home;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.morsedecoder.Data.EnglishVocabulary;
import com.morsedecoder.Data.RussianVocabulary;
import com.morsedecoder.Data.TranslationResult;
import com.morsedecoder.Data.TranslationsDatabase;
import com.morsedecoder.Domain.CommonTranslator;
import com.morsedecoder.R;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final int SPINNERS_COUNT = 2;
    private static TranslationsDatabase database;
    private LiveData<List<TranslationResult>> translationHistory;
    private CommonTranslator commonTranslator;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = TranslationsDatabase.getInstance(getApplication());
        translationHistory = database.translationDao().getAllTranlationHistory();
    }

    public LiveData<List<TranslationResult>> getTranslationHistory() {
        return translationHistory;
    }

    public void insertTranslationInDB(TranslationResult translationResult){
        new InsertTranslationTask().execute(translationResult);
    }

    public void updateTranslation(TranslationResult translationResult){
        new UpdateTranslationTask().execute(translationResult);
    }

    public void deleteTranslationFromDB(TranslationResult translationResult){
        new DeleteTranslationTask().execute(translationResult);
    }

    public void deleteAllTranslationsFromDB(){
        new ClearAllHistoryTask().execute();
    }


    public String getTranslatedMessage(boolean isFromMorse, String text) {
        String translation = "";
        if (commonTranslator != null) {
            translation = commonTranslator.getTranslatedMessage(isFromMorse, text);
        }
        return translation;
    }

    public void onTranslaterChanged(Context context, String [] spinnerValues) {
       if (spinnerValues != null && spinnerValues.length == SPINNERS_COUNT){

           String [] LANGUAGES = context.getResources().getStringArray(R.array.Languages);
           final String MORSE = LANGUAGES[0];
           final String ENG = LANGUAGES[1];
           final String RUS = LANGUAGES[2];

           String left = spinnerValues[0];
           String right = spinnerValues[1];

           if (left.equals(right) || !left.equals(MORSE) && !right.equals(MORSE)) {
               commonTranslator = null;
               return;
           }
           if (left.equals(ENG) || right.equals(ENG)) {
               commonTranslator = new CommonTranslator(new EnglishVocabulary());
           } else if (left.equals(RUS) || right.equals(RUS)) {
               commonTranslator = new CommonTranslator(new RussianVocabulary());
           }
       }
    }

    private static class InsertTranslationTask extends AsyncTask<TranslationResult, Void, Void>{
        @Override
        protected Void doInBackground(TranslationResult... translationResults) {
            if(translationResults!= null && translationResults.length != 0){
                database.translationDao().insertUserTranslation(translationResults[0]);
            }
            return null;
        }
    }

    private static class UpdateTranslationTask extends AsyncTask<TranslationResult, Void, Void>{
        @Override
        protected Void doInBackground(TranslationResult... translationResults) {
            if(translationResults!= null && translationResults.length != 0){
                database.translationDao().updateTranslation(translationResults[0]);
            }
            return null;
        }
    }


    private static class DeleteTranslationTask extends AsyncTask<TranslationResult, Void, Void>{
        @Override
        protected Void doInBackground(TranslationResult... translationResults) {
            if(translationResults!= null && translationResults.length != 0){
                database.translationDao().deleteTranslation(translationResults[0]);
            }
            return null;
        }
    }

    private static class ClearAllHistoryTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.translationDao().clearAllTranslationHistory();
            return null;
        }
    }



}
