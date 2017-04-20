package com.fwk.lkxj3.android.ui.version2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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
import com.fwk.lkxj3.common.activity.NFCActivity;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.TimeUtils;
import com.fwk.lkxj3.common.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/10/26.
 */

public class SelectActivity extends NFCActivity implements AdapterView.OnItemClickListener {
    private static final int Flag = 1;
    private static final int Flag2 = 2;
    public static String TITLENAME;
    private TextView title;
    private TextView name;
    private ListView listView;
    private SharedPreferencesUtils sp;
    private SelectJCObjAdapter adapter;
    private List<QuYuBean.RerurnValueBean> listBean;
    private List<QuYuBean.RerurnValueBean> list;
    private int checkactegoryId;
    public String CheckObjectTable;
    public int CheckObjectCategory;
    private int CheckConclusionId;//检查结果类型
    private int ManualChooseObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_obj);
        init();
        initView();
        intURl();
    }

    private void init() {
        dialog.show();
        sp = new SharedPreferencesUtils();
        Intent intent = getIntent();
        CheckConclusionId = intent.getIntExtra(Keyword.KEY_CHECKCONCLUSIONID, 0);
        checkactegoryId = intent.getIntExtra(Keyword.KEY_CHECKCATEGORYID, 0);
        TITLENAME = intent.getStringExtra(Keyword.KEY_TYPE_LIST_NAME);
        CheckObjectTable = intent.getStringExtra(Keyword.KEY_CHECKOBJECTTABLE);
        CheckObjectCategory = intent.getIntExtra(Keyword.KEY_CHECKOBJECTCATEGORY, 0);
        ManualChooseObject = intent.getIntExtra(Keyword.KEY_MANUALCHOOSEOBJECT, 0);
        LogUtils.d("ManualChooseObject--" + ManualChooseObject);
    }

    private void initView() {
        setNewTypeVisable(false);
        setTitleString(TITLENAME);
        title = (TextView) findViewById(R.id.tv_one1);
//        name = (TextView) findViewById(R.id.xjkp_tv_selectobj_fragment);
        if (ManualChooseObject == 0){
            title.setText("请刷卡：");
        }
        listView = (ListView) findViewById(R.id.xjkp_lv_selcetobj_fragment);
        listView.setOnItemClickListener(this);
    }

    private void intURl() {
        OKHttp okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        String url = String.format(HTTPURL.API_OBJECT_LIST, sp.getInt(Keyword.LOGIN_KGID), checkactegoryId);
        LogUtils.d("检查对象：" + url);
        okHttp.getAsynHttp(Flag, url, QuYuBean.class);
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String messages = finalBean.getMessage();
                            ToastUtil.show(messages);
                        }
                    });
                }
            } catch (Exception e) {

            }
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(message);
                }
            });
        }

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Flag:
                    ImageAdapter();
                    break;
                case Flag2:
                    startActivity();
                    break;
            }
        }
    };

    private void startActivity() {
        Intent intent = new Intent(SelectActivity.this, TimuListActivity.class);
        intent.putExtra(Keyword.KEY_CHECKCATEGORYID, checkactegoryId);
        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID, CheckConclusionId);
        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME, TITLENAME);
        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE, CheckObjectTable);
        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY, CheckObjectCategory);
        intent.putExtra(Keyword.KEY_DISTRICTID, DistrictId);
        intent.putExtra(Keyword.KEY_NAME, Name);
        startActivity(intent);
        finish();
    }

    private void ImageAdapter() {
        dialog.dismiss();
        listBean = new ArrayList<>();
        list = new ArrayList<>();
        listBean = (List<QuYuBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_JIANCHADUIXIANG);
        for (QuYuBean.RerurnValueBean bean : listBean) {
//            if (bean.getParentId() != 0) {
            list.add(bean);
//            }
        }
        adapter = new SelectJCObjAdapter(MyApplication.getContext(), list);
        listView.setAdapter(adapter);
        adapter.setSelectItme(-1);
    }

    private int DistrictId;
    private String Name;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (ManualChooseObject == 1) {
            adapter.setSelectItme(position);
            adapter.notifyDataSetInvalidated();
            DistrictId = list.get(position).getDistrictId();
            Name = list.get(position).getName();
            sp.setInt(Keyword.KEY_SELCET_OBJ, position);
            startActivity();
        } else if (ManualChooseObject == 0) {
            ToastUtil.show("请刷卡！");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String cardid = readICCardNo(intent);
        NFCIdCard(cardid);
    }

    public void NFCIdCard(String cardid) {
        int cardnumber = 0;
        for (int position = 0; position < list.size(); position++) {
            if (list.get(position).getCardNo() != null) {
                if (list.get(position).getCardNo().equals(cardid)) {
                    adapter.setSelectItme(position);
                    adapter.notifyDataSetInvalidated();
                    sp.setInt(Keyword.KEY_SELCET_OBJ, position);
                    DistrictId = list.get(position).getDistrictId();
                    Name = list.get(position).getName();
                    handler.sendEmptyMessage(Flag2);
                    cardnumber++;
                }
            }
        }
        if (cardnumber == 0) {
            ToastUtil.show("没有找到检查对象");
        }
    }

    @Override
    public void initNewTypeListener() {

    }

    @Override
    public void Error() {

    }

    @Override
    public void up_page() {

    }

    @Override
    public void down_page() {

    }
}
