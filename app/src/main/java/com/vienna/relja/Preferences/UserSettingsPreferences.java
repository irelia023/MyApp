package com.vienna.relja.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by reljica on 10/1/2015.
 */
public class UserSettingsPreferences extends UserPreferences{

    private final static String USER_SETTINGS = "UserInfoPrefFile";


    public UserSettingsPreferences(Context context) {
       super(context, USER_SETTINGS);
    }





}
