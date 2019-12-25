package com.morsedecoder.UI.Fragments.Home;


import com.morsedecoder.InterfacesForTranslations.Translateable;
import com.morsedecoder.Data.EnglishVocabulary;
import com.morsedecoder.Data.RussianVocabulary;
import com.morsedecoder.Domain.CommonTranslator;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private String[] spinnerValues;
    private Translateable commonTranslator;

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onTranslaterChanged() {
        String[] languages = homeView.getAppLanguages();
        spinnerValues = homeView.getSpinnersValues();

        final String MORSE = languages[0];
        final String ENG = languages[1];
        final String RUS = languages[2];

        String left = spinnerValues[0];
        String right = spinnerValues[1];

        if (left.equals(right) || !left.equals(MORSE) && !right.equals(MORSE)) {
            commonTranslator = null;
            return;
        }
        if (left.equals(ENG) || right.equals(ENG)) {
            commonTranslator = new CommonTranslator(new EnglishVocabulary());
        } else if (left.equals(RUS) || right.equals(RUS)) {
            commonTranslator = new CommonTranslator(new RussianVocabulary());
        }

    }

    @Override
    public String getTranslation(boolean isFromMorse, String text) {
        String translation = null;
        if (commonTranslator != null) {
            translation = commonTranslator.getTranslatedMessage(isFromMorse, text);
        }
        return translation;
    }

}
