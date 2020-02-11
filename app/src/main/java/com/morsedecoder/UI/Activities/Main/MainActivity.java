package com.morsedecoder.UI.Activities.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.morsedecoder.HelpClasses.UserDialogs;
import com.morsedecoder.UI.Fragments.Home.HomeFragment;
import com.morsedecoder.UI.Fragments.Home.MainViewModel;
import com.morsedecoder.UI.Fragments.Settings.SettingsFragment;
import com.morsedecoder.UI.Fragments.Signal.SignalFragment;
import com.morsedecoder.R;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnSendSignal, SignalFragment.OnSendRequest {

    private BottomNavigationView bottomNav;

    private final HomeFragment fragmentHome = new HomeFragment();
    private final SignalFragment fragmentSignal = new SignalFragment();
    private final SettingsFragment fragmentSettings = new SettingsFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    private final static String THEME_KEY = "IsNightMode";
    private Fragment active = fragmentSettings;
    private boolean IsNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        if (sharedPreferences.getBoolean(THEME_KEY, false)) {
            setTheme(R.style.ThemeDark);
            IsNightMode = true;
        } else {
            setTheme(R.style.ThemeLight);
            IsNightMode = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setElevation(0);
        }

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        changeBottomNavigationTint(IsNightMode);

        fm.beginTransaction().add(R.id.Linear_container, fragmentSettings, "3").hide(fragmentSettings).commit();
        fm.beginTransaction().add(R.id.Linear_container, fragmentSignal, "2").hide(fragmentSignal).commit();
        fm.beginTransaction().add(R.id.Linear_container, fragmentHome, "1").commit();

        UserDialogs.checkCameraPermission(this);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fm.beginTransaction().hide(active).show(fragmentHome).commit();
                active = fragmentHome;
                break;
            case R.id.nav_signal:
                fm.beginTransaction().hide(active).show(fragmentSignal).commit();
                active = fragmentSignal;
                break;
            case R.id.nav_settings:
                fm.beginTransaction().hide(active).show(fragmentSettings).commit();
                active = fragmentSettings;
                break;
        }
        return true;
    };

    private void changeBottomNavigationTint(boolean isDark) {
        if (isDark) {
            ColorStateList colorStateList = getResources().getColorStateList(R.color.buttom_nav_colors);
            bottomNav.setItemIconTintList(colorStateList);
            bottomNav.setItemTextColor(colorStateList);
        }
    }

    @Override
    public void onAttachFragment(@NotNull Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            HomeFragment homeFragment = (HomeFragment) fragment;
            homeFragment.setOnSendSignal(this);
        } else if (fragment instanceof SignalFragment) {
            SignalFragment signalFragment = (SignalFragment) fragment;
            signalFragment.setOnSendRequest(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fav_translates:
                Intent toFavIntent = new Intent(MainActivity.this, FavouriteActivity.class);
                toFavIntent.putExtra(THEME_KEY, IsNightMode);
                startActivity(toFavIntent);
                return true;
            case R.id.get_help:
                Intent toHelpIntent = new Intent(MainActivity.this, HelpActivity.class);
                toHelpIntent.putExtra(THEME_KEY, IsNightMode);
                startActivity(toHelpIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void sendMessageSignal(String message) {
        fragmentSignal.sendSignalWithMessage(message);
    }

    @Override
    public String requestMessage() {
        return fragmentHome.requestMessage();
    }
}
