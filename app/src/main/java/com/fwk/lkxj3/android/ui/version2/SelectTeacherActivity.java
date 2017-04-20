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
import com.fwk.lkxj3.android.bean.WorkerExtensionBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.ui.adapter.SelectJCObjAdapter;
import com.fwk.lkxj3.android.ui.adapter.SelectJCObjTeacherAdapter;
import com.fwk.lkxj3.common.activity.NFCActivity;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanwenke on 16/11/11.
 */

public class SelectTeacherActivity extends NFCActivity implements AdapterView.OnItemClickListener {
    private static final int Flag = 1;
    private static final int Flag2 = 2;
    public static String TITLENAME;
    private TextView tv;
    private TextView name;
    private ListView listView;
    private SharedPreferencesUtils sp;
    private SelectJCObjTeacherAdapter adapter;
    private List<WorkerExtensionBean.RerurnValueBean> listBean;
//    private List<WorkerExtensionBean.RerurnValueBean> list;
    private int checkactegoryId;
    public String CheckObjectTable;
    public int CheckObjectCategory;
    private int CheckConclusionId;//检查结果类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_teacher_obj);
        init();
        initView();
//        intURl();
        ImageAdapter();
    }

    private void init() {
        dialog.show();
        sp = new SharedPreferencesUtils();
        Intent intent = getIntent();
        CheckConclusionId = intent.getIntExtra(Keyword.KEY_CHECKCONCLUSIONID, 0);
        checkactegoryId = intent.getIntExtra(Keyword.KEY_CHECKCATEGORYID, 0);
        TITLENAME = intent.getStringExtra(Keyword.KEY_TYPE_LIST_NAME);
        CheckObjectTable = intent.getStringExtra(Keyword.KEY_CHECKOBJECTTABLE);
        DistrictId = intent.getIntExtra(Keyword.KEY_DISTRICTID,0);
        Name = intent.getStringExtra(Keyword.KEY_NAME);
    }

    private void initView() {
        setNewTypeVisable(false);
        setTitleString(TITLENAME);
        tv = (TextView) findViewById(R.id.xjkp_tv_selectobj_fragment);
        name = (TextView) findViewById(R.id.xjkp_tv_selectobj_fragment);
        listView = (ListView) findViewById(R.id.xjkp_lv_selcetobj_teacher_fragment);
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
    public <T> void OnSucceed(int flag, T cla, final String message) {
//        if (cla != null) {
//            try {
//                QuYuBean bean = (QuYuBean) cla;
//
//                if (bean.getRerurnValue() != null) {
//                    sp.saveToShared(Keyword.SP_JIANCHADUIXIANG, bean.getRerurnValue());
//                    handler.sendEmptyMessage(Flag);
//                    bean = null;
//                } else {
//                    final QuYuBean finalBean = bean;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String messages = finalBean.getMessage();
//                            ToastUtil.show(messages);
//                        }
//                    });
//                }
//            }catch (Exception e){
//
//            }
//        } else {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    ToastUtil.show(message);
//                }
//            });
//        }

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Flag:
//                    ImageAdapter();
                    break;
                case Flag2:
                    startActivity();
                    break;
            }
        }
    };

    private void startActivity() {
        Intent intent = new Intent(SelectTeacherActivity.this,TimuListActivity.class);
        intent.putExtra(Keyword.KEY_CHECKCATEGORYID, checkactegoryId);
        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID, CheckConclusionId);
        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME, TITLENAME);
        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE,CheckObjectTable);
        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY,CheckObjectCategory);
        intent.putExtra(Keyword.KEY_DISTRICTID,DistrictId);
        intent.putExtra(Keyword.KEY_NAME,Name);
        intent.putExtra(Keyword.KEY_SELCET_TEACHER_OBJ,WorkerId);
        startActivity(intent);
        finish();
    }

    private void ImageAdapter() {
        dialog.dismiss();
//        list = new ArrayList<>();
        listBean = (List<WorkerExtensionBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_WORKEREXTENSION);
        LogUtils.d(listBean.size()+"---size");
        adapter = new SelectJCObjTeacherAdapter(this, listBean);
        listView.setAdapter(adapter);
        adapter.setSelectItme(-1);
        LogUtils.d(adapter.getCount()+"");

    }
    private int DistrictId;
    private String Name;
    private int WorkerId;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setSelectItme(position);
        adapter.notifyDataSetInvalidated();
        WorkerId = listBean.get(position).getWorkerExtensionId();
        sp.setInt(Keyword.KEY_SELCET_TEACHER_OBJ, position);
        startActivity();
    }

    @Override
    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        String cardid = readICCardNo(intent);
//        NFCIdCard(cardid);
    }

    public void NFCIdCard(String cardid) {
//        int cardnumber = 0;
//        for (int position = 0; position < list.size(); position++) {
//            if (list.get(position).getCardNo() != null) {
//                if (list.get(position).getCardNo().equals(cardid)){
//                    adapter.setSelectItme(position);
//                    adapter.notifyDataSetInvalidated();
//                    sp.setInt(Keyword.KEY_SELCET_OBJ, position);
//                    DistrictId = list.get(position).getDistrictId();
//                    Name = list.get(position).getName();
//                    handler.sendEmptyMessage(Flag2);
//                    cardnumber++;
//                }
//            }
//        }
//        if (cardnumber == 0){
//            ToastUtil.show("没有找到检查对象");
//        }
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
