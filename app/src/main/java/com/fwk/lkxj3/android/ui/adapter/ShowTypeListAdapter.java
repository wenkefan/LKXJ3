package com.fwk.lkxj3.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.ShowTypeListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/10/17.
 */

public class ShowTypeListAdapter extends BaseAdapter {
    private ShowTypeListBean.RerurnValueBean mBean;
    private List<ShowTypeListBean.RerurnValueBean.XjkpCheckDetailListBean> list;
    public void getList(ShowTypeListBean.RerurnValueBean mBean) {
        this.mBean = mBean;
        list = new ArrayList<>();
        list = mBean.getXjkpCheckDetailList();
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
            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.xjkp_show_typelist_adapter, null);
            holder.order = (TextView) convertView.findViewById(R.id.xjkp_tv_order_number);
            holder.timu = (TextView) convertView.findViewById(R.id.xjkp_tv_timu);
            holder.pingjia = (TextView) convertView.findViewById(R.id.xjkp_tv_pingjia);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        holder.order.setText(position+1+".");
        holder.timu.setText(list.get(position).getItName());
        holder.pingjia.setText(list.get(position).getVcharCheckResult());
        return convertView;
    }

    public class HolderView {
        private TextView order;
        private TextView timu;
        private TextView pingjia;
    }
}
