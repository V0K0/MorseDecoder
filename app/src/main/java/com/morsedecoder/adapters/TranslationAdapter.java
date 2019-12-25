package com.morsedecoder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morsedecoder.R;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationViewHolder> {

    // Class will be finished in future commit

    @NonNull
    @Override
    public TranslationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.translation_history_item, parent, false);
        return new TranslationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationViewHolder holder, int position) {
        // Will be added in future updates
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class TranslationViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFrom;
        private TextView textViewIn;

        public TranslationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFrom = itemView.findViewById(R.id.translationFrom);
            textViewIn = itemView.findViewById(R.id.translationIn);
        }
    }
}
