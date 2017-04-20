package com.fwk.lkxj3.MVP.ui;

import com.fwk.lkxj3.MVP.base.MVPBaseActivity;
import com.fwk.lkxj3.MVP.presenter.TypeListActivityPresenter;
import com.fwk.lkxj3.MVP.view.ITypeList_Activity_view;

/**
 * Created by fanwenke on 16/11/10.
 */

public class MVP_TypeList extends MVPBaseActivity<ITypeList_Activity_view,TypeListActivityPresenter> {
    @Override
    public TypeListActivityPresenter creatPresenter() {
        return new TypeListActivityPresenter();
    }

    @Override
    public int provideContenViewId() {
        return 0;
    }

    @Override
    public void init() {

    }
}
