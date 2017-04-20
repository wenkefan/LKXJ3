package com.fwk.lkxj3.android.ui.listener;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface OnSucceedListener {
    public <T> void OnSucceed(int flag, T cla, String message);

    public void Error();
}
