package com.vienna.relja.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by reljica on 10/1/2015.
 */
public class UserPreferences {

    public SharedPreferences.Editor editor;
    public SharedPreferences preferences;

    public UserPreferences(Context context,String prefFileName){
        preferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
}
