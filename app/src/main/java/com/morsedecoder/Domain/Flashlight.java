package com.morsedecoder.Domain;

import android.hardware.Camera;

import com.morsedecoder.HelpClasses.FlashThread;

import java.util.ArrayList;


public final class Flashlight {

    private char[] morseSignalChars;
    private Camera camera;
    private Camera.Parameters parameters;
    private FlashThread flashThread;
    private static Flashlight instance;
    private ArrayList<char[]> morseLetters = new ArrayList<>();


    public static Flashlight getInstance() {
        if (instance == null) {
            instance = new Flashlight();
        }
        return instance;
    }

    public void sendSignal(String morseSignal) {
        morseSignal = morseSignal + " ";
        convertToCharArraysMessage(morseSignal);

        flashThread = new FlashThread(morseLetters);
    }

    private void convertToCharArraysMessage(String str) {
        morseLetters.clear();
        morseSignalChars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char morseSignalChar : morseSignalChars) {
            if (morseSignalChar != ' ') {
                builder.append(morseSignalChar);
            } else if (morseSignalChar == ' ') {
                morseLetters.add(builder.toString().toCharArray());
                builder = new StringBuilder();
            }
        }
    }

    public void interruptFlashThread() {
        if (flashThread != null && !flashThread.isInterrupted()) {
            flashThread.stopThread();
        }
    }


    public void setFlashLightOn() {
        camera = Camera.open();
        parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
        camera.startPreview();
    }

    public void setFlashLightOff() {
        camera.stopPreview();
        camera.release();
    }


}



