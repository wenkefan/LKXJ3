package com.fwk.lkxj3.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.NewTypeBean.RerurnValueBean.CheckCategoryListBean;
import com.fwk.lkxj3.common.util.LogUtils;

import java.util.List;

/**
 * Created by fanwenke on 2017/4/18.
 */

public class Adapter_main_2_jibie_activity extends BaseAdapter {

    private List<CheckCategoryListBean> checkCategoryList;
    private TextView textView;

    Adapter_main_2_jibie_activity(List<CheckCategoryListBean> checkCategoryList) {
        this.checkCategoryList = checkCategoryList;
    }

    @Override
    public int getCount() {
        return checkCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return checkCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.xjkp_new_popup_recyclerview_adapter,null);
        textView = (TextView) view.findViewById(R.id.tv_xjkp_type_name);
        textView.setText(checkCategoryList.get(position).getCategoryName());
        return view;
    }


}
