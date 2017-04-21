package com.fwk.lkxj3.android.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.WorkerExtensionBean;
import com.fwk.lkxj3.android.constant.SpLogin;
import com.fwk.lkxj3.android.ui.adapter.Adapter_main_activity;
import com.fwk.lkxj3.android.bean.NewTypeBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.IconNumber;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fanwenke on 16/10/12.
 */

public class MainActivity extends BaseActvity implements Adapter_main_activity.OnItemListener {

    private static final int Flag1 = 1;
    private static final int Flag1_1 = 101;
    private static final int Flag2 = 2;
//    @InjectView(R.id.xjkp2_iv_main_activity)
//    ImageView xjkp2IvMainActivity;
    @InjectView(R.id.xjkp2_gv_main_activity)
    ListView listView;

    private OKHttp okHttp;
    private SharedPreferencesUtils sp;
    private Adapter_main_activity adapter;
    private NewTypeBean typeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SpLogin.getAlreadyLogin()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.xjkp2_main_activity);
        ButterKnife.inject(this);
        setTitleVisable(true);
        setBackViewVisable(false);
        init();
        initURL();
    }

    private void initLogin() {
        if (!SpLogin.getAlreadyLogin()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }
    }

    @Override
    public void initNewTypeListener() {
        ivnewtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定注销当前用户");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SpLogin.setAlreadyLogin(false);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        MainActivity.this.finish();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void init() {
        sp = new SharedPreferencesUtils();

        ivnewtype.setImageDrawable(getResources().getDrawable(R.mipmap.user_qiehuan));
    }

    private void initURL() {
        okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        String url = String.format(HTTPURL.API_NEW_TYPE, SpLogin.getUserId());
        LogUtils.d("获取集合：" + url);
        okHttp.getAsynHttp(Flag1, url, NewTypeBean.class);

//        String url1 = String.format(HTTPURL.API_WORKEREXTENSION, SpLogin.getKgId());
//        LogUtils.d("获取职工：" + url1);
//        okHttp.getAsynHttp(Flag2, url1, WorkerExtensionBean.class);
    }

    private void setAdapter() {
        setTitleString(SpLogin.getKgName());
        adapter = new Adapter_main_activity();
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
        adapter.setOnListener(this);
        LogUtils.d(adapter.getCount() + "---");
    }

    private List<NewTypeBean.RerurnValueBean> list;

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null) {
            switch (flag) {
                case Flag1:
                    //请求成功
                    typeBean = (NewTypeBean) cla;
                    if (typeBean != null) {
                        list = typeBean.getRerurnValue();
                        sp.saveToShared(Keyword.SP_NEW_TYPE_LIST, list);
                        handler.sendEmptyMessage(Flag1);
                    } else {
                        handler.sendEmptyMessage(Flag1_1);
                    }
                    break;
                case Flag2:
                    final WorkerExtensionBean bean = (WorkerExtensionBean) cla;
                    if (bean.getRerurnValue() != null) {
                        List<WorkerExtensionBean.RerurnValueBean> list = bean.getRerurnValue();
                        sp.saveToShared(Keyword.SP_WORKEREXTENSION, list);
                        list = null;
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(bean.getMessage());
                            }
                        });
                    }
                    break;
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
                case Flag1:
                    setAdapter();
                    break;
                case Flag1_1:
                    //请求无数据操作
                    LogUtils.d("没有数据");
                    break;
            }
        }
    };

    @Override
    public void Error() {
        LogUtils.d("Error");
    }

    @Override
    public void up_page() {

    }

    @Override
    public void down_page() {

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        LogUtils.d("点击了position---" + position);
//        String name = list.get(position).getCategoryName();
//        int CheckCategoryId = list.get(position).getCheckCategoryId();
//        int checkConclusionid = list.get(position).getCheckConclusionId();
//        String CheckObjectTable = list.get(position).getCheckObjectTable();
//        int CheckObjectCategory = list.get(position).getCheckObjectCategory();
//        Intent intent = new Intent(this, TypeList.class);
//        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME, name);
//        intent.putExtra(Keyword.KEY_CHECKCATEGORYID, CheckCategoryId);
//        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID, checkConclusionid);
//        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE, CheckObjectTable);
//        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY, CheckObjectCategory);
//        startActivity(intent);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        typeBean = null;
        IconNumber.Sujishu = 0;
//        typeListBean = null;
    }

    private List<NewTypeBean.RerurnValueBean.CheckCategoryListBean> listBeen;

    @Override
    public void setOnItemListener(int item, int position) {
        if (list == null) {
            list = (List<NewTypeBean.RerurnValueBean>) sp.queryForSharedToObject(Keyword.SP_NEW_TYPE_LIST);
        }
        listBeen = list.get(item).getCheckCategoryList();
        String name = listBeen.get(position).getCategoryName();
        int CheckCategoryId = listBeen.get(position).getCheckCategoryId();
        int checkConclusionid = listBeen.get(position).getCheckConclusionId();
        String CheckObjectTable = listBeen.get(position).getCheckObjectTable();
        int CheckObjectCategory = listBeen.get(position).getCheckObjectCategory();
        int ManualChooseObject = listBeen.get(position).getManualChooseObject();
        Intent intent = new Intent(this, TypeList.class);
        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME, name);
        intent.putExtra(Keyword.KEY_CHECKCATEGORYID, CheckCategoryId);
        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID, checkConclusionid);
        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE, CheckObjectTable);
        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY, CheckObjectCategory);
        intent.putExtra(Keyword.KEY_MANUALCHOOSEOBJECT,ManualChooseObject);
        startActivity(intent);
    }
}
