package com.negomatic.retailer.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.negomatic.retailer.model.Account;


/**
 * Manejador de preferencias de la sesión de la cuenta
 */
public class SessionPrefs {

    public static final String PREFS_NAME = "SESSION_PREFS";

    public static final String PREF_SESSION_USERNAME = "PREF_SESSION_USERNAME";
    public static final String PREF_SESSION_PASSWORD = "PREF_SESSION_PASSWORD";
    public static final String PREF_SESSION_TOKEN = "PREF_SESSION_TOKEN";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_SESSION_USERNAME, null));

    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void saveAccount(Account account) {
        if (account != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREF_SESSION_USERNAME, account.getUserName());
            editor.putString(PREF_SESSION_PASSWORD, account.getPassword());
            editor.putString(PREF_SESSION_TOKEN, account.getToken());
            editor.apply();
            mIsLoggedIn = true;
        }
    }
    public void saveToken(String token) {
        if (token != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREF_SESSION_TOKEN, token);
            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_SESSION_USERNAME, null);
        editor.putString(PREF_SESSION_PASSWORD, null);
        editor.putString(PREF_SESSION_TOKEN, null);
        editor.apply();
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key,defValue);
    }

}
