package com.example.tubes01e;

import android.content.Context;
import android.content.SharedPreferences;

public class MenuPreferences {
    protected SharedPreferences sharedPref;

    protected final static String NAMA_SHARED_PREF = "sp_menu";
    protected final static String KEY_INITIAL_LOAD = "INITIAL_MENU_LOADED";

    public MenuPreferences(Context context){
        this.sharedPref = context.getSharedPreferences(NAMA_SHARED_PREF, 0);
        //loadInitialValueForKey();
    }

    public void loadInitialMenu(){
        boolean isLoaded = true;
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putBoolean(KEY_INITIAL_LOAD, isLoaded);
        editor.commit();
    }

    public void loadInitialValueForKey(){
        boolean isLoaded = false;
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putBoolean(KEY_INITIAL_LOAD, isLoaded);
        editor.commit();
    }

    public boolean isLoaded(){
        return sharedPref.getBoolean(KEY_INITIAL_LOAD, false);
    }

}
