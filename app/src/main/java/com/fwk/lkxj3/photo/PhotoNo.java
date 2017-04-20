package com.fwk.lkxj3.photo;

import android.os.Bundle;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.common.activity.BaseActvity;

/**
 * Created by fanwenke on 16/11/6.
 */

public class PhotoNo extends BaseActvity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_no_ativity);
        init();
    }

    private void init() {
        setNewTypeVisable(false);
        setTitleString("查看图片");
    }

    @Override
    public void initNewTypeListener() {

    }

    @Override
    public <T> void OnSucceed(int flag, T cla, String message) {

    }

    @Override
    public void Error() {

    }

    @Override
    public void up_page() {

    }

    @Override
    public void down_page() {

    }
}
