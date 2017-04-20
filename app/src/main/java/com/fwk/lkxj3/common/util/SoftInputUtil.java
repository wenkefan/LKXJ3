package com.fwk.lkxj3.common.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @Author：Administrator
 * @Since on 2016/6/4 21:23
 * @Description：
 */
public class SoftInputUtil {

    /**
     *
     * @Title: hideSoftInputMode
     * @Description: 隐藏软键盘
     * @return void
     */
    public static void hideSoftInputMode(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View view = activity.getCurrentFocus();
        if (view != null)
            ((InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
