package com.fwk.lkxj3.android.constant;

import android.content.Context;
import android.content.SharedPreferences;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

/**
 * Created by fanwenke on 16/11/4.
 */

public class SpLogin {
    private static SharedPreferences SP = MyApplication.getContext().getSharedPreferences(Keyword.CONTEXTKEY, Context.MODE_PRIVATE);

    public static void save(int kgid, String kgname, String name, int userid, int workerextensionid){
        SharedPreferences.Editor editor = SP.edit();
        editor.putInt(Keyword.LOGIN_KGID,kgid);
        editor.putString(Keyword.LOGIN_KGNAME,kgname);
        editor.putString(Keyword.LOGIN_NAME,name);
        editor.putInt(Keyword.LOGIN_USERID,userid);
        editor.putInt(Keyword.LOGIN_WORKEREXTENSIONID,workerextensionid);
        editor.commit();
    }

    public static int getKgId(){
        return SP.getInt(Keyword.LOGIN_KGID,0);
    }
    public static String getKgName(){
        return SP.getString(Keyword.LOGIN_KGNAME,"");
    }
    public static String getName(){
        return SP.getString(Keyword.LOGIN_NAME,"");
    }
    public static int getUserId(){
        return SP.getInt(Keyword.LOGIN_USERID,0);
    }
    public static int getWorkerxtensionId(){
        return SP.getInt(Keyword.LOGIN_WORKEREXTENSIONID,0);
    }

    /**
     * 设置是否登录
     *
     * @param loggedIn
     */
    public static void setAlreadyLogin(boolean loggedIn) {
        SharedPreferences.Editor editor = SP.edit();
        editor.putBoolean(Keyword.LOGIN_ALREADYLOGIN, loggedIn);
        editor.commit();
    }

    public static boolean getAlreadyLogin() {
        return SP.getBoolean(Keyword.LOGIN_ALREADYLOGIN, false);
    }
}
