package com.morsedecoder.Data;

import com.morsedecoder.InterfacesForTranslations.Vocabulary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnglishVocabulary implements Vocabulary {

    private List<Character> englishAlphabet = new ArrayList<>();
    private List<Character> symbols = new ArrayList<>();
    private List<String> morseValuesForEnglishAndSymbols = new ArrayList<>();

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

    @SuppressWarnings("ConstantConditions")
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
        for (int i = 0; i < 26; i++) {
            englishAlphabet.add((char) asciiStartIndex);
            asciiStartIndex++;
        }
    }

    private void setSymbols() {
        symbols.add('1');
        symbols.add('2');
        symbols.add('3');
        symbols.add('4');
        symbols.add('5');
        symbols.add('6');
        symbols.add('7');
        symbols.add('8');
        symbols.add('9');
        symbols.add('0');
        symbols.add('!');
        symbols.add('.');
        symbols.add(',');
        symbols.add('?');
        symbols.add(':');
        symbols.add('+');
        symbols.add('=');
        symbols.add('—');
        symbols.add('_');
        symbols.add('&');
        symbols.add('@');
        symbols.add('$');
        symbols.add(')');
        symbols.add('(');
        symbols.add(';');
        symbols.add('"');
        symbols.add('\'');
    }

    private void setMorseCodes() {
        morseValuesForEnglishAndSymbols.add(".-");
        morseValuesForEnglishAndSymbols.add("-...");
        morseValuesForEnglishAndSymbols.add("-.-.");
        morseValuesForEnglishAndSymbols.add("-..");
        morseValuesForEnglishAndSymbols.add(".");
        morseValuesForEnglishAndSymbols.add("..-.");
        morseValuesForEnglishAndSymbols.add("--.");
        morseValuesForEnglishAndSymbols.add("....");
        morseValuesForEnglishAndSymbols.add("..");
        morseValuesForEnglishAndSymbols.add(".---");
        morseValuesForEnglishAndSymbols.add("-.-");
        morseValuesForEnglishAndSymbols.add(".-..");
        morseValuesForEnglishAndSymbols.add("--");
        morseValuesForEnglishAndSymbols.add("-.");
        morseValuesForEnglishAndSymbols.add("---");
        morseValuesForEnglishAndSymbols.add(".--.");
        morseValuesForEnglishAndSymbols.add("--.-");
        morseValuesForEnglishAndSymbols.add(".-.");
        morseValuesForEnglishAndSymbols.add("...");
        morseValuesForEnglishAndSymbols.add("-");
        morseValuesForEnglishAndSymbols.add("..-");
        morseValuesForEnglishAndSymbols.add("...-");
        morseValuesForEnglishAndSymbols.add(".--");
        morseValuesForEnglishAndSymbols.add("-..-");
        morseValuesForEnglishAndSymbols.add("-.--");
        morseValuesForEnglishAndSymbols.add("--..");

        // symbols

        morseValuesForEnglishAndSymbols.add(".----"); // = '1';
        morseValuesForEnglishAndSymbols.add("..---"); // = '2';
        morseValuesForEnglishAndSymbols.add("...--");  //= '3';
        morseValuesForEnglishAndSymbols.add("....-");  //= '4';
        morseValuesForEnglishAndSymbols.add(".....");  // = '5';
        morseValuesForEnglishAndSymbols.add("-....");  //= '6';
        morseValuesForEnglishAndSymbols.add("--...");  //= '7';
        morseValuesForEnglishAndSymbols.add("---..");  // = '8';
        morseValuesForEnglishAndSymbols.add("----.");  //= '9';
        morseValuesForEnglishAndSymbols.add("-----");  //= '0';

        morseValuesForEnglishAndSymbols.add("-.-.--");  //= '!';
        morseValuesForEnglishAndSymbols.add(".-.-.-");  //= '.';
        morseValuesForEnglishAndSymbols.add("--..--");  //= ',';
        morseValuesForEnglishAndSymbols.add("..--.."); //= '?';
        morseValuesForEnglishAndSymbols.add("---...");  //= ':';
        morseValuesForEnglishAndSymbols.add(".-.-.");  //= '+';
        morseValuesForEnglishAndSymbols.add("-...-");  //= '=';
        morseValuesForEnglishAndSymbols.add("-....-");  //= '—';
        morseValuesForEnglishAndSymbols.add("..--.-");  //= '_';
        morseValuesForEnglishAndSymbols.add(".-...");  //= '&';
        morseValuesForEnglishAndSymbols.add(".--.-.");  //= '@';
        morseValuesForEnglishAndSymbols.add("...-..-");  //= '$';
        morseValuesForEnglishAndSymbols.add("-.--.-");  //= ')';
        morseValuesForEnglishAndSymbols.add("-.--.");  //= '(';
        morseValuesForEnglishAndSymbols.add("-.-.-.");  //= ';';
        morseValuesForEnglishAndSymbols.add(".-..-.");  //= '"';
        morseValuesForEnglishAndSymbols.add(".----.");  //= ' ' ';
    }

    private void setTableEng() {
        for (int i = 0; i < englishAlphabet.size(); i++) {
            tableEnglish.put(englishAlphabet.get(i), morseValuesForEnglishAndSymbols.get(i));
        }
        int symbolsIterator = 0;
        for (int j = englishAlphabet.size(); j < morseValuesForEnglishAndSymbols.size(); j++) {
            tableEnglish.put(symbols.get(symbolsIterator++), morseValuesForEnglishAndSymbols.get(j));
        }
    }

    private void setTableMorse() {
        for (int i = 0; i < englishAlphabet.size(); i++) {
            tableMorse.put(morseValuesForEnglishAndSymbols.get(i), englishAlphabet.get(i));
        }
        int symbolsIterator = 0;
        for (int j = englishAlphabet.size(); j < morseValuesForEnglishAndSymbols.size(); j++) {
            tableMorse.put(morseValuesForEnglishAndSymbols.get(j), symbols.get(symbolsIterator++));
        }
    }
}