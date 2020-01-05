package com.morsedecoder.Data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "translationResults")
public class TranslationResult  {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String languageFrom;
    private String languageIn;

    public TranslationResult(int id, String languageFrom, String languageIn) {
        this.id = id;
        this.languageFrom = languageFrom;
        this.languageIn = languageIn;
    }

    @Ignore
    public TranslationResult(String languageFrom, String languageIn) {
        this.languageFrom = languageFrom;
        this.languageIn = languageIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(String languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageIn() {
        return languageIn;
    }

    public void setLanguageIn(String languageIn) {
        this.languageIn = languageIn;
    }
}
