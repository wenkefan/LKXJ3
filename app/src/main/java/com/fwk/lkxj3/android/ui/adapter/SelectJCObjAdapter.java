package com.fwk.lkxj3.android.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.QuYuBean;

import java.util.List;


/**
 * Created by fanwenke on 16/10/13.
 */

public class SelectJCObjAdapter extends BaseAdapter {
    private Context mContext;
    private List<QuYuBean.RerurnValueBean> mList;
    private int selectItem = -1;
    public SelectJCObjAdapter(Context mContext, List<QuYuBean.RerurnValueBean> mList){
        this.mContext = mContext;
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.selectjcobj_adapter,null);
            holder.tv = (TextView) convertView.findViewById(R.id.xjkp_tv_selectobj_name);
            holder.iv = (ImageView) convertView.findViewById(R.id.xjkp_iv_selectjcobj_adapter);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        if (position == selectItem){
            holder.iv.setVisibility(View.VISIBLE);
        }else{
            holder.iv.setVisibility(View.GONE);
        }
        holder.tv.setText(mList.get(position).getName());

        return convertView;
    }
    public class HolderView{
        private TextView tv;
        private ImageView iv;
    }
    public void setSelectItme(int selectItme){
        this.selectItem = selectItme;
    }
}
