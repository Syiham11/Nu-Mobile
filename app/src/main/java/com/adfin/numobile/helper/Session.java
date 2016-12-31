package com.adfin.numobile.helper;
// Created by prakasa on 31/12/16.

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private static Context mContext;

    private static SharedPreferences mSession;

    public static Session with(Context context) {
        if (context != null) mContext = context;
        return new Session();
    }

    public Session load(String sessionName){
        mSession = mContext.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        return this;
    }

    public Session set(String k, String v){
        SharedPreferences.Editor mEditor = mSession.edit();
        mEditor.putString(k, v);
        mEditor.apply();
        return this;
    }

    public String get(String k){
        return mSession.getString(k, null);
    }
}
