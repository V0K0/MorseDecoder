package com.morsedecoder.test;

import com.morsedecoder.Data.EnglishVocabulary;
import com.morsedecoder.Data.RussianVocabulary;
import com.morsedecoder.Domain.CommonTranslator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonTranslatorTest {

    private CommonTranslator translatorEng;
    private CommonTranslator translatorRus;

    @Before
    public void setTranlators() {
        translatorEng = new CommonTranslator(new EnglishVocabulary());
        translatorRus = new CommonTranslator(new RussianVocabulary());
    }

    @Test
    public void translateFromMorseToEng() {
        String actualEng = translatorEng.translateFromMorse("... --- ...  ... --- ...");
        String expectedEng = "SOS SOS";
        Assert.assertEquals(expectedEng, actualEng);
    }

    @Test
    public void translateFromMorseToRus() {
        String actualRus = translatorRus.translateFromMorse("... --- ...  ... --- ...");
        String expectedRus = "СОС СОС";
        Assert.assertEquals(expectedRus, actualRus);
    }

    @Test
    public void translateInMorseFromEng() {
        String actualEng = translatorEng.translateInMorse("sos sos");
        String expectedFromEng = "... --- ...  ... --- ...";
        Assert.assertEquals(expectedFromEng, actualEng);
    }

    @Test
    public void translateInMorseFromRus() {
        String actualRus = translatorRus.translateInMorse("сос сос");
        String expectedFromRus = "... --- ...  ... --- ...";
        Assert.assertEquals(expectedFromRus, actualRus);
    }

    @Test
    public void getTranslatedMessageTrueFalseEng() {
        String actualEng = translatorEng.getTranslatedMessage(true, "... --- ...");
        String expectedEng = "SOS";
        Assert.assertEquals(expectedEng, actualEng);
    }

    @Test
    public void getTranslatedMessageTrueFalseRus() {
        String actualFromMorseToRus = translatorRus.getTranslatedMessage(true, "... --- ...");

        String actualFromRusToMorse = translatorRus.getTranslatedMessage(false, "сос");

        String expectedFromMorseToRus = "СОС";
        String expectedFromRusToMorse = "... --- ...";

        Assert.assertEquals(expectedFromMorseToRus, actualFromMorseToRus);
        Assert.assertEquals(expectedFromRusToMorse, actualFromRusToMorse);
    }




}