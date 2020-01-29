package com.morsedecoder.Data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "translationResults")
public class TranslationResult  {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timeCreated;
    private String languageFrom;
    private String languageIn;

    public TranslationResult(int id, String languageFrom, String languageIn) {
        this.id = id;
        this.languageFrom = languageFrom.trim().toLowerCase();
        this.languageIn = languageIn.trim().toLowerCase();
        timeCreated = (System.currentTimeMillis() / 1000L);
    }

    @Ignore
    public TranslationResult(String languageFrom, String languageIn) {
        this.languageFrom = languageFrom.trim().toLowerCase();
        this.languageIn = languageIn.trim().toLowerCase();
        timeCreated = (System.currentTimeMillis() / 1000L);
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = (timeCreated / 1000L);
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
