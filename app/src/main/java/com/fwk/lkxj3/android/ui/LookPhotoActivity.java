package com.fwk.lkxj3.android.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.ShowTypeListBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.ui.adapter.LookViewPagerAdapter;
import com.fwk.lkxj3.android.view.ViewPagers;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fanwenke on 16/10/20.
 */

public class LookPhotoActivity extends BaseActvity {


    @InjectView(R.id.viewpager_look)
    ViewPagers viewPager;
    private LookViewPagerAdapter adapter;
    private List<ShowTypeListBean.RerurnValueBean.XjkpCheckPhotosListModeBean> pho;
    private SharedPreferencesUtils sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photograph_look);
        ButterKnife.inject(this);
        setNewTypeVisable(false);
        setTitleString("查看图片");
        sp = new SharedPreferencesUtils();
        pho = (List<ShowTypeListBean.RerurnValueBean.XjkpCheckPhotosListModeBean>) sp.queryForSharedToObject("photo");
        adapter = new LookViewPagerAdapter(pho,this,LookPhotoActivity.this);
        viewPager.setAdapter(adapter);

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
