package com.morsedecoder.UI.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.morsedecoder.R;
import com.morsedecoder.UI.Activities.MainActivity;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Switch mSwitch;
    private boolean isSwitchActivated;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        isSwitchActivated = sharedPreferences.getBoolean("IsNightMode", false);

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mSwitch = view.findViewById(R.id.setting_switch);
        mSwitch.setChecked(isSwitchActivated);
        mSwitch.setOnCheckedChangeListener(checkedChangeListener);

        return view;
    }

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> {
        swapTheme(isChecked);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Objects.requireNonNull(getActivity()).finish();
        startActivity(intent);
    };

    private void swapTheme(boolean isChecked) {
        if (isChecked) {
            sharedPreferences.edit().putBoolean("IsNightMode", isChecked).apply();
        } else {
             sharedPreferences.edit().putBoolean("IsNightMode", isChecked).apply();
        }
    }
}
