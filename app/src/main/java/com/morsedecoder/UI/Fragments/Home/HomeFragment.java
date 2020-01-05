package com.morsedecoder.UI.Fragments.Home;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.transition.TransitionSet;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class HomeFragment extends Fragment implements SignalFragment.OnSendRequest {

    private TableLayout topPanel;
    private LinearLayout mainPanel;
    private CardView card;
    private BottomNavigationView bottomNavigationView;
    private Spinner leftSpinner;
    private Spinner rightSpinner;
    private PopupMenu popupMenu;
    private EditText editText;
    private TextView translateLine;
    private TextView cardText;
    private ImageButton clearButton;
    private ImageButton goButton;
    private ImageButton dropDownCardButton;
    private ImageButton copyCardButton;
    private ImageButton swapButton;

    private SharedPreferences sharedPreferences;
    private MainViewModel viewModel;
    private TranslationAdapter adapter;
    private final List<TranslationResult> translations = new ArrayList<>();
    private RecyclerView recyclerView;
    private ActionBar actionBar;

    private boolean isNightMode;

    private OnSendSignal onSendSignal;

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

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();

        topPanel = view.findViewById(R.id.topPanel);
        mainPanel = view.findViewById(R.id.Content_container);
        card = view.findViewById(R.id.card);
        cardText = view.findViewById(R.id.cardResult);
        translateLine = view.findViewById(R.id.translateLine);
        swapButton = view.findViewById(R.id.swapButton);
        clearButton = view.findViewById(R.id.cancelButton);
        goButton = view.findViewById(R.id.goButton);
        copyCardButton = view.findViewById(R.id.cardCopy);
        dropDownCardButton = view.findViewById(R.id.dropDownCardMenu);
        editText = view.findViewById(R.id.TextInputArea);
        leftSpinner = view.findViewById(R.id.leftSpinner);
        rightSpinner = view.findViewById(R.id.rightSpinner);
        bottomNavigationView = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_navigation);
        recyclerView = view.findViewById(R.id.recyclerViewHistory);

        // editText.requestFocus();

        swapButton.setOnClickListener(swapClickListener);
        clearButton.setOnClickListener(clearClickListener);
        goButton.setOnClickListener(goClickListener);
        copyCardButton.setOnClickListener(copyClickListener);
        dropDownCardButton.setOnClickListener(dropdownClickListener);
        rightSpinner.setOnItemSelectedListener(changedSelection);
        leftSpinner.setOnItemSelectedListener(changedSelection);
        editText.addTextChangedListener(watcher);
        editText.setOnClickListener(editTextClick);

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

    private ImageButton.OnClickListener swapClickListener = (View v) -> {
        v.animate().rotation(v.getRotation() + 180).setDuration(500).start();
        int pos = leftSpinner.getSelectedItemPosition();
        leftSpinner.setSelection(rightSpinner.getSelectedItemPosition());
        rightSpinner.setSelection(pos);
    };

    private ImageButton.OnClickListener clearClickListener = (View v) -> {
        Animation clickAnim = AnimationUtils.loadAnimation(getContext(), R.anim.click_button_effect);
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            v.startAnimation(clickAnim);
            editText.setText("");
        } else {
            cardText.setText("");
            setInitialView();
        }
    };

    private ImageButton.OnClickListener goClickListener = (View v) -> {
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
    };

    private ImageButton.OnClickListener copyClickListener = (View v) -> {
        if (cardText.getText() != null) {
            String text = cardText.getText().toString().trim();
            ClipboardManager clipManager = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Coping Text", text);
            if (clipManager != null) {
                clipManager.setPrimaryClip(clip);
                Toast.makeText(getContext(), getResources().getString(R.string.successCopied), Toast.LENGTH_SHORT).show();
            }
        }
    };

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
    private ImageButton.OnClickListener dropdownClickListener = (View v) -> {
        popupMenu = new PopupMenu(getContext(), v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.card_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(clickListener);
        popupMenu.show();
    };

    private Spinner.OnItemSelectedListener changedSelection = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            viewModel.onTranslaterChanged(getContext(), getSpinnersValues());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    private  TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String translatedMessage = viewModel.getTranslatedMessage(isFromMorse(), editText.getText().toString().trim());
            translateLine.setText(translatedMessage);
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private EditText.OnClickListener editTextClick = (View v) -> {
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
    };

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

}