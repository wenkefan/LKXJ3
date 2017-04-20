package com.fwk.lkxj3.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.ui.fragment.SetImage;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SDCardImageLoader;
import com.fwk.lkxj3.common.util.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by fanwenke on 16/10/14.
 */

public class Fragment_Photo_Adapter extends BaseAdapter implements View.OnClickListener {

    private SDCardImageLoader loader;
    private SetImage fragment;
    public ArrayList<String> imagePathList;

    public Fragment_Photo_Adapter(ArrayList<String> photoPathList, SetImage fragment) {
        this.imagePathList = photoPathList;
        this.fragment = fragment;
        loader = new SDCardImageLoader(ScreenUtils.getScreenW(),
                ScreenUtils.getScreenH());
    }

    @Override
    public int getCount() {
        return imagePathList == null ? 0 : imagePathList.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String filePath = (String) getItem(position);

        final TestViewHolder holder;
        if (convertView == null) {
            holder = new TestViewHolder();
            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.xjkp_main_gridview_item2, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.main_gridView_item_photo);
            holder.imageViewdele = (ImageView) convertView.findViewById(R.id.xjkp_iv_dele);
            holder.imageViewdele.setOnClickListener(this);
            convertView.setTag(holder);
        } else {
            holder = (TestViewHolder) convertView.getTag();
        }
        holder.imageViewdele.setVisibility(View.VISIBLE);
        if (imagePathList.get(position).equals("first")) {
            holder.imageView.setImageResource(R.mipmap.addphoto_cj);
            holder.imageViewdele.setVisibility(View.GONE);
            LogUtils.d("getPosition-true" + position);

        } else {
            holder.imageView.setTag(filePath);
            loader.loadImage(1, filePath, holder.imageView);
            holder.imageViewdele.setTag(position);
            LogUtils.d("getPosition-false" + position);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        fragment.deleImageView((int)v.getTag());
    }


    public class TestViewHolder {
        ImageView imageView;
        ImageView imageViewdele;

    }

    public void setImagePathList(ArrayList<String> list) {
        this.imagePathList = list;
    }
}
