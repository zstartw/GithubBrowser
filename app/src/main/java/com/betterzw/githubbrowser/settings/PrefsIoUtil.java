package com.betterzw.githubbrowser.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.betterzw.githubbrowser.GithubBrowserApp;

/**
 * Created by zhengwu on 4/10/18.
 */
public class PrefsIoUtil {

    public static boolean getBoolean(@StringRes int id, boolean defaultValue) {
        return getBoolean(getKey(id), defaultValue);
    }


    public static boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    /** @return Key String resource from preference_keys.xml. */
    @NonNull
    public static String getKey(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }


    private PrefsIoUtil(){

    }

    @NonNull
    private static SharedPreferences getPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @NonNull
    private static Resources getResources() {
        return getContext().getResources();
    }

    @NonNull
    private static Context getContext() {
        return GithubBrowserApp.getInstance();
    }
}
