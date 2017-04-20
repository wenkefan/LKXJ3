package com.fwk.lkxj3.android.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.CreatType;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.ui.NewTypeActivity;
import com.fwk.lkxj3.android.ui.adapter.ListViewAdapter;
import com.fwk.lkxj3.common.activity.BaseFragment;
import com.fwk.lkxj3.common.util.LogUtils;

import java.util.Map;

/**
 * Created by fanwenke on 16/9/23.
 */
public class OneFragment extends BaseFragment {

    private static Map<Integer, Integer> map;
//    private Activity mContext;
    private CreatType creattype;
    private ListViewAdapter adapter1;
    private ListViewAdapter adapter2;

    private TextView title1;
    private TextView title2;
    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private TextView order1;
    private TextView order2;
    private GridView xuanxiang;
    private GridView xuanxiang1;

    private int currentNumber;
    private int total_number;
    private int yushu;
    private int one_title;//第一个题目
    private int twe_title;//第二个题目

    public OneFragment(){}

    @Override
    public void onFragmentCreate(ViewGroup rootView) {
        setContentView(R.layout.yanshi_one_fragement);
        init();
        initView(rootView);
        map = NewTypeActivity.getSelectAdapter();

    }

    private void init() {//初始化数据
        Bundle bundle = getArguments();
        currentNumber = bundle.getInt(Keyword.KEY_CURRENT_NUMBER);
        creattype = (CreatType) bundle.getSerializable(Keyword.SP_XIANGXI_NEIRONG);
        total_number = bundle.getInt(Keyword.KEY_FRAGMENT_TOTAL_NUMBER);
        yushu = bundle.getInt(Keyword.KEY_YUSHU);
        one_title = currentNumber * Keyword.FRAGMENT_ITME + 1;
        twe_title = currentNumber * Keyword.FRAGMENT_ITME + 2;
    }

    private void initView(ViewGroup rootView) {//初始化控件
        BJCRVisiblity(true);
        TIMEVisiblity(false);
        ZFVisiblity(true);
        title1 = (TextView) rootView.findViewById(R.id.xjkp_tv_title_1);
        title2 = (TextView) rootView.findViewById(R.id.xjkp_tv_title_2);
        layout1 = (RelativeLayout) rootView.findViewById(R.id.xjkp_rl_title1);
        layout2 = (RelativeLayout) rootView.findViewById(R.id.xjkp_rl_title2);
        order1 = (TextView) rootView.findViewById(R.id.tv_one2);
        order2 = (TextView) rootView.findViewById(R.id.tv_one3);
        xuanxiang = (GridView) rootView.findViewById(R.id.xjkp_gv_xuanxiang);
        xuanxiang1 = (GridView) rootView.findViewById(R.id.xjkp_gv_xuanxiang1);
        setTitleString(currentNumber * Keyword.FRAGMENT_ITME);
        setSelectAdapter();
    }
    private void setZFView(int zongfen){
        setZF("总分：" + zongfen);
    }
    private void setSelectAdapter() {
        adapter1 = new ListViewAdapter();
        xuanxiang.setAdapter(adapter1);
        xuanxiang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter1.setSelectItem(position);
                adapter1.notifyDataSetInvalidated();
                int zf1 = NewTypeActivity.setSelcetAdapter(one_title, position);
                NewTypeActivity.ZF = zf1;
                setZFView(zf1);
            }
        });

        adapter2 = new ListViewAdapter();
        xuanxiang1.setAdapter(adapter2);
        xuanxiang1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter2.setSelectItem(position);
                adapter2.notifyDataSetInvalidated();
               int zf2 = NewTypeActivity.setSelcetAdapter(twe_title, position);
                NewTypeActivity.ZF = zf2;
                setZFView(zf2);
            }
        });
    }

    private void setTitleString(int i) {
        i = i - 4;
        if (currentNumber == total_number - 1) {
            LogUtils.d("currentNumber:" + currentNumber + "total_number:" +total_number +"----" + yushu);
            if (yushu != 0) {
                layout2.setVisibility(View.INVISIBLE);
                title1.setText(getTitleString(i));
                order1.setText((i + 1) + ".");
                return;
            }
        }
        title1.setText(getTitleString(i));
        title2.setText(getTitleString(i + 1));
        order1.setText((i + 1) + ".");
        order2.setText((i + 2) + ".");
    }

    private String getTitleString(int key) {
        String keys = null;
        try{
            keys = creattype.getRerurnValue().get(key).getItName();
            LogUtils.d(keys);
        }catch (Exception e){
            getActivity().finish();
        }
        return keys;
    }

    @Override
    public void onFragmnetResume() {//展现选择项
        setJCR(NewTypeActivity.JC);
        setBJCR(NewTypeActivity.BJC);
        setTIME(NewTypeActivity.TIME);
        setBT(NewTypeActivity.TITLENAME);
        setZFView(NewTypeActivity.ZF);
        int a = -1;
        int b = -1;
        try {
            a = map.get(one_title);
            b = map.get(twe_title);
        } catch (Exception e) {

        }
        adapter1.setSelectItem(a);
        adapter2.setSelectItem(b);
    }

    @Override
    public void onFragmentDestory() {

    }
}
