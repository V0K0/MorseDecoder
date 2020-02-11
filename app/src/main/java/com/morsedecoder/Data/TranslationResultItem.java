package com.morsedecoder.Data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "translationResults")
public class TranslationResultItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timeCreated;
    private String messageBeforeTranslation;
    private String messageAfterTranslation;
    private boolean isFavourite = false;

    public TranslationResultItem(int id, String messageBeforeTranslation, String messageAfterTranslation) {
        this.id = id;
        this.messageBeforeTranslation = messageBeforeTranslation.trim().toLowerCase();
        this.messageAfterTranslation = messageAfterTranslation.trim().toLowerCase();
        timeCreated = (System.currentTimeMillis() / 1000L);
    }

    @Ignore
    public TranslationResultItem(String messageBeforeTranslation, String messageAfterTranslation) {
        this.messageBeforeTranslation = messageBeforeTranslation.trim().toLowerCase();
        this.messageAfterTranslation = messageAfterTranslation.trim().toLowerCase();
        timeCreated = (System.currentTimeMillis() / 1000L);
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreatedUNIX) {
        this.timeCreated = timeCreatedUNIX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageBeforeTranslation() {
        return messageBeforeTranslation;
    }

    public void setMessageBeforeTranslation(String messageBeforeTranslation) {
        this.messageBeforeTranslation = messageBeforeTranslation;
    }

    public String getMessageAfterTranslation() {
        return messageAfterTranslation;
    }

    public void setMessageAfterTranslation(String messageAfterTranslation) {
        this.messageAfterTranslation = messageAfterTranslation;
    }
}
