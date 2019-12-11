package com.morsedecoder.Domain;

import com.morsedecoder.Abstractions.Translator;
import com.morsedecoder.Abstractions.Vocabulary;

import java.util.ArrayList;

public class CommonTranslator extends Translator {
    private Vocabulary vocabulary;


    public CommonTranslator(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    @Override
    public String translateFromMorse(String morseMessage) {
        StringBuilder outputMessage = new StringBuilder();
        ArrayList<String> list = new  ArrayList<String>();

        char[] MorseLine = morseMessage.toCharArray();
        StringBuilder EncryptedLetter = new StringBuilder();

        for(int i = 0; i < MorseLine.length; i++){
            if(MorseLine[i] != ' '){
                EncryptedLetter.append(MorseLine[i]);
                if (i == MorseLine.length - 1){
                    list.add(EncryptedLetter.toString());
                }
            }
            else if (i != 0 && MorseLine[i] == ' '){
                list.add(EncryptedLetter.toString());
                EncryptedLetter = new StringBuilder();
            }

        }
        for (String ELetter : list) {
            outputMessage.append(vocabulary.getCharValue(ELetter));
        }
        return outputMessage.toString();
    }


    @Override
    public String translateInMorse(String message) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();
        char charArray[] = message.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                result.append(vocabulary.getMorseValue(charArray[i]) + " ");
            } else {
                result.append(vocabulary.getMorseValue(charArray[i]));
            }
        }
        return result.toString();
    }

    @Override
    public String getTranslatedMessage(boolean isFromMorse, String msg) {
        String outputMsg;
        if (isFromMorse){
            outputMsg = translateFromMorse(msg);
        } else {
            outputMsg = translateInMorse(msg);
        }
        return outputMsg;
    }
}

