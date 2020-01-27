package com.morsedecoder.UI.Fragments.Home;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.morsedecoder.Data.TranslationResult;
import com.morsedecoder.HelpClasses.Keyboard;
import com.morsedecoder.HelpClasses.UserDialogs;
import com.morsedecoder.R;
import com.morsedecoder.UI.Fragments.Signal.SignalFragment;
import com.morsedecoder.adapters.TranslationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements SignalFragment.OnSendRequest {

    @BindView(R.id.topPanel) TableLayout topPanel;
    @BindView(R.id.Content_container) LinearLayout mainPanel;
    @BindView(R.id.card) CardView card;
    @BindView(R.id.leftSpinner) Spinner leftSpinner;
    @BindView(R.id.rightSpinner) Spinner rightSpinner;
    @BindView(R.id.editTextUserInput) EditText editText;
    @BindView(R.id.translateLine) TextView translateLine;
    @BindView(R.id.resultTranslation) TextView cardText;
    @BindView(R.id.clearButton) ImageButton clearButton;
    @BindView(R.id.goButton) ImageButton goButton;
    @BindView(R.id.cardMenuButton) ImageButton dropDownCardButton;
    @BindView(R.id.cardCopyButton) ImageButton copyCardButton;
    @BindView(R.id.swapButton) ImageButton swapButton;
    @BindView(R.id.recyclerViewHistory) RecyclerView recyclerView;

    private Unbinder unbinder;

    private MainViewModel viewModel;
    private TranslationAdapter adapter;
    private final List<TranslationResult> translations = new ArrayList<>();

    private ActionBar actionBar;

    private boolean isNightMode;

    private OnSendSignal onSendSignal;
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        isNightMode = sharedPreferences.getBoolean("IsNightMode", false);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();

        unbinder = ButterKnife.bind(this, view);

        bottomNavigationView = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_navigation);

        setCustomAdaptersForSpinners();
        rightSpinner.setSelection(0);
        leftSpinner.setSelection(1);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        adapter = new TranslationAdapter(translations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getDataInAdapter();
        recyclerView.setAdapter(adapter);


        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteFocusedTranslation(viewHolder.getAdapterPosition());
            }
        });

        touchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private void deleteFocusedTranslation(int adapterPosition) {
        TranslationResult focused = adapter.getTranslationResults().get(adapterPosition);
        viewModel.deleteTranslationFromDB(focused);
    }

    @Override
    public String requestMessage() {
        return isFromMorse() ? editText.getText().toString() : cardText.getText().toString();
    }

    public interface OnSendSignal {
        void sendMessageSignal(String message);
    }

    public void setOnSendSignal(OnSendSignal onSendSignal) {
        this.onSendSignal = onSendSignal;
    }

    private void sendMessage() {
        String message = requestMessage();
        message = message.trim();
        onSendSignal.sendMessageSignal(message);
    }

    @OnClick(R.id.swapButton)
    void onSwapClick(View v) {
        v.animate().rotation(v.getRotation() + 180).setDuration(500).start();
        int pos = leftSpinner.getSelectedItemPosition();
        leftSpinner.setSelection(rightSpinner.getSelectedItemPosition());
        rightSpinner.setSelection(pos);
    }

    @OnClick(R.id.clearButton)
    void onClearClick(View v) {
        Animation clickAnim = AnimationUtils.loadAnimation(getContext(), R.anim.click_button_effect);
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            v.startAnimation(clickAnim);
            editText.setText("");
        } else {
            cardText.setText("");
            setInitialView();
        }
    }

    @OnClick(R.id.goButton)
    void goClickListener() {
        if (!translateLine.getText().toString().trim().isEmpty()) {
            TranslationResult result = new TranslationResult(editText.getText().toString(), translateLine.getText().toString());
            cardText.setText(translateLine.getText());
            setInitialView();
            viewModel.insertTranslationInDB(result);
            card.setVisibility(View.VISIBLE);
        } else {
            setInitialView();
            editText.setText("");
        }
    }

    @OnClick(R.id.cardCopyButton)
    void copyClickListener() {
        if (cardText.getText() != null) {
            String text = cardText.getText().toString().trim();
            ClipboardManager clipManager = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Coping Text", text);
            if (clipManager != null) {
                clipManager.setPrimaryClip(clip);
                Toast.makeText(getContext(), getResources().getString(R.string.successCopied), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private PopupMenu.OnMenuItemClickListener clickListener = (MenuItem item) -> {
        switch (item.getItemId()) {
            case R.id.card_share:
                UserDialogs.ShareDialog(Objects.requireNonNull(getContext()), cardText.getText().toString());
                return true;
            case R.id.card_reverseTranslate:
                swapButton.performClick();
                String pastDecryptedMessage = cardText.getText().toString();
                String pastEncryptedMessage = editText.getText().toString();
                cardText.setText(pastEncryptedMessage);
                editText.setText(pastDecryptedMessage);
                return true;
            case R.id.give_signal:
                if (onSendSignal != null) {
                    bottomNavigationView.setSelectedItemId(R.id.nav_signal);
                    sendMessage();
                }
                return true;
            default:
                return false;
        }

    };

    @OnClick(R.id.cardMenuButton)
    void onDropdownClick(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.card_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(clickListener);
        popupMenu.show();
    }

    @OnItemSelected({R.id.leftSpinner, R.id.rightSpinner})
    void onChangedSelection() {
        viewModel.onTranslaterChanged(getContext(), getSpinnersValues());
    }

    @OnTextChanged(R.id.editTextUserInput)
    void onTextChanged() {
        String translatedMessage = viewModel.getTranslatedMessage(isFromMorse(), editText.getText().toString().trim());
        translateLine.setText(translatedMessage);
    }


    @OnClick(R.id.editTextUserInput)
    void onEditTextClick() {
        if (topPanel.getVisibility() == View.VISIBLE) {
            Animation slideTop = AnimationUtils.loadAnimation(getContext(), R.anim.topslide_effect);
            slideTop.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    topPanel.setVisibility(View.GONE);
                    actionBar.hide();
                    card.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    clearButton.setVisibility(View.VISIBLE);
                    goButton.setVisibility(View.VISIBLE);
                    translateLine.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            mainPanel.startAnimation(slideTop);
        }
    }

    private void setInitialView() {
        Activity activity = Objects.requireNonNull(getActivity());
        actionBar.show();
        Keyboard.hideKeyboard(activity);
        clearButton.setVisibility(View.INVISIBLE);
        topPanel.setVisibility(View.VISIBLE);
        translateLine.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setCustomAdaptersForSpinners() {
        if (isNightMode) {
            ArrayAdapter adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()), R.array.Languages, R.layout.dark_spinner_font);
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_dark);
            leftSpinner.setAdapter(adapter);
            rightSpinner.setAdapter(adapter);
        }
    }

    private void getDataInAdapter() {
        LiveData<List<TranslationResult>> history = viewModel.getTranslationHistory();
        history.observe(this, translationResults -> adapter.setTranslationResults(translationResults));
    }

    private String[] getSpinnersValues() {
        String[] values = new String[2];
        values[0] = leftSpinner.getSelectedItem().toString();
        values[1] = rightSpinner.getSelectedItem().toString();
        return values;
    }

    private boolean isFromMorse() {
        String left = leftSpinner.getSelectedItem().toString();
        String Morse = getResources().getStringArray(R.array.Languages)[0];
        return left.equals(Morse);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}