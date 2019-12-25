package com.morsedecoder.InterfacesForTranslations;

public interface Translateable {

    String translateFromMorse(String morseMessage);

    String translateInMorse(String message);

    String getTranslatedMessage(boolean isFromMorse, String message);

}
