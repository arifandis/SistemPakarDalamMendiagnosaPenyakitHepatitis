package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Admin;

public class AdminPreference {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public AdminPreference(Context context) {
        pref = context.getSharedPreferences(Global.PREF_NAME,Context.MODE_PRIVATE);
        editor = context.getSharedPreferences(Global.PREF_NAME,Context.MODE_PRIVATE).edit();
    }

    public void setAdminLogin(Admin admin){
        editor.putString("username",admin.getUsername());
        editor.putString("password",admin.getPassword());
        editor.apply();
    }

    public Admin getAdminLogin(){
        String username = pref.getString("username",null);
        String password = pref.getString("password",null);

        if (username == null){
            return null;
        }else{
            return new Admin(username,password);
        }
    }

    public void clearAdminLogged(){
        editor.clear();
        editor.apply();
    }
}
