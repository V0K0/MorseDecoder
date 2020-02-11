package com.morsedecoder.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morsedecoder.Data.TranslationResultItem;
import com.morsedecoder.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationViewHolder> {

    private List<TranslationResultItem> translationResults;
    private boolean showIcon;
    private OnAddToFavouriteClickListener onAddToFavouriteClickListener;

    public TranslationAdapter(List<TranslationResultItem> translationResults, boolean showIcon) {
        this.translationResults = translationResults;
        this.showIcon = showIcon;
    }

    public interface OnAddToFavouriteClickListener {
        void onAddToFavourite(int position);
    }

    public void setOnAddToFavouriteClickListener(OnAddToFavouriteClickListener onAddToFavouriteClickListener) {
        this.onAddToFavouriteClickListener = onAddToFavouriteClickListener;
    }

    public void setTranslationResults(List<TranslationResultItem> translationResults) {
        this.translationResults = translationResults;
        notifyDataSetChanged();
    }

    public List<TranslationResultItem> getTranslationResults() {
        return translationResults;
    }

    @NonNull
    @Override
    public TranslationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.translation_history_item, parent, false);
        return new TranslationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationViewHolder holder, int position) {
        TranslationResultItem result = translationResults.get(position);
        holder.textViewIn.setText(result.getMessageAfterTranslation());
        holder.textViewFrom.setText(result.getMessageBeforeTranslation());
        int resId = result.isFavourite() ? R.drawable.ic_star_yellow_40dp : holder.starDefaultResId;
        holder.imageViewStar.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return translationResults.size();
    }


    class TranslationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.translationFrom)
        TextView textViewFrom;
        @BindView(R.id.translationIn)
        TextView textViewIn;
        @BindView(R.id.imageViewStar)
        ImageView imageViewStar;
        private int starDefaultResId;

        TranslationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            starDefaultResId = Resources.getSystem().getIdentifier(imageViewStar.toString(), "drawable", itemView.getContext().getPackageName());
            if (showIcon){
                imageViewStar.setVisibility(View.VISIBLE);
            } else {
                imageViewStar.setVisibility(View.INVISIBLE);
            }
            imageViewStar.setOnClickListener(v -> {
                if (onAddToFavouriteClickListener != null) {
                    onAddToFavouriteClickListener.onAddToFavourite(getAdapterPosition());
                }
            });
        }
    }
}
