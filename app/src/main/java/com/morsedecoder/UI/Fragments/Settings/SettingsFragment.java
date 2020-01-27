package com.morsedecoder.UI.Fragments.Settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.morsedecoder.HelpClasses.UserSettingsItem;
import com.morsedecoder.R;
import com.morsedecoder.UI.Activities.Main.MainActivity;
import com.morsedecoder.UI.Fragments.Home.MainViewModel;
import com.morsedecoder.adapters.SettingsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SettingsFragment extends Fragment {

    private final String CLEAR_HISTORY = "Clear history";
    private final String SET_NIGTH_MODE = "Set night mode";
    private final String TAG_HISTORY = "history";
    private final String TAG_SWICTH_MODE = "switch_mode";



   @BindView(R.id.recyclerViewSettings) RecyclerView recyclerView;
    private SettingsAdapter adapter;

    private List<UserSettingsItem> settingsItems;
    private MainViewModel viewModel;

    private boolean isNightMode;
    private SharedPreferences sharedPreferences;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        isNightMode = sharedPreferences.getBoolean("IsNightMode", false);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        unbinder = ButterKnife.bind(this, view);

        addSettings();

        adapter = new SettingsAdapter(settingsItems);
        adapter.setOnSettingsIconClickListener(iconClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private SettingsAdapter.OnSettingsIconClickListener iconClickListener = new SettingsAdapter.OnSettingsIconClickListener() {
        @Override
        public void onIconClick(int position) {
            UserSettingsItem userSettings = settingsItems.get(position);
            switch (userSettings.getTag()) {
                case TAG_HISTORY:
                    viewModel.deleteAllTranslationsFromDB();
                    Toast.makeText(getContext(), getString(R.string.history_cleared_toast), Toast.LENGTH_SHORT).show();
                    break;
                case TAG_SWICTH_MODE:
                    swapTheme(!isNightMode);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Objects.requireNonNull(getActivity()).finish();
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };


    private void addSettings() {
        settingsItems = new ArrayList<>();

        settingsItems.add(new UserSettingsItem(
                getSettingsIconByCurrentMode(isNightMode, SET_NIGTH_MODE),
                getString(R.string.Night_mode),
                getString(R.string.Enable_night_mode),
                TAG_SWICTH_MODE));

        settingsItems.add(new UserSettingsItem(
                getSettingsIconByCurrentMode(isNightMode, CLEAR_HISTORY),
                getString(R.string.clear_history),
                getString(R.string.clear_all_notes),
                TAG_HISTORY));

    }

    private int getSettingsIconByCurrentMode(boolean isNightMode, String settingsLabel) {
        switch (settingsLabel) {
            case CLEAR_HISTORY:
                return isNightMode ? R.drawable.ic_delete_white_40dp : R.drawable.ic_delete_blue_40dp;
            case SET_NIGTH_MODE:
                return isNightMode ? R.drawable.ic_brightness_white_50dp : R.drawable.ic_brightness_blue_50dp;
            default:
                return 0;
        }
    }


    private void swapTheme(boolean isChecked) {
        if (isChecked) {
            sharedPreferences.edit().putBoolean("IsNightMode", isChecked).apply();
        } else {
            sharedPreferences.edit().putBoolean("IsNightMode", isChecked).apply();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
