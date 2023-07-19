package com.negomatic.retailer.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class CartPrefs {
    public static final String PREFS_NAME = "CART_PREFS";
    public static final String PREF_CART_ITEMS = "PREF_CART_ITEMS";

    private final SharedPreferences mPrefs;

    private boolean mIsItemsInCart = false;

    private static CartPrefs INSTANCE;

    public static CartPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CartPrefs(context);
        }
        return INSTANCE;
    }
    private CartPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mIsItemsInCart = !TextUtils.isEmpty(mPrefs.getString(PREF_CART_ITEMS, null));

    }

    public boolean mIsItemsInCart(){return mIsItemsInCart;}

    public void saveItems(String value){
        SharedPreferences.Editor e = mPrefs.edit();
        e.putString(PREF_CART_ITEMS, value);
        e.apply();
        mIsItemsInCart = true;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key,defValue);
    }

}
