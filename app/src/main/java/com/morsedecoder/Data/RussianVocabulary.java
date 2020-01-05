package com.morsedecoder.Data;

import com.morsedecoder.InterfacesForTranslations.Vocabulary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RussianVocabulary implements Vocabulary {

    private List<Character> symbols = new ArrayList<>();
    private List<Character> russianAlphabet = new ArrayList<>();
    private List<String> morseValuesForRussianAndSymbols = new ArrayList<>();
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

    private void setRussianAlphabet() {
        for (char letter = 'А'; letter <= 'Я'; letter++) {
            russianAlphabet.add(letter);
        }
    }

    private void setMorseCodes() {
        morseValuesForRussianAndSymbols.add(".-");
        morseValuesForRussianAndSymbols.add("-...");
        morseValuesForRussianAndSymbols.add(".--");
        morseValuesForRussianAndSymbols.add("--.");
        morseValuesForRussianAndSymbols.add("-..");
        morseValuesForRussianAndSymbols.add(".");
        morseValuesForRussianAndSymbols.add("...-");
        morseValuesForRussianAndSymbols.add("--..");
        morseValuesForRussianAndSymbols.add("..");
        morseValuesForRussianAndSymbols.add(".---");
        morseValuesForRussianAndSymbols.add("-.-");
        morseValuesForRussianAndSymbols.add(".-..");
        morseValuesForRussianAndSymbols.add("--");
        morseValuesForRussianAndSymbols.add("-.");
        morseValuesForRussianAndSymbols.add("---");
        morseValuesForRussianAndSymbols.add(".--.");
        morseValuesForRussianAndSymbols.add(".-.");
        morseValuesForRussianAndSymbols.add("...");
        morseValuesForRussianAndSymbols.add("-");
        morseValuesForRussianAndSymbols.add("..-");
        morseValuesForRussianAndSymbols.add("..-.");
        morseValuesForRussianAndSymbols.add("....");
        morseValuesForRussianAndSymbols.add("-.-.");
        morseValuesForRussianAndSymbols.add("---.");
        morseValuesForRussianAndSymbols.add("----");
        morseValuesForRussianAndSymbols.add("--.-");
        morseValuesForRussianAndSymbols.add("--.--");
        morseValuesForRussianAndSymbols.add("-.--");
        morseValuesForRussianAndSymbols.add("-..-");
        morseValuesForRussianAndSymbols.add("..-..");
        morseValuesForRussianAndSymbols.add("..--");
        morseValuesForRussianAndSymbols.add(".-.-");
        // Symbols
        morseValuesForRussianAndSymbols.add(".----"); // = '1';
        morseValuesForRussianAndSymbols.add("..---"); // = '2';
        morseValuesForRussianAndSymbols.add("...--");  //= '3';
        morseValuesForRussianAndSymbols.add("....-");  //= '4';
        morseValuesForRussianAndSymbols.add(".....");  // = '5';
        morseValuesForRussianAndSymbols.add("-....");  //= '6';
        morseValuesForRussianAndSymbols.add("--...");  //= '7';
        morseValuesForRussianAndSymbols.add("---..");  // = '8';
        morseValuesForRussianAndSymbols.add("----.");  //= '9';
        morseValuesForRussianAndSymbols.add("-----");  //= '0';
        morseValuesForRussianAndSymbols.add("--..--"); //= '!'
        morseValuesForRussianAndSymbols.add("......"); //= '.';
        morseValuesForRussianAndSymbols.add(".-.-.-"); //= ',';
        morseValuesForRussianAndSymbols.add("..--.."); //= '?';
        morseValuesForRussianAndSymbols.add("---..."); //= ':';
        morseValuesForRussianAndSymbols.add(".-.-."); //= '+';
        morseValuesForRussianAndSymbols.add("-...-"); //= '=';
        morseValuesForRussianAndSymbols.add("-....-"); //= '—';
        morseValuesForRussianAndSymbols.add("..--.-"); //= '_';
        morseValuesForRussianAndSymbols.add(".-..."); //= '&';
        morseValuesForRussianAndSymbols.add(".--.-."); //= '@';
        morseValuesForRussianAndSymbols.add("...-..-"); //= '$';
        morseValuesForRussianAndSymbols.add("-.-.-."); //= ';';
        morseValuesForRussianAndSymbols.add(".-..-."); //= '"';
        morseValuesForRussianAndSymbols.add(".----."); //= ' ' ';
    }

    private void setTableRussian() {
        for (int i = 0; i < russianAlphabet.size(); i++) {
            tableRussian.put(russianAlphabet.get(i), morseValuesForRussianAndSymbols.get(i));
        }
        int symbolsIterator = 0;
        for (int j = russianAlphabet.size(); j < morseValuesForRussianAndSymbols.size(); j++) {
            tableRussian.put(symbols.get(symbolsIterator++), morseValuesForRussianAndSymbols.get(j));
        }
    }

    private void setTableMorse() {
        for (int i = 0; i < russianAlphabet.size(); i++) {
            tableMorse.put(morseValuesForRussianAndSymbols.get(i), russianAlphabet.get(i));
        }
        int symbolsIterator = 0;
        for (int j = russianAlphabet.size(); j < morseValuesForRussianAndSymbols.size(); j++) {
            tableMorse.put(morseValuesForRussianAndSymbols.get(j), symbols.get(symbolsIterator++));
        }
    }
}
