package com.vienna.relja.Preferences;

import android.content.Context;

/**
 * Created by reljica on 10/1/2015.
 */
public class UserInfoPreferences extends UserPreferences{

    private final static String USER_INFO = "UserSearchSettingsPrefFile";

    public UserInfoPreferences(Context context){
        super(context, USER_INFO);
    }
}
