package com.fwk.lkxj3.android.ui.adapter;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.JieguoType;
import com.fwk.lkxj3.android.bean.TypeListBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fanwenke on 16/10/10.
 */

public class ListViewAdapter extends BaseAdapter {
    private String[] name = {"很差", "差", "中", "良", "优"};
    private int selectItem = -1;
    private SharedPreferencesUtils sp;
    private List<JieguoType.RerurnValueBean> list = new ArrayList<>();
    private int[] leftDrawables = {R.mipmap.xjkp_btn_radio_nomal_a, R.mipmap.xjkp_btn_radio_nomal_b, R.mipmap.xjkp_btn_radio_nomal_c,
            R.mipmap.xjkp_btn_radio_nomal_d, R.mipmap.xjkp_btn_radio_nomal_e};
    public ListViewAdapter(){
        sp = new SharedPreferencesUtils();
        list = (List<JieguoType.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_JIEGUO_TYPE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder;
        if (convertView == null) {
            holder = new HolderView();
            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.xjkp_gridview_adapder, null);
            holder.tv = (TextView) convertView.findViewById(R.id.xjkp_tv_xuanxiang);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        if (position == selectItem) {
            Drawable leftDrawableLast = MyApplication.getContext().getResources().getDrawable(R.mipmap.xjkp_btn_radio_y);
            leftDrawableLast.setBounds(0, 0, leftDrawableLast.getMinimumWidth(), leftDrawableLast.getMinimumHeight());
            holder.tv.setCompoundDrawables(leftDrawableLast, null, null, null);
        } else {
            Drawable leftDrawable = MyApplication.getContext().getResources().getDrawable(leftDrawables[position]);
            leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
            holder.tv.setCompoundDrawables(leftDrawable, null, null, null);
        }
        holder.tv.setText(list.get(position).getDataDictionaryName());

        return convertView;
    }

    private class HolderView {
        private TextView tv;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
}
