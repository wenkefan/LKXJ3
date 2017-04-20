package com.fwk.lkxj3.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.NewTypeBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/10/12.
 */

public class Adapter_main_activity extends BaseAdapter {
    private int[] imageId = {R.mipmap.main_bjws,
            R.mipmap.main_cfgz,
            R.mipmap.main_rcjx,
            R.mipmap.main_jxfk,
            R.mipmap.main_tkpk,
            R.mipmap.main_yrws,
            R.mipmap.main_xcaq,
            R.mipmap.main_xcws,
            R.mipmap.main_nwjc};
    private SharedPreferencesUtils sp;

    private TextView tv;
    private List<NewTypeBean.RerurnValueBean> listBeen;

    public Adapter_main_activity() {
        sp = new SharedPreferencesUtils();
        listBeen = (List<NewTypeBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_NEW_TYPE_LIST);
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int itme, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.xjkp_new_1_adapter,null);
        tv = (TextView) view.findViewById(R.id.tv_1_biaoti);
         ListView listView = (ListView) view.findViewById(R.id.lv_2_biaoti);

        Adapter_main_2_jibie_activity adapter = new Adapter_main_2_jibie_activity(listBeen.get(itme).getCheckCategoryList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listener.setOnItemListener(itme,position);
            }
        });

        tv.setText(listBeen.get(itme).getName());
        return view;
    }

    OnItemListener listener;

    public void setOnListener(OnItemListener listener){
        this.listener = listener;
    }

    public interface OnItemListener{
        void setOnItemListener(int item,int position);
    }
}
