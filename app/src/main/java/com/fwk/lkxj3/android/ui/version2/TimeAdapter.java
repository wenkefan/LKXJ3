package com.fwk.lkxj3.android.ui.version2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.CreatType;
import com.fwk.lkxj3.android.bean.TijiaoBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fanwenke on 16/10/25.
 */

public class TimeAdapter extends BaseAdapter {

    private List<CreatType.RerurnValueBean> listBeen = new ArrayList<>();
    private ListViewAdapter adapter;
    private Map<Integer, Integer> map;
    private TimuListActivity mActivity;
    private boolean isFen = false;
    private SharedPreferencesUtils sp = new SharedPreferencesUtils();
//    private List<CreatType.RerurnValueBean> date;
    public TimeAdapter() {
        super();
    }

    public TimeAdapter(TimuListActivity timuListActivity) {
        this.mActivity = timuListActivity;
//        this.date = (List<CreatType.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_JIANCHA);
    }

    public void setData(List<CreatType.RerurnValueBean> bean) {
        this.listBeen = bean;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
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
    public View getView(final int positions, View convertView, ViewGroup parent) {
        final HolderView holder;
        final int[] fenshu = {0};
        if (convertView == null) {
            holder = new HolderView();
            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.version2_adapter, null);
            holder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);
            holder.xuhao = (TextView) convertView.findViewById(R.id.tv_one2);
            holder.biaoti = (TextView) convertView.findViewById(R.id.xjkp_tv_title_1);
            holder.gridView = (GridView) convertView.findViewById(R.id.xjkp_gv_xuanxiang);

            holder.ll = (LinearLayout) convertView.findViewById(R.id.ll);
            holder.jian = (ImageView) convertView.findViewById(R.id.jian);
            holder.zhong = (TextView) convertView.findViewById(R.id.zhong);
            holder.jia = (ImageView) convertView.findViewById(R.id.jia);
            holder.fenshuji = (TextView) convertView.findViewById(R.id.zudazhi);

            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        holder.xuhao.setText(positions + 1 + ".");
        holder.biaoti.setText(listBeen.get(positions).getItName());

        if (isFen) {
            holder.rl.setVisibility(View.GONE);
            holder.ll.setVisibility(View.VISIBLE);
            holder.zhong.setText(fenshu[0]+"");
            String cc = "(" + listBeen.get(positions).getMinScore() + "~" + listBeen.get(positions).getMaxScore() + ")";
            holder.fenshuji.setText(cc);
            holder.jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fenshu[0] != listBeen.get(positions).getMinScore()) {
                        fenshu[0] = fenshu[0] - 1;
                    }
                    holder.zhong.setText(fenshu[0] + "");
                    map.put(positions,fenshu[0]);
                    mActivity.getFenMap(map);
                }
            });

            holder.jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fenshu[0] != listBeen.get(positions).getMaxScore()){
                        fenshu[0] = fenshu[0] + 1;
                    }
                    holder.zhong.setText(fenshu[0] + "");
                    map.put(positions,fenshu[0]);
                    mActivity.getFenMap(map);
                }
            });

        } else {
            holder.rl.setVisibility(View.VISIBLE);
            holder.ll.setVisibility(View.GONE);
            adapter = new ListViewAdapter(positions);
            holder.gridView.setAdapter(adapter);
            adapter.setSelectItem(Integer.valueOf(map.get(positions)));
            adapter.notifyDataSetChanged();
            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    map.put(positions, position);
                    mActivity.getMap(map, positions, position);
                }
            });
        }
        return convertView;
    }

    private class HolderView {
        private RelativeLayout rl;
        private TextView xuhao;
        private TextView biaoti;
        private GridView gridView;

        private LinearLayout ll;
        private ImageView jian;
        private TextView zhong;
        private ImageView jia;
        private TextView fenshuji;
    }

    public void getIsSelectFen(boolean is) {
        this.isFen = is;
    }
}
