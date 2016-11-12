package com.adfin.numobile.utility;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import org.json.JSONArray;

/**
 * Created by xeranta on 2/16/16.
 */
public class SessionManager {

    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    public static final String language ="language";
    public static final String ipServer ="ipServer";
    public static final String userType ="userType";
    public static final String portServer ="portServer";
    public static final String registerId ="registerId";
    public static final String userId ="userId";
    public static final String mediaDescriptionType ="mediaDescriptionType";
    public static final String dropdownArray = "[\"10\",\"25\",\"50\",\"70\",\"100\"]";



    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public static final JSONArray arrTes = null;



    public void setIpServer(String ipservers_)
    {
        editor.putString(ipServer, ipservers_);
        editor.commit();
    }
    public String getIpServer() {
        return pref.getString(ipServer, "");
    }



    public void setArrDropDown(String dropdown_)
    {
        editor.putString(dropdownArray, dropdown_);
        editor.commit();
    }
    public String getArrDropDown() {
        return pref.getString(dropdownArray, "[\"10\",\"25\",\"50\",\"70\",\"100\"]");
    }


    public void setUserId(String userId_)
    {
        editor.putString(userId, userId_);
        editor.commit();
    }

    public void setUserType(String userType_)
    {
        editor.putString(userType, userType_);
        editor.commit();
    }

    public String getUserType() {
        return pref.getString(userType, "");
    }


    public void setRegisterId(String registerid_)
    {
        editor.putString(registerId, registerid_);
        editor.commit();
    }

    public String getRegisterId() {
        return pref.getString(registerId, "");
    }

    public String getMediaDesTyp()
    {
        return pref.getString(mediaDescriptionType, "");
    }

    public void setMediaDesTyp(String mediaDesTyp_)
    {
        editor.putString(mediaDescriptionType, mediaDesTyp_);
        editor.commit();
    }
    public String getUserId() {
        return pref.getString(userId, "");
    }


    public void setPortServer(String portserver_)
    {
        editor.putString(portServer, portserver_);
        editor.commit();
    }
    public String getPortServer() {
        return pref.getString(portServer, "");
    }

    public void setLanguage(int language_){
        editor.putInt(language, language_);
        editor.commit();
    }

      public static void setUserObject(Context c, String userObject, String key) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, userObject);
        editor.commit();
    }

    public static String getUserObject(Context ctx, String key) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String userObject = pref.getString(key, null);
        return userObject;
    }



    public static void setUserObjectFinish(Context c, String userObject, String key) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, userObject);
        editor.commit();
    }

    public static String setUserObjectFinish(Context ctx, String key) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String userObjectFinish= prefs.getString(key, null);
        return userObjectFinish;
    }


}