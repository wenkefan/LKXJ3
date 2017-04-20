package com.fwk.lkxj3.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.ShowTypeListBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.ui.adapter.ShowTypeListAdapter;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.ToastUtil;
import com.fwk.lkxj3.photo.ImageProvider;
import com.fwk.lkxj3.photo.PhotoNo;
import com.fwk.lkxj3.photo.PhotoPagerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fanwenke on 16/10/17.
 */

public class ShowTypeListActivity extends BaseActvity {
    private static final int Flag = 1;
    @InjectView(R.id.xjkp_lv_show_typelist)
    ListView listView;
    @InjectView(R.id.xjkp_tv_jiancharen)
    TextView JC;
    @InjectView(R.id.xjkp_tv_beijiancharen)
    TextView BJC;
    @InjectView(R.id.xjkp_tv_time)
    TextView TIME;
    @InjectView(R.id.xjkp_tv_zongfen)
    TextView ZF;
    @InjectView(R.id.bzc_context)
    TextView BZ;
    @InjectView(R.id.ll_note)
    LinearLayout BZX;
    @InjectView(R.id.tv_chakanzhaopian)
    TextView PhotoSee;
    @InjectView(R.id.bzc_context)
    TextView bzc_context;
    private int checkresultsid;
    private OKHttp okHttp;
    private ShowTypeListBean.RerurnValueBean mBean;
    private ShowTypeListAdapter adapter;
    private SharedPreferencesUtils sp;

    private List<ShowTypeListBean.RerurnValueBean.XjkpCheckPhotosListModeBean> pho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showtypelist_activity);
        ButterKnife.inject(this);
        init();
        getURL();

    }

    @Override
    public void initNewTypeListener() {
        ivnewtype.setImageResource(R.drawable.select_photo);
        ivnewtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pho == null || pho.size() == 0) {
                    startActivity(new Intent(ShowTypeListActivity.this, PhotoNo.class));
                } else {

                    new PhotoPagerConfig.Builder(ShowTypeListActivity.this)
                            .setBigImageUrls(getImageUrls())
                            .setSavaImage(true)
                            .build();
                }
            }
        });
    }

    public ArrayList<String> getImageUrls() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < pho.size(); i++) {
            String url = HTTPURL.IMGURL + pho.get(i).getPhotoFile();
            list.add(url);
        }

        return list;
    }

    private void getURL() {
        okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        String url = String.format(HTTPURL.API_XIANGXILIST, checkresultsid);
        LogUtils.d("检查详细内容URL：" + url);
        okHttp.getAsynHttp(Flag, url, ShowTypeListBean.class);
    }

    private void init() {
        setTitleString("查看详情");
        setNewTypeVisable(true);
        sp = new SharedPreferencesUtils();
        Intent intent = getIntent();
        checkresultsid = intent.getIntExtra(Keyword.KEY_CHECKRESULTSID, 0);

    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null) {
            switch (flag) {
                case Flag:
                    final ShowTypeListBean bean = (ShowTypeListBean) cla;
                    if (bean.getRerurnValue() != null) {
                        mBean = bean.getRerurnValue();
                        pho = mBean.getXjkpCheckPhotosListMode();
                        sp.saveToShared("photo", pho);
                        handler.sendEmptyMessage(Flag);
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

    @Override
    public void Error() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Flag:
                    setShowAdapter();
                    initView();
                    break;
            }
        }
    };

    private void initView() {
        JC.setText("检查人: " + sp.getString(Keyword.LOGIN_NAME));
        BJC.setText("检查对象: " + mBean.getName());
//        mBean.getCheckDate().split("T")[0] + "\n" +
        TIME.setText(mBean.getCheckDate().split("T")[1]);
        ZF.setText("得分: " + mBean.getIntCheckResult() + "");
        if (mBean.getRemark() != null) {
            if (!mBean.getRemark().toString().equals("")) {
                BZ.setText(mBean.getRemark() + "");
                BZX.setVisibility(View.VISIBLE);
            } else {
                BZX.setVisibility(View.GONE);
            }
        } else {
            BZX.setVisibility(View.GONE);
        }

//        if (pho.size() == 0){
//            PhotoSee.setVisibility(View.INVISIBLE);
//        }else {
//            PhotoSee.setVisibility(View.GONE);
//        }
    }

    private void setShowAdapter() {
        adapter = new ShowTypeListAdapter();
        adapter.getList(mBean);
        listView.setAdapter(adapter);
    }

    @Override
    public void up_page() {

    }

    @Override
    public void down_page() {

    }

    @OnClick(R.id.tv_chakanzhaopian)
    public void onClick() {
        startActivity(new Intent(this, LookPhotoActivity.class));
    }

}
