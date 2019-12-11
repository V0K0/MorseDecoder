package com.morsedecoder.Data;

import com.morsedecoder.Abstractions.Vocabulary;

import java.util.HashMap;
import java.util.Map;

public class EnglishVocabulary extends Vocabulary {

    private char[] englishAlphabet = new char[26];
    private char[] symbols = new char[27];

    private String[] morseValuesForEnglishAndSymbols = new String[symbols.length + englishAlphabet.length];
    private Map<Character, String> tableEnglish = new HashMap<>();
    private Map<String, Character> tableMorse = new HashMap<>();

    public EnglishVocabulary() {
        setEnglishAlphabet();
        setSymbols();
        setMorseCodes();
        setTableEng();
        setTableMorse();
    }

    @Override
    public String getMorseValue(char key) {
        if (key == ' ') {
            return "";
        }
        if (tableEnglish.containsKey(key)) {
            return tableEnglish.get(key);
        } else {
            return "";
        }
    }

    @Override
    public char getCharValue(String morseCode) {
        if (morseCode.equals("")) {
            return ' ';
        }
        if (tableMorse.containsKey(morseCode)) {
            return tableMorse.get(morseCode);
        } else {
            return ' ';
        }
    }


    private void setEnglishAlphabet() {
        int asciiStartIndex = 65; //Верхний регистр
        for (int i = 0; i < englishAlphabet.length; i++) {
            englishAlphabet[i] = (char) asciiStartIndex;
            asciiStartIndex++;
        }
    }

    private void setSymbols() {
        symbols[0] = '1';
        symbols[1] = '2';
        symbols[2] = '3';
        symbols[3] = '4';
        symbols[4] = '5';
        symbols[5] = '6';
        symbols[6] = '7';
        symbols[7] = '8';
        symbols[8] = '9';
        symbols[9] = '0';
        symbols[10] = '!';
        symbols[11] = '.';
        symbols[12] = ',';
        symbols[13] = '?';
        symbols[14] = ':';
        symbols[15] = '+';
        symbols[16] = '=';
        symbols[17] = '—';
        symbols[18] = '_';
        symbols[19] = '&';
        symbols[20] = '@';
        symbols[21] = '$';
        symbols[22] = ')';
        symbols[23] = '(';
        symbols[24] = ';';
        symbols[25] = '"';
        symbols[26] = '\'';
    }

    private void setMorseCodes() {
        morseValuesForEnglishAndSymbols[0] = ".-";
        morseValuesForEnglishAndSymbols[1] = "-...";
        morseValuesForEnglishAndSymbols[2] = "-.-.";
        morseValuesForEnglishAndSymbols[3] = "-..";
        morseValuesForEnglishAndSymbols[4] = ".";
        morseValuesForEnglishAndSymbols[5] = "..-.";
        morseValuesForEnglishAndSymbols[6] = "--.";
        morseValuesForEnglishAndSymbols[7] = "....";
        morseValuesForEnglishAndSymbols[8] = "..";
        morseValuesForEnglishAndSymbols[9] = ".---";
        morseValuesForEnglishAndSymbols[10] = "-.-";
        morseValuesForEnglishAndSymbols[11] = ".-..";
        morseValuesForEnglishAndSymbols[12] = "--";
        morseValuesForEnglishAndSymbols[13] = "-.";
        morseValuesForEnglishAndSymbols[14] = "---";
        morseValuesForEnglishAndSymbols[15] = ".--.";
        morseValuesForEnglishAndSymbols[16] = "--.-";
        morseValuesForEnglishAndSymbols[17] = ".-.";
        morseValuesForEnglishAndSymbols[18] = "...";
        morseValuesForEnglishAndSymbols[19] = "-";
        morseValuesForEnglishAndSymbols[20] = "..-";
        morseValuesForEnglishAndSymbols[21] = "...-";
        morseValuesForEnglishAndSymbols[22] = ".--";
        morseValuesForEnglishAndSymbols[23] = "-..-";
        morseValuesForEnglishAndSymbols[24] = "-.--";
        morseValuesForEnglishAndSymbols[25] = "--..";

        // символы дальше

        morseValuesForEnglishAndSymbols[26] = ".----"; // = '1';
        morseValuesForEnglishAndSymbols[27] = "..---"; // = '2';
        morseValuesForEnglishAndSymbols[28] = "...--";  //= '3';
        morseValuesForEnglishAndSymbols[29] = "....-";  //= '4';
        morseValuesForEnglishAndSymbols[30] = ".....";  // = '5';
        morseValuesForEnglishAndSymbols[31] = "-....";  //= '6';
        morseValuesForEnglishAndSymbols[32] = "--...";  //= '7';
        morseValuesForEnglishAndSymbols[33] = "---..";  // = '8';
        morseValuesForEnglishAndSymbols[34] = "----.";  //= '9';
        morseValuesForEnglishAndSymbols[35] = "-----";  //= '0';

        morseValuesForEnglishAndSymbols[36] = "-.-.--";  //= '!';
        morseValuesForEnglishAndSymbols[37] = ".-.-.-";  //= '.';
        morseValuesForEnglishAndSymbols[38] = "--..--";  //= ',';
        morseValuesForEnglishAndSymbols[39] = "..--.."; //= '?';
        morseValuesForEnglishAndSymbols[40] = "---...";  //= ':';
        morseValuesForEnglishAndSymbols[41] = ".-.-.";  //= '+';
        morseValuesForEnglishAndSymbols[42] = "-...-";  //= '=';
        morseValuesForEnglishAndSymbols[43] = "-....-";  //= '—';
        morseValuesForEnglishAndSymbols[44] = "..--.-";  //= '_';
        morseValuesForEnglishAndSymbols[45] = ".-...";  //= '&';
        morseValuesForEnglishAndSymbols[46] = ".--.-.";  //= '@';
        morseValuesForEnglishAndSymbols[47] = "...-..-";  //= '$';
        morseValuesForEnglishAndSymbols[48] = "-.--.-";  //= ')';
        morseValuesForEnglishAndSymbols[49] = "-.--.";  //= '(';
        morseValuesForEnglishAndSymbols[50] = "-.-.-.";  //= ';';
        morseValuesForEnglishAndSymbols[51] = ".-..-.";  //= '"';
        morseValuesForEnglishAndSymbols[52] = ".----.";  //= ' ' ';
    }

    private void setTableEng() {
        for (int i = 0; i < englishAlphabet.length; i++) {
            tableEnglish.put(englishAlphabet[i], morseValuesForEnglishAndSymbols[i]);
        }
        int symbolsIterator = 0;
        for (int j = englishAlphabet.length; j < morseValuesForEnglishAndSymbols.length; j++) {
            tableEnglish.put(symbols[symbolsIterator++], morseValuesForEnglishAndSymbols[j]);
        }
    }

    private void setTableMorse() {
        for (int i = 0; i < englishAlphabet.length; i++) {
            tableMorse.put(morseValuesForEnglishAndSymbols[i], englishAlphabet[i]);
        }
        int symbolsIterator = 0;
        for (int j = englishAlphabet.length; j < morseValuesForEnglishAndSymbols.length; j++) {
            tableMorse.put(morseValuesForEnglishAndSymbols[j], symbols[symbolsIterator++]);
        }
    }
}
