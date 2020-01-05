package com.morsedecoder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morsedecoder.Data.TranslationResult;
import com.morsedecoder.R;

import java.util.ArrayList;
import java.util.List;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationViewHolder> {

    private List<TranslationResult> translationResults;

    public List<TranslationResult> getTranslationResults() {
        return translationResults;
    }

    public void setTranslationResults(List<TranslationResult> translationResults) {
        this.translationResults = translationResults;
        notifyDataSetChanged();
    }

    public TranslationAdapter(List<TranslationResult> translationResults) {
        this.translationResults = translationResults;
    }



    @NonNull
    @Override
    public TranslationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.translation_history_item, parent, false);
        return new TranslationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationViewHolder holder, int position) {
        TranslationResult result = translationResults.get(position);
        holder.textViewIn.setText(result.getLanguageIn());
        holder.textViewFrom.setText(result.getLanguageFrom());
    }

    @Override
    public int getItemCount() {
        return translationResults.size();
    }


    public class TranslationViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFrom;
        private TextView textViewIn;
       // private ImageView imageViewStar;

        public TranslationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFrom = itemView.findViewById(R.id.translationFrom);
            textViewIn = itemView.findViewById(R.id.translationIn);
          //  imageViewStar = itemView.findViewById(R.id.imageViewStar);
        }
    }
}
