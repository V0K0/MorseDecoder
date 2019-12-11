package com.morsedecoder.Abstractions;

public abstract class Vocabulary {

    public abstract String getMorseValue(char key);

    public abstract char getCharValue(String morseCode);

}
