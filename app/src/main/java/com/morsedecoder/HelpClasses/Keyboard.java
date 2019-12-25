package com.morsedecoder.HelpClasses;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Keyboard {

    public static void hideKeyboard(Activity activity) {
        View view = activity.getWindow().getDecorView();
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }
}

