package com.fwk.lkxj3.common.activity;

import android.view.ViewGroup;

/**
 * Created by fanwenke on 16/9/29.
 */

public interface IFragmentLoad {
    public void onFragmentCreate(ViewGroup rootView);

    public void onFragmnetResume();

    public void onFragmentDestory();
}
