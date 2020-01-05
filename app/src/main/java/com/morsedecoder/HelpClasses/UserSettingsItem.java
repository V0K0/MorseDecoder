package com.morsedecoder.HelpClasses;

public class UserSettingsItem {

    private int settingsIconId;
    private String settingsLabel;
    private String settingsDescription;
    private String tag;


    public UserSettingsItem(int settingsIconId, String settingsLabel, String settingsDescription, String tag) {
        this.settingsIconId = settingsIconId;
        this.settingsLabel = settingsLabel;
        this.settingsDescription = settingsDescription;
        this.tag = tag;
    }

    public int getSettingsIconId() {
        return settingsIconId;
    }

    public String getSettingsLabel() {
        return settingsLabel;
    }

    public String getSettingsDescription() {
        return settingsDescription;
    }

    public String getTag() {
        return tag;
    }
}
