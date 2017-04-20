package com.fwk.lkxj3.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.fwk.lkxj3.R;


/**
 * Created by Administrator on 2016/7/21.
 */
public class DialogUtils {
    private ProgressDialog dialog;
    private void initDialog(Activity activity) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
    }
    public void ProgressDialogShow(Activity activity){
        initDialog(activity);
        if (dialog != null && dialog.isShowing()){
            dialog.show();
        }
    }
    public void ProgressDialogDismiss(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 等待中
     * @param context
     * @param info
     * @return 返回一个new Dialog
     */
    public static Dialog waitForDialog(Context context, String info) {
        Dialog dialog = new Dialog(context,
                android.R.style.Theme_Translucent_NoTitleBar);
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_wait_for, null);
        TextView tvTip = (TextView) view.findViewById(R.id.tv_tip);
        if (info == null || "".equals(info)) {
//            tvTip.setVisibility(View.GONE);
        } else {
            tvTip.setText(info);
        }
        dialog.setContentView(view);
        return dialog;
    }
}
