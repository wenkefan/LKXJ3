package com.fwk.lkxj3.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.TypeListBean;
import com.fwk.lkxj3.android.ui.TypeList;
import com.fwk.lkxj3.common.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fanwenke on 16/10/11.
 */

public class TypeListAdapter extends BaseAdapter {

    private List<TypeListBean.RerurnValueBean> bean = new ArrayList<>();
    private TypeList activity;
    public TypeListAdapter(TypeList activity){
        this.activity = activity;
    }


    @Override
    public int getCount() {
        getTypeActivityCount(bean.size());
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
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
            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.xjkp_type_list_activity_adapter, null);
            holder.tvname = (TextView) convertView.findViewById(R.id.xjkp_tv_adapter_name_type_list_activity);
            holder.time = (TextView) convertView.findViewById(R.id.xjkp_tv_adapter_time_type_list_activity);
            holder.zongfen = (TextView) convertView.findViewById(R.id.xjkp_tv_adapter_zongfen_list_activity);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tvname.setText(bean.get(position).getName()+"");
        holder.time.setText(bean.get(position).getCheckDate().split("T")[1]);
        holder.zongfen.setText("总分:"+ bean.get(position).getIntCheckResult());
        return convertView;
    }

    public class HolderView {
        private TextView tvname;
        private TextView time;
        private TextView zongfen;
    }

    public void setList(List<TypeListBean.RerurnValueBean> bean) {
        this.bean = bean;
    }

    private void getTypeActivityCount(int size) {
        activity.setAdapterCount(size);
    }
}
