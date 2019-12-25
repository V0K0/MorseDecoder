package com.morsedecoder.Data;

import com.morsedecoder.InterfacesForTranslations.Vocabulary;

import java.util.HashMap;
import java.util.Map;

public class RussianVocabulary implements Vocabulary {

    private char[] symbols = new char[25];
    private char[] russianAlphabet = new char[32];
    private String[] morseValuesForRussianAndSymbols = new String[symbols.length + russianAlphabet.length];
    private Map<Character, String> tableRussian = new HashMap<>();
    private Map<String, Character> tableMorse = new HashMap<>();

    public RussianVocabulary() {
        setRussianAlphabet();
        setSymbols();
        setMorseCodes();
        setTableRussian();
        setTableMorse();
    }

    @Override
    public String getMorseValue(char key) {
        if (key == ' ') {
            return "";
        }
        if (tableRussian.containsKey(key)) {
            return tableRussian.get(key);
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
        symbols[22] = ';';
        symbols[23] = '"';
        symbols[24] = '\'';
    }

    private void setRussianAlphabet() {
        int iterator = 0;
        for (char c = 'А'; c <= 'Я'; c++) {
            russianAlphabet[iterator] = c;
            iterator++;
        }
    }

    private void setMorseCodes() {
        morseValuesForRussianAndSymbols[0] = ".-";
        morseValuesForRussianAndSymbols[1] = "-...";
        morseValuesForRussianAndSymbols[2] = ".--";
        morseValuesForRussianAndSymbols[3] = "--.";
        morseValuesForRussianAndSymbols[4] = "-..";
        morseValuesForRussianAndSymbols[5] = ".";
        morseValuesForRussianAndSymbols[6] = "...-";
        morseValuesForRussianAndSymbols[7] = "--..";
        morseValuesForRussianAndSymbols[8] = "..";
        morseValuesForRussianAndSymbols[9] = ".---";
        morseValuesForRussianAndSymbols[10] = "-.-";
        morseValuesForRussianAndSymbols[11] = ".-..";
        morseValuesForRussianAndSymbols[12] = "--";
        morseValuesForRussianAndSymbols[13] = "-.";
        morseValuesForRussianAndSymbols[14] = "---";
        morseValuesForRussianAndSymbols[15] = ".--.";
        morseValuesForRussianAndSymbols[16] = ".-.";
        morseValuesForRussianAndSymbols[17] = "...";
        morseValuesForRussianAndSymbols[18] = "-";
        morseValuesForRussianAndSymbols[19] = "..-";
        morseValuesForRussianAndSymbols[20] = "..-.";
        morseValuesForRussianAndSymbols[21] = "....";
        morseValuesForRussianAndSymbols[22] = "-.-.";
        morseValuesForRussianAndSymbols[23] = "---.";
        morseValuesForRussianAndSymbols[24] = "----";
        morseValuesForRussianAndSymbols[25] = "--.-";
        morseValuesForRussianAndSymbols[26] = "--.--";
        morseValuesForRussianAndSymbols[27] = "-.--";
        morseValuesForRussianAndSymbols[28] = "-..-";
        morseValuesForRussianAndSymbols[29] = "..-..";
        morseValuesForRussianAndSymbols[30] = "..--";
        morseValuesForRussianAndSymbols[31] = ".-.-";
        // Symbols
        morseValuesForRussianAndSymbols[32] = ".----"; // = '1';
        morseValuesForRussianAndSymbols[33] = "..---"; // = '2';
        morseValuesForRussianAndSymbols[34] = "...--";  //= '3';
        morseValuesForRussianAndSymbols[35] = "....-";  //= '4';
        morseValuesForRussianAndSymbols[36] = ".....";  // = '5';
        morseValuesForRussianAndSymbols[37] = "-....";  //= '6';
        morseValuesForRussianAndSymbols[38] = "--...";  //= '7';
        morseValuesForRussianAndSymbols[39] = "---..";  // = '8';
        morseValuesForRussianAndSymbols[40] = "----.";  //= '9';
        morseValuesForRussianAndSymbols[41] = "-----";  //= '0';
        morseValuesForRussianAndSymbols[42] = "--..--"; //= '!'
        morseValuesForRussianAndSymbols[43] = "......"; //= '.';
        morseValuesForRussianAndSymbols[44] = ".-.-.-"; //= ',';
        morseValuesForRussianAndSymbols[45] = "..--.."; //= '?';
        morseValuesForRussianAndSymbols[46] = "---..."; //= ':';
        morseValuesForRussianAndSymbols[47] = ".-.-."; //= '+';
        morseValuesForRussianAndSymbols[48] = "-...-"; //= '=';
        morseValuesForRussianAndSymbols[49] = "-....-"; //= '—';
        morseValuesForRussianAndSymbols[50] = "..--.-"; //= '_';
        morseValuesForRussianAndSymbols[51] = ".-..."; //= '&';
        morseValuesForRussianAndSymbols[52] = ".--.-."; //= '@';
        morseValuesForRussianAndSymbols[53] = "...-..-"; //= '$';
        morseValuesForRussianAndSymbols[54] = "-.-.-."; //= ';';
        morseValuesForRussianAndSymbols[55] = ".-..-."; //= '"';
        morseValuesForRussianAndSymbols[56] = ".----."; //= ' ' ';
    }

    private void setTableRussian() {
        for (int i = 0; i < russianAlphabet.length; i++) {
            tableRussian.put(russianAlphabet[i], morseValuesForRussianAndSymbols[i]);
        }
        int symbolsIterator = 0;
        for (int j = russianAlphabet.length; j < morseValuesForRussianAndSymbols.length; j++) {
            tableRussian.put(symbols[symbolsIterator++], morseValuesForRussianAndSymbols[j]);
        }
    }

    private void setTableMorse() {
        for (int i = 0; i < russianAlphabet.length; i++) {
            tableMorse.put(morseValuesForRussianAndSymbols[i], russianAlphabet[i]);
        }
        int symbolsIterator = 0;
        for (int j = russianAlphabet.length; j < morseValuesForRussianAndSymbols.length; j++) {
            tableMorse.put(morseValuesForRussianAndSymbols[j], symbols[symbolsIterator++]);
        }
    }
}
