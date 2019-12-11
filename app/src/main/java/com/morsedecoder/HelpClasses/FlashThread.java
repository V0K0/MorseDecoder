package com.morsedecoder.HelpClasses;

import com.morsedecoder.Domain.Flashlight;

import java.util.ArrayList;

public class FlashThread extends Thread {

    private ArrayList<char[]> morseLetters = new ArrayList<>();
    private volatile boolean running = true;
    private final int LONG_TIMEOUT = 330;
    private final int SHORT_TIMEOUT = 110;
    private int sleepValue;
    private static final Flashlight light = Flashlight.getInstance();

    public FlashThread(ArrayList<char[]> morseLetters) {
        this.morseLetters.addAll(morseLetters);
        new Thread(this).start();
    }

    public void stopThread() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            sendSignals();
        }
    }

    private void sendSignals() {
        synchronized (light) {
            switchLight();
        }
    }

    private void switchLight() {

        for (char[] letter : morseLetters) {
            for (char symbol : letter) {
                {
                    try {
                        if (running) {
                            light.setFlashLightOn();
                            sleepValue = getTimeOut(symbol);
                            Thread.sleep(sleepValue);
                            light.setFlashLightOff();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pauseBetweenLetters();
            }
        }
        pauseBeforeRepeat();
    }

    private int getTimeOut(char symbol) {
        if (symbol == '.') {
            return SHORT_TIMEOUT;
        } else {
            return LONG_TIMEOUT;
        }
    }

    private void pauseBetweenLetters() {
        try {
            Thread.sleep(330);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pauseBeforeRepeat() {
        try {
            Thread.sleep(770);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
