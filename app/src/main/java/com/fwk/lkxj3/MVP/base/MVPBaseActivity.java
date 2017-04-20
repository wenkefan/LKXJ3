package com.fwk.lkxj3.MVP.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by fanwenke on 16/11/10.
 */

public abstract class MVPBaseActivity<V extends IMVP_view,T extends MVPBasePresenter<V>> extends AppCompatActivity {

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (creatPresenter() != null){
            mPresenter = creatPresenter();
            mPresenter.attachView((V) this);
        }

        setContentView(provideContenViewId());
        ButterKnife.inject(this);
        init();
    }

    public abstract T creatPresenter();
    public abstract int provideContenViewId();
    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
