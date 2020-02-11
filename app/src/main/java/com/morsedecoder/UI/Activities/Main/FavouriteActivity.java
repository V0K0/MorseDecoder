package com.morsedecoder.UI.Activities.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.morsedecoder.Data.TranslationResultItem;
import com.morsedecoder.R;
import com.morsedecoder.adapters.TranslationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteActivity extends AppCompatActivity {

    private final static String THEME_KEY = "IsNightMode";

    private TranslationAdapter adapter;
    private List<TranslationResultItem> translations = new ArrayList<>();
    @BindView(R.id.recyclerViewFavourite) RecyclerView recyclerView;
    private FavouriteViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int themeId = intent.getBooleanExtra(THEME_KEY, false) ? R.style.ThemeDark : R.style.ThemeLight;
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setElevation(0);
            bar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new TranslationAdapter(translations, false);
        viewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadTranslatesInAdapter();
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

    }

    private ItemTouchHelper.SimpleCallback callback  = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
           removeFromFavourite(viewHolder.getAdapterPosition());
        }
    };

    private void removeFromFavourite(int adapterPosition) {
        TranslationResultItem translation = translations.get(adapterPosition);
        translation.setFavourite(false);
        viewModel.updateTranslation(translation);
    }


    private void loadTranslatesInAdapter(){
        LiveData<List<TranslationResultItem>> favourites = viewModel.getFavouriteTranslations();
        favourites.observe(this, translationResults -> {
            adapter.setTranslationResults(translationResults);
            translations = translationResults;
        });
    }



}
