package ru.yandex.dunaev.mick.simpleinternetradio.database;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static PreferencesHelper sHelper;
    private SharedPreferences mPreferences;
    private PreferencesHelper(Context context){
        mPreferences = context.getSharedPreferences("settings.txt", Context.MODE_PRIVATE);
    };

    public static void init(Context context){
        if(sHelper == null) sHelper = new PreferencesHelper(context);
    }

    public static void putString(String key, String text){
        SharedPreferences.Editor editor = sHelper.mPreferences.edit();
        editor.putString(key, text);
        editor.apply();
    }

    public static String getString(String key){
        return sHelper.mPreferences.getString(key, "");
    }

}
