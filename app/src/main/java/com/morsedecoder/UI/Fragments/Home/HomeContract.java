package com.morsedecoder.UI.Fragments.Home;

import android.content.Context;

public interface HomeContract {

    interface View{
        String[] getSpinnersValues();
        String[] getAppLanguages();

    }

    interface Presenter{
        void onTranslaterChanged();
        String getTranslation(boolean isFromMorse,String Text);
    }

}
