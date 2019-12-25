package com.morsedecoder.Domain;

import com.morsedecoder.InterfacesForTranslations.Translateable;
import com.morsedecoder.InterfacesForTranslations.Vocabulary;

import java.util.ArrayList;

public class CommonTranslator implements Translateable {

    private Vocabulary vocabulary;

    public CommonTranslator(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    @Override
    public String translateFromMorse(String morseMessage) {
        StringBuilder outputMessage = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();

        char[] MorseLine = morseMessage.toCharArray();
        StringBuilder encryptedLetter = new StringBuilder();

        for (int i = 0; i < MorseLine.length; i++) {
            if (MorseLine[i] != ' ') {
                encryptedLetter.append(MorseLine[i]);
                if (i == MorseLine.length - 1) {
                    list.add(encryptedLetter.toString());
                }
            } else if (i != 0 && MorseLine[i] == ' ') {
                list.add(encryptedLetter.toString());
                encryptedLetter = new StringBuilder();
            }

        }
        for (String eLetter : list) {
            outputMessage.append(vocabulary.getCharValue(eLetter));
        }
        return outputMessage.toString();
    }

    @Override
    public String translateInMorse(String message) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();
        char[] messageAsChars = message.toCharArray();
        for (int i = 0; i < messageAsChars.length; i++) {
            if (i != messageAsChars.length - 1) {
                result.append(vocabulary.getMorseValue(messageAsChars[i])).append(" ");
            } else {
                result.append(vocabulary.getMorseValue(messageAsChars[i]));
            }
        }
        return result.toString();
    }

    @Override
    public String getTranslatedMessage(boolean isFromMorse, String msg) {
        String outputMsg;
        outputMsg = isFromMorse ? translateFromMorse(msg) : translateInMorse(msg);
        return outputMsg;
    }
}

