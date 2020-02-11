package com.morsedecoder.UI.Activities.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.morsedecoder.R;

public class HelpActivity extends AppCompatActivity {

    private final static String THEME_KEY = "IsNightMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int themeId = intent.getBooleanExtra(THEME_KEY, false) ? R.style.ThemeDark : R.style.ThemeLight;
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.setDisplayHomeAsUpEnabled(true);
        }

        //
        // Will be updated in next commit
        //

    }
}
