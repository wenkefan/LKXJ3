package com.fwk.lkxj3.android.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.ShowTypeListBean;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.view.photoview.PhotoView;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * 图片查看器
 * 
 * @author Ax
 */
public class LookViewPagerAdapter extends PagerAdapter {
    // 图片数据
    private List<ShowTypeListBean.RerurnValueBean.XjkpCheckPhotosListModeBean> pho;
    // 图片控件数组
    private PhotoView[] image;
    // 上下文
    private Context context;
    private Activity activity;
    // 点击图片时间间隔
    private long cur = 0;

    public LookViewPagerAdapter(List<ShowTypeListBean.RerurnValueBean.XjkpCheckPhotosListModeBean> pho, Context context,
                                Activity activity) {
        this.pho = pho;
        this.context = context;
        this.activity = activity;

        image = new PhotoView[pho.size()];
        int size = pho.size();
        // 设置图片样式
        for (int i = 0; i < size; i++) {
            PhotoView imageView = new PhotoView(context);
            imageView.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ScaleType.CENTER_INSIDE);
            image[i] = imageView;
        }
    }

    @Override
    public int getCount() {
        return pho.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        PhotoView imageView = image[position];
        container.addView(imageView);
        // 加载失败 configDefaultLoadFailedImage(R.drawable.mpo);

        // 加载中configDefaultLoadingImage(R.drawable.jz);
        // 图片URL连网显示
        new BitmapUtils(context)
                .configDefaultLoadFailedImage(R.mipmap.mpo)
                .configDefaultLoadingImage(R.mipmap.jz)
                .display(
                        imageView,
                        HTTPURL.IMGURL
                                + pho.get(position).getPhotoFile());
        // 图片控件双击屏幕监听
        // imageView.setOnClickListener(new OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // if ((System.currentTimeMillis() - cur) > 300) {
        // cur = System.currentTimeMillis();
        // } else {
        // activity.finish();
        // }
        // }
        // });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        PhotoView imageView = image[position];
        container.removeView(imageView);

    }

}
