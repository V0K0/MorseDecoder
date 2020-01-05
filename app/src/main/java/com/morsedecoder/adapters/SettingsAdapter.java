package com.morsedecoder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morsedecoder.HelpClasses.UserSettingsItem;
import com.morsedecoder.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {

    private List<UserSettingsItem> settingsItems;
    private OnSettingsIconClickListener onSettingsIconClickListener;

    public List<UserSettingsItem> getSettingsItems() {
        return settingsItems;
    }

    public void setSettingsItems(List<UserSettingsItem> settingsItems) {
        this.settingsItems = settingsItems;
        notifyDataSetChanged();
    }

    public SettingsAdapter(List<UserSettingsItem> settingsItems) {
        this.settingsItems = settingsItems;
    }

   public interface OnSettingsIconClickListener {
        void onIconClick(int position);
    }

    public void setOnSettingsIconClickListener(OnSettingsIconClickListener onSettingsIconClickListener) {
        this.onSettingsIconClickListener = onSettingsIconClickListener;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item, parent, false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        UserSettingsItem settingsItem = settingsItems.get(position);
        holder.settingsDescription.setText(settingsItem.getSettingsDescription());
        holder.settingsLabel.setText(settingsItem.getSettingsLabel());
        holder.settingsIcon.setImageResource(settingsItem.getSettingsIconId());
    }

    @Override
    public int getItemCount() {
        if (settingsItems != null) {
            return settingsItems.size();
        }
        return 0;
    }

    class SettingsViewHolder extends RecyclerView.ViewHolder {

        private ImageView settingsIcon;
        private TextView settingsLabel;
        private TextView settingsDescription;

        public SettingsViewHolder(@NonNull View itemView) {
            super(itemView);
            settingsIcon = itemView.findViewById(R.id.settings_icon);
            settingsLabel = itemView.findViewById(R.id.title_of_setting);
            settingsDescription = itemView.findViewById(R.id.desc_of_setting);
            settingsIcon.setOnClickListener(v -> {
                if (onSettingsIconClickListener != null) {
                    onSettingsIconClickListener.onIconClick(getAdapterPosition());
                }
            });
        }
    }
}
