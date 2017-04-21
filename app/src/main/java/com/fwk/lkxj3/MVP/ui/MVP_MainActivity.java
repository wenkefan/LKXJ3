package com.fwk.lkxj3.MVP.ui;

import android.widget.GridView;
import android.widget.ImageView;

import com.fwk.lkxj3.MVP.KeyValue;
import com.fwk.lkxj3.MVP.base.MVPBaseActivity;
import com.fwk.lkxj3.MVP.presenter.MainActivityPresenter;
import com.fwk.lkxj3.MVP.view.IMain_Activity_view;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.constant.SpLogin;
import com.fwk.lkxj3.android.okhttp.HTTPURL;

import butterknife.InjectView;

/**
 * Created by fanwenke on 16/11/10.
 */

public class MVP_MainActivity extends MVPBaseActivity<IMain_Activity_view,MainActivityPresenter> implements IMain_Activity_view {


//    @InjectView(R.id.xjkp2_iv_main_activity)
//    ImageView switchUser;
    @InjectView(R.id.xjkp2_gv_main_activity)
    GridView gridView;

    @Override
    public MainActivityPresenter creatPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    public int provideContenViewId() {
        return R.layout.xjkp2_main_activity;
    }

    @Override
    public void init() {
        mPresenter.initView();
        String url = String.format(HTTPURL.API_NEW_TYPE, SpLogin.getKgId());
        mPresenter.setTypeList(KeyValue.SP_TYPE_FLAG,url);
    }

    @Override
    public ImageView getImageView() {
//        return switchUser;
        return null;
    }

    @Override
    public GridView getGridView() {
        return gridView;
    }
}
