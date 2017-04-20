package com.fwk.lkxj3.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.NewTypeBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.ui.listener.onIconNewListener;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.util.List;


/**
 * Created by fanwenke on 16/9/18.
 */
public class New_PopupAdapter extends RecyclerView.Adapter<New_PopupAdapter.MyRecyclerViewHolder> implements View.OnClickListener {
    private Context mContext;
    private SharedPreferencesUtils sp;
    private List<NewTypeBean.RerurnValueBean> listBeen;
    private int[] imageId = {R.mipmap.tabbar_compose_camera, R.mipmap.tabbar_compose_idea,
            R.mipmap.tabbar_compose_lbs, R.mipmap.tabbar_compose_more,
            R.mipmap.tabbar_compose_photo, R.mipmap.tabbar_compose_review};

    public New_PopupAdapter(Context mContext) {
        this.mContext = mContext;
        sp = new SharedPreferencesUtils();
        listBeen = (List<NewTypeBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_NEW_TYPE_LIST);
    }
    public static onIconNewListener IconNewListener;
    public static void setOnIconSelcetListener(onIconNewListener onIconNewListener){
        IconNewListener = onIconNewListener;
    }
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.xjkp_new_popup_recyclerview_adapter, parent, false);
        MyRecyclerViewHolder holder = new MyRecyclerViewHolder(layout);
        layout.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        int number = position;
        while (number >= imageId.length){
            number = number - imageId.length;
        }
        holder.iv.setImageResource(imageId[number]);
        holder.tv.setText(listBeen.get(position).getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    @Override
    public void onClick(View view) {
        IconNewListener.onIconSelcetListener((int)view.getTag());
    }


    class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;
        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_xjkp_type_name);
//            iv = (ImageView) itemView.findViewById(R.id.iv_xjkp_type_icon);
        }
    }
}
