package com.fwk.lkxj3.common.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/7/20.
 */
public class LogUtils {
    private static final String TAG = "fanwenke";
    public static void d(String text){
        Log.d(TAG,text);
    }
    public static void e(String text){
        Log.e(TAG,text);
    }
}
