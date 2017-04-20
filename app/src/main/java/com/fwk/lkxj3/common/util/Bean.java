package com.fwk.lkxj3.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.android.constant.Keyword;

/**
 * Created by fanwenke on 16/8/19.
 */
public class Bean {
    public static final SharedPreferences SP = MyApplication.getContext()
            .getSharedPreferences(Keyword.CONTEXTKEY, Context.MODE_PRIVATE);
}
