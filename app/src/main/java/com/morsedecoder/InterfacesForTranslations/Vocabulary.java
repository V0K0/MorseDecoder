package com.morsedecoder.InterfacesForTranslations;

public interface Vocabulary {

    String getMorseValue(char key);

    char getCharValue(String morseCode);

}
