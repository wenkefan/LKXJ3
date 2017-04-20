package com.fwk.lkxj3.android.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.TypeListBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.ui.adapter.TypeListAdapter;
import com.fwk.lkxj3.android.ui.version2.SelectActivity;
import com.fwk.lkxj3.android.ui.version2.TimuListActivity;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.ToastUtil;
import com.fwk.lkxj3.smallchart.chart.CombineChart;
import com.fwk.lkxj3.smallchart.data.LineData;
import com.fwk.lkxj3.smallchart.interfaces.iData.IBarLineCurveData;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fanwenke on 16/10/11.
 */

public class TypeList extends BaseActvity implements AdapterView.OnItemClickListener {
    private static final int Flag = 1;
    @InjectView(R.id.xjkp_lv_type_list)
    ListView listView;
    @InjectView(R.id.xjkp_cc_combineChart)
    CombineChart combineChart;
    @InjectView(R.id.xjkp_ivb_new_type)
    ImageButton newType;
    @InjectView(R.id.xjkp_iv_left_time)
    ImageView LeftTime;
    @InjectView(R.id.xjkp_tv_select_time)
    TextView SelectTime;
    @InjectView(R.id.xjkp_iv_right_time)
    ImageView RightTime;
    @InjectView(R.id.xjkp_ll_zanwushuju)
    RelativeLayout Zanwushuju;
    private TypeListAdapter adapter;
    private LineData mLineData = new LineData();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    private ArrayList<IBarLineCurveData> mDataList = new ArrayList<>();
    //检查类型；
    private int checkcategoryid;
    //检查结果类型
    private int checkconclusionid;
    private String TimeString;//时间
    private TypeListBean typeListBean;
    private List<TypeListBean.RerurnValueBean> list;
    private String titleName;
    private String CheckObjectTable;
    private int CheckObjectCategory;
    private int CheckResultsId;
    private int ManualChooseObject;
    //当前展示的第几天
    private int Day = 1;
    private int ZongDay;
    private SharedPreferencesUtils sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typelist_activity);
        ButterKnife.inject(this);
        setTitleVisable(true);
        setNewTypeVisable(false);
        init();//初始化数据
        initView();
        setURL();
    }

    @Override
    public void initNewTypeListener() {

    }

    private void setURL() {
        OKHttp ok = OKHttp.getInstance();
        ok.setListener(this);
        String url = String.format(HTTPURL.API_LIST, checkcategoryid, sp.getInt(Keyword.LOGIN_KGID));
        LogUtils.d("类型集合：" + url);
        ok.getAsynHttp(Flag, url, TypeListBean.class);
    }

    @Override
    public void onResume() {
        if (sp.getBoolean(Keyword.KEY_ISSHUAXIN)){
            setURL();
            sp.setboolean(Keyword.KEY_ISSHUAXIN,false);
        }
        super.onResume();
    }

    private void initView() {
        adapter = new TypeListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void init() {
        sp = new SharedPreferencesUtils();
        Intent intent = getIntent();
        titleName = intent.getStringExtra(Keyword.KEY_TYPE_LIST_NAME);
        checkcategoryid = intent.getIntExtra(Keyword.KEY_CHECKCATEGORYID, 0);
        checkconclusionid = intent.getIntExtra(Keyword.KEY_CHECKCONCLUSIONID, 0);
        CheckObjectTable = intent.getStringExtra(Keyword.KEY_CHECKOBJECTTABLE);
        CheckObjectCategory = intent.getIntExtra(Keyword.KEY_CHECKOBJECTCATEGORY,0);
        ManualChooseObject = intent.getIntExtra(Keyword.KEY_MANUALCHOOSEOBJECT,0);
        LogUtils.d("ManualChooseObject--" + ManualChooseObject);
        setTitleString(titleName);
//        setCombineChart();
    }

    private void setCombineChart() {
        float[] f = {4, 2, 5, 5, 4};
        for (int i = 0; i < 5; i++) {
            mPointArrayList.add(new PointF(i + 1, f[i]));
        }
        mLineData.setValue(mPointArrayList);
        mLineData.setColor(Color.CYAN);
        mLineData.setPaintWidth(3f);
        mLineData.setTextSize(20f);
        mLineData.setName("xujian");
        mLineData.setIsTextSize(true);
        mDataList.add(mLineData);
        combineChart.setDataList(mDataList);
        combineChart.isAnimated = false;
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null) {
            switch (flag) {
                case Flag:
                     typeListBean = (TypeListBean) cla;
                    if (typeListBean.getRerurnValue() != null) {
                        list = new ArrayList<>();
                        list = typeListBean.getRerurnValue();
                        setTimeList();
                        handler.sendEmptyMessage(Flag);
                    } else {
                        final String mes = typeListBean.getMessage();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(mes);
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
                    if (list.size() == 0){
                        Intent intent = new Intent(TypeList.this, SelectActivity.class);
                        intent.putExtra(Keyword.KEY_CHECKCATEGORYID, checkcategoryid);
                        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID, checkconclusionid);
                        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME, titleName);
                        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE,CheckObjectTable);
                        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY,CheckObjectCategory);
                        intent.putExtra(Keyword.KEY_MANUALCHOOSEOBJECT,ManualChooseObject);
                        startActivity(intent);
                    }
                    initLeftRight();
                    adapter.setList(getTypeList(1));
                    adapter.notifyDataSetChanged();
                    setTitleTime(TimeString);
                    break;
            }

        }
    };

    /**
     * 初始化左右加载控件
     */
    private void initLeftRight() {
        if (ZongDay == 0 || ZongDay == 1) {
            LeftTime.setVisibility(View.INVISIBLE);
            RightTime.setVisibility(View.INVISIBLE);
        }
        if (ZongDay >= 2) {
            LeftTime.setVisibility(View.VISIBLE);
            RightTime.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * //给时间进行setItme区分
     */
    private void setTimeList() {
        int number = 0;
        ZongDay = 1;
        if (list.size() == 1){
            list.get(0).setItme(ZongDay);
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).setItme(ZongDay);
            if (getTime(number).equals(getTime(i + 1))) {
                list.get(i + 1).setItme(ZongDay);
            } else {
                number = i + 1;
                ZongDay++;
                if (i == list.size() - 2) {
                    list.get(i + 1).setItme(ZongDay);
                }
            }
        }
    }

    /**
     * 按照天数返回当天的记录
     *
     * @param itme 第几天
     * @return
     */
    private List<TypeListBean.RerurnValueBean> getTypeList(int itme) {
        List<TypeListBean.RerurnValueBean> adapterlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getItme() == itme) {
                TimeString = list.get(i).getCheckDate().split("T")[0];
                adapterlist.add(list.get(i));
            }
        }
        return adapterlist;
    }

    private void setTitleTime(String time) {
        SelectTime.setText(time);
    }

    private String getTime(int itme) {
        return (list.get(itme).getCheckDate()).split("T")[0];
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ShowTypeListActivity.class);
        intent.putExtra(Keyword.KEY_CHECKRESULTSID, typeListBean.getRerurnValue().get(position).getCheckResultsId());
        startActivity(intent);
    }

    @OnClick(R.id.xjkp_ivb_new_type)
    public void onClick() {
        Intent intent = new Intent(this, SelectActivity.class);
        intent.putExtra(Keyword.KEY_CHECKCATEGORYID, checkcategoryid);
        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID, checkconclusionid);
        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME, titleName);
        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE,CheckObjectTable);
        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY,CheckObjectCategory);
        intent.putExtra(Keyword.KEY_MANUALCHOOSEOBJECT,ManualChooseObject);
        startActivity(intent);
    }

    @OnClick({R.id.xjkp_iv_left_time, R.id.xjkp_iv_right_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xjkp_iv_left_time:
                up_page();
                break;
            case R.id.xjkp_iv_right_time:
                down_page();
                break;
        }
    }

    @Override
    public void up_page() {
        //日期向前  Day ＋ 1
        if (ZongDay >= 7) {
            if (Day < 6) {
                RightTime.setVisibility(View.VISIBLE);
                LeftTime.setVisibility(View.VISIBLE);
            } else if (Day == 6) {
                RightTime.setVisibility(View.VISIBLE);
                LeftTime.setVisibility(View.INVISIBLE);
            }
        } else {
            if (Day < ZongDay - 1) {
                RightTime.setVisibility(View.VISIBLE);
            } else if (Day == ZongDay - 1) {
                RightTime.setVisibility(View.VISIBLE);
                LeftTime.setVisibility(View.INVISIBLE);
            }
        }
        Day++;
        adapter.setList(getTypeList(Day));
        adapter.notifyDataSetChanged();
        setTitleTime(TimeString);
    }

    @Override
    public void down_page() {
        if (Day > 2){
            RightTime.setVisibility(View.VISIBLE);
            LeftTime.setVisibility(View.VISIBLE);
        }else if (Day == 2){
            RightTime.setVisibility(View.INVISIBLE);
            LeftTime.setVisibility(View.VISIBLE);
        }
        Day--;
        adapter.setList(getTypeList(Day));
        adapter.notifyDataSetChanged();
        setTitleTime(TimeString);
    }


    /**
     * 当adapter无数据时，显示暂无数据页面
     *
     * @param size
     */
    public void setAdapterCount(int size) {
        if (size == 0) {
            listView.setVisibility(View.GONE);
            Zanwushuju.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            Zanwushuju.setVisibility(View.GONE);
        }
    }
}
