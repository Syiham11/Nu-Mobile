package com.adfin.numobile.util;

/**
 * Created by aldieemaulana on 1/12/2016.
 * All right reserved 2015
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



import java.util.HashMap;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "WMSPDAMbyaldieemaulana";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_TOKEN = "getToken";
    private static final String KEY_ID = "getId";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String isToken, int isId) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_TOKEN, isToken);
        editor.putInt(KEY_ID, isId);

        editor.commit();
    }

//    public void  isLogOut(){
//        DB handler = new DB(_context);
//        handler.dropDB();
//        editor.clear();
//        editor.commit();
//    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getToken(){
        return pref.getString(KEY_TOKEN, null);
    }

    public int getId(){
        return pref.getInt(KEY_ID, 0);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put("token", pref.getString(KEY_TOKEN, null));
        user.put("id", pref.getString(KEY_ID, null));


        return user;
    }
}
