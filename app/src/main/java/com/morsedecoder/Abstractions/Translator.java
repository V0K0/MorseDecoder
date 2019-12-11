package com.morsedecoder.Abstractions;

public abstract class Translator {

    public abstract String translateFromMorse(String morseMessage);

    public abstract String translateInMorse(String message);

    public abstract String getTranslatedMessage(boolean isFromMorse, String message);
}
