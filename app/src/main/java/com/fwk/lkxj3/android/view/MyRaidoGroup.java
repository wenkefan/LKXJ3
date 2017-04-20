package com.fwk.lkxj3.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

/**
 * Created by fanwenke on 16/9/30.
 */

public class MyRaidoGroup extends RadioGroup {
    private MyRaidoGroup myRaido = null;
    public MyRaidoGroup(Context context) {
        super(context);
        initView();
    }

    public MyRaidoGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        myRaido = this;
        myRaido.getLayoutParams();
    }


}
