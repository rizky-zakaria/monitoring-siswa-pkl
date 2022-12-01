package com.example.monitoringsiswapkl.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.monitoringsiswapkl.model.DataLogin;

import java.util.HashMap;

public class SharedPreferences {
    private Context context;
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    public static final String ISLOGED = "isLogin";

    public static final String USERID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    public SharedPreferences(Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    private void createLoginSession(DataLogin dataLogin){
        editor.putBoolean(ISLOGED, true);
        editor.putString(USERNAME, dataLogin.getUsername());
        editor.putString(PASSWORD, dataLogin.getPassword());
        editor.putString(USERID, dataLogin.getId());
        editor.putString(ROLE, dataLogin.getRole());
        editor.commit();
    }

    public HashMap<String, String>getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USERID, sharedPreferences.getString(USERID, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(ROLE, sharedPreferences.getString(ROLE, null));
        return  user;
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public  boolean isLogedIn(){
        return sharedPreferences.getBoolean(ISLOGED, false);
    }

    public String getRole()
    {
        return sharedPreferences.getString(ROLE, null);
    }

    public String getUsername(){
        return  sharedPreferences.getString(USERNAME, null);
    }

    public  String getPassword(){
        return sharedPreferences.getString(PASSWORD, null);
    }
}
