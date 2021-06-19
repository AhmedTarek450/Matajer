package com.example.matajer.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {
    private SharedPreferences msharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context context;
    private Intent mintent;

    private static final String PREF_NAME = "Matajer";

    public static final String IS_LOGIN = "isloggingin";
    public static final String KEY_ID = "keyid";
    public static final String KEY_FIRST_NAME = "frist_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";

    public SessionManagement(Context context) {
        this.context = context;
        msharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        mEditor = msharedPreferences.edit();
    }
    public void creatLoginSession (String frist_name, String last_name,String email,String phone,String password){

        mEditor.putString(KEY_FIRST_NAME,frist_name);
        mEditor.putString(KEY_LAST_NAME,last_name);
        mEditor.putString(KEY_EMAIL,email);
        mEditor.putString(KEY_PHONE,phone);
        mEditor.putString(KEY_PASSWORD,password);
        mEditor.commit();
    }
    HashMap<String,String> getUserDetails(){
        HashMap<String,String> users = new HashMap<>();
        users.put(KEY_ID,msharedPreferences.getString(KEY_ID,null));
        users.put(KEY_FIRST_NAME,msharedPreferences.getString(KEY_FIRST_NAME,null));
        users.put(KEY_LAST_NAME,msharedPreferences.getString(KEY_LAST_NAME,null));
        users.put(KEY_EMAIL,msharedPreferences.getString(KEY_EMAIL,null));
        users.put(KEY_PHONE,msharedPreferences.getString(KEY_PHONE,null));
        return users;
    }

    public void logoutuser(){
        mEditor.clear();
        mEditor.commit();
        mintent = new Intent(context,Login.class);
        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mintent);
    }
    public Boolean isLoggedin(){
        return msharedPreferences.getBoolean(IS_LOGIN,false);
    }
}
