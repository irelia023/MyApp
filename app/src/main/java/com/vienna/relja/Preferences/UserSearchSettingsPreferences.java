package com.vienna.relja.Preferences;

import android.content.Context;

/**
 * Created by reljica on 10/1/2015.
 */
public class UserSearchSettingsPreferences extends UserPreferences {

    private final static String USER_SEARCH_SETTINGS = "UserSearchSettingsPrefFile";

    public UserSearchSettingsPreferences(Context context) {
        super(context, USER_SEARCH_SETTINGS);
    }
}
