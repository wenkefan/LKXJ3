package com.fwk.lkxj3.android.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.QuYuBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.ui.NewTypeActivity;
import com.fwk.lkxj3.android.ui.adapter.SelectJCObjAdapter;
import com.fwk.lkxj3.android.ui.listener.OnSucceedListener;
import com.fwk.lkxj3.common.activity.BaseFragment;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.TimeUtils;
import com.fwk.lkxj3.common.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/10/13.
 */

public class SelectJCObj extends BaseFragment implements OnSucceedListener, AdapterView.OnItemClickListener {
    private static final int Flag = 1;
    private TextView tv;
    private TextView name;
    private ListView listView;
    private SharedPreferencesUtils sp;
    private SelectJCObjAdapter adapter;
    private List<QuYuBean.RerurnValueBean> listBean;
    private List<QuYuBean.RerurnValueBean> list;
    private static NewTypeActivity activit;
    private NewTypeActivity activitys;
    private int checkactegoryId;
    public SelectJCObj(){

    }
    public static final SelectJCObj newInstance(NewTypeActivity activity)
    {
        SelectJCObj fragment = new SelectJCObj();
        activit = activity;
        return fragment ;
    }
    public String CheckObjectTable;
    public int CheckObjectCategory;
    @Override
    public void onFragmentCreate(ViewGroup rootView) {
        setContentView(R.layout.fragment_select_obj);
        init();
        initView(rootView);
        intURl();
    }

    private void init() {
        activitys = activit;
        dialog.show();
        sp = new SharedPreferencesUtils();
        NewTypeActivity.TIME = TimeUtils.getyyyy_MM_ddString();
        Bundle bundle = getArguments();
        CheckObjectTable = bundle.getString(Keyword.KEY_CHECKOBJECTTABLE);
        CheckObjectCategory = bundle.getInt(Keyword.KEY_CHECKOBJECTCATEGORY);
        checkactegoryId = bundle.getInt(Keyword.KEY_CHECKCATEGORYID);
    }

    private void initView(ViewGroup rootView) {
        BJCRVisiblity(false);
        TIMEVisiblity(true);
        ZFVisiblity(false);
        tv = (TextView) rootView.findViewById(R.id.xjkp_tv_selectobj_fragment);
        name = (TextView) rootView.findViewById(R.id.xjkp_tv_selectobj_fragment);
        listView = (ListView) rootView.findViewById(R.id.xjkp_lv_selcetobj_fragment);
        listView.setOnItemClickListener(this);
    }

    private void intURl() {
        OKHttp okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        String url = String.format(HTTPURL.API_OBJECT_LIST, sp.getInt(Keyword.LOGIN_KGID),checkactegoryId);
        LogUtils.d("检查对象：" + url);
        okHttp.getAsynHttp(Flag, url, QuYuBean.class);
    }

    @Override
    public void onFragmnetResume() {
        setJCR(NewTypeActivity.JC);
        setBJCR(NewTypeActivity.BJC);
        setTIME(NewTypeActivity.TIME);
        setBT(NewTypeActivity.TITLENAME);
//        setZF(NewTypeActivity.ZF);
    }

    @Override
    public void onFragmentDestory() {

    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null) {
            try {
                QuYuBean bean = (QuYuBean) cla;

                if (bean.getRerurnValue() != null) {
                    sp.saveToShared(Keyword.SP_JIANCHADUIXIANG, bean.getRerurnValue());
                    handler.sendEmptyMessage(Flag);
                    bean = null;
                } else {
                    final QuYuBean finalBean = bean;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String messages = finalBean.getMessage();
                            ToastUtil.show(messages);
                        }
                    });
                }
            }catch (Exception e){

            }
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(message);
                }
            });
        }

    }

    @Override
    public void Error() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Flag:
                    ImageAdapter();
                    break;
            }
        }
    };

    private void ImageAdapter() {
        dialog.dismiss();
        listBean = new ArrayList<>();
        list = new ArrayList<>();
        listBean = (List<QuYuBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_JIANCHADUIXIANG);
        for (QuYuBean.RerurnValueBean bean : listBean) {
            if (bean.getParentId() != 0) {
                list.add(bean);
            }
        }
        adapter = new SelectJCObjAdapter(MyApplication.getContext(), list);
        listView.setAdapter(adapter);
        try {
            int value = sp.getInt(Keyword.KEY_SELCET_OBJ);
            adapter.setSelectItme(value);
        } catch (Exception e) {
            adapter.setSelectItme(-1);
        }
    }
    private int DistrictId;
    private String Name;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setSelectItme(position);
        adapter.notifyDataSetInvalidated();
        NewTypeActivity.BJC = list.get(position).getName();
        DistrictId = list.get(position).getDistrictId();
        Name = list.get(position).getName();
        setBJCR(Name);
        sp.setInt(Keyword.KEY_SELCET_OBJ, position);
        activitys.objDown();
        setData();
    }
    public void setData(){//向acitivy传区域ID和name
        activitys.getSelectjCobjFramgent(DistrictId,Name);
    }
    public void NFCIdCard(String cardid) {
        int cardnumber = 0;
        for (int position = 0; position < list.size(); position++) {
            if (list.get(position).getCardNo() != null) {
                if (list.get(position).getCardNo().equals(cardid)){
                    adapter.setSelectItme(position);
                    adapter.notifyDataSetInvalidated();
                    NewTypeActivity.BJC = list.get(position).getName();
                    sp.setInt(Keyword.KEY_SELCET_OBJ, position);
                    activitys.objDown();
                    cardnumber++;
                }
            }
        }
        if (cardnumber == 0){
            ToastUtil.show("没有找到检查对象");
        }
    }
}
