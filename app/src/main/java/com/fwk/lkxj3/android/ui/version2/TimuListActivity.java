package com.fwk.lkxj3.android.ui.version2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.CreatType;
import com.fwk.lkxj3.android.bean.JieguoType;
import com.fwk.lkxj3.android.bean.TijiaoBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.constant.SpLogin;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.ui.fragment.SetImage;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.NumberUtils;
import com.fwk.lkxj3.common.util.PhotographUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.TimeUtils;
import com.fwk.lkxj3.common.util.ToastUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fanwenke on 16/10/25.
 */

public class TimuListActivity extends BaseActvity implements AdapterView.OnItemClickListener {
    private static final int FLAG1 = 1;
    private static final int FLAG2 = 2;
    private static final int FLAG3 = 3;
    @InjectView(R.id.lv_version2)
    ListView listView;
    @InjectView(R.id.rl_set_title)
    RelativeLayout rlSetTitle;
    @InjectView(R.id.xjkp_tv_jiancharen)
    TextView xjkpTvJiancharen;
    @InjectView(R.id.rl_jiancharen)
    RelativeLayout rlJiancharen;
    @InjectView(R.id.xjkp_tv_beijiancharen)
    TextView xjkpTvBeijiancharen;
    @InjectView(R.id.rl_beijiancharen)
    RelativeLayout rlBeijiancharen;
    @InjectView(R.id.xjkp_tv_time)
    TextView xjkpTvTime;
    @InjectView(R.id.rl_time)
    RelativeLayout rlTime;
    @InjectView(R.id.xjkp_tv_zongfen2)
    TextView xjkpTvZongfen;
    @InjectView(R.id.rl_zongfen)
    RelativeLayout rlZongfen;
    @InjectView(R.id.rl_a)
    LinearLayout rlA;
    @InjectView(R.id.rl_xjkp_one_fragment_name)
    RelativeLayout rlXjkpOneFragmentName;
    @InjectView(R.id.xjkp_rl_title)
    RelativeLayout xjkpRlTitle;
    private TimeAdapter adapter = new TimeAdapter(TimuListActivity.this);
    ;
    //    private CreatType creatType;
    private List<CreatType.RerurnValueBean> getData;
    private JieguoType jieguoType;
    private List<TijiaoBean.xjkpCheckDetailList> CheckDetailListxjkp = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();
    private ArrayList<String> imagePathList;
    private int categoryId;
    private int checkconclusionId;

    private int DistrictId;
    private String Name;
    private int WorkId;

    private String aN;
    private String bN;
    private String cN;
    private String dN;
    private int TotalNumber = 0;
    private String CheckObjectTable;

    private SharedPreferencesUtils sp;

    public static String note = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version2_activity);
        ButterKnife.inject(this);
        init();
        setURL();
    }

    @Override
    public void initNewTypeListener() {
    }

    public void note(View view) {
        startActivity(new Intent(TimuListActivity.this, NoteActivity.class));
    }

    public void paizhao(View view) {
        startActivity(new Intent(TimuListActivity.this, PaizhaoActivity.class));
    }

    private void init() {
        sp = new SharedPreferencesUtils();
        rlSetTitle.setVisibility(View.GONE);
        rlZongfen.setVisibility(View.GONE);
        xjkpTvJiancharen.setText(sp.getString(Keyword.LOGIN_NAME));
        Intent intent = getIntent();
        categoryId = intent.getIntExtra(Keyword.KEY_CHECKCATEGORYID, 0);
        checkconclusionId = intent.getIntExtra(Keyword.KEY_CHECKCONCLUSIONID, 0);
        CheckObjectTable = intent.getStringExtra(Keyword.KEY_CHECKOBJECTTABLE);
        String titlename = intent.getStringExtra(Keyword.KEY_TYPE_LIST_NAME);
        DistrictId = intent.getIntExtra(Keyword.KEY_DISTRICTID, 0);
        Name = intent.getStringExtra(Keyword.KEY_NAME);
        WorkId = intent.getIntExtra(Keyword.KEY_SELCET_TEACHER_OBJ, 0);
        listView.setOnItemClickListener(this);
        ivnewtype.setImageDrawable(getResources().getDrawable(R.mipmap.paizhao));
        setTitleString(titlename);
        xjkpTvBeijiancharen.setText(Name);
        xjkpTvZongfen.setText("总分:100");
        String timedata = TimeUtils.getyyThh();
        String setTime = timedata.split("_")[1];
        xjkpTvTime.setText(setTime);
        setNewTypeVisable(false);
    }

    private void setURL() {
        OKHttp okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        String url1 = String.format(HTTPURL.API_CREAC_TYPE, sp.getInt(Keyword.LOGIN_KGID), categoryId);
        LogUtils.d("检查内容URL:" + url1);
        okHttp.getAsynHttp(FLAG1, url1, CreatType.class);
        if (checkconclusionId == 0) {
            handler.sendEmptyMessage(FLAG3);
            return;
        }
        String url2 = String.format(HTTPURL.API_JIEGUOOBJECT, checkconclusionId);
        LogUtils.d("检查结果类型URL:" + url2);
        okHttp.getAsynHttp(FLAG2, url2, JieguoType.class);
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null) {
            switch (flag) {
                case FLAG1:
                    CreatType creatType = (CreatType) cla;
                    List<CreatType.RerurnValueBean> getDatas = creatType.getRerurnValue();
                    getData = new ArrayList<>();
                    for (int i = 0; i < getDatas.size(); i++) {
                        if (getDatas.get(i).getParentId() != 0) {
                            CreatType.RerurnValueBean bean = new CreatType.RerurnValueBean();
                            bean = getDatas.get(i);
                            getData.add(bean);
                        }
                    }
                    TotalNumber = getData.size();
                    for (int i = 0; i < getData.size(); i++) {
                        TijiaoBean.xjkpCheckDetailList xjkpBean = new TijiaoBean.xjkpCheckDetailList();
                        xjkpBean.setDetailedContentId(0);//详细内容编号
                        xjkpBean.setCheckResultsId(0);//检查结果编号
                        xjkpBean.setItName(getData.get(i).getItName());//项目名称
                        xjkpBean.setItemContent(getData.get(i).getItemContent());//项目内容
                        xjkpBean.setMinScore(getData.get(i).getMinScore());
                        xjkpBean.setMaxScore(getData.get(i).getMaxScore());
                        xjkpBean.setIntCheckResult(0);
                        xjkpBean.setVcharCheckResult(null);
                        xjkpBean.setRemark(null);
                        CheckDetailListxjkp.add(xjkpBean);
                        map.put(i, 0);
                    }
                    sp.saveToShared(Keyword.SP_JIANCHA, getData);
                    handler.sendEmptyMessage(flag);
                    break;
                case FLAG2:
                    jieguoType = (JieguoType) cla;
                    for (int i = 0; i < jieguoType.getRerurnValue().size(); i++) {
                        JiguoList.add(jieguoType.getRerurnValue().get(i).getDataDictionaryName());
                        fenshu.add(jieguoType.getRerurnValue().get(i).getLevelCode());
                    }
                    handler.sendEmptyMessage(flag);
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
            super.handleMessage(msg);
            switch (msg.what) {
                case FLAG1:
                    adapter.setData(getData);
                    adapter.setMap(map);
                    listView.setAdapter(adapter);
                    break;
                case FLAG2:
                    sp.saveToShared(Keyword.SP_JIEGUO_TYPE, jieguoType);
                    switch (checkconclusionId) {
                        case 1:
                        case 2:
                            aN = jieguoType.getRerurnValue().get(0).getLevelCode();
                            break;
                        case 3:
                            aN = jieguoType.getRerurnValue().get(0).getLevelCode();
                            bN = jieguoType.getRerurnValue().get(1).getLevelCode();
                            break;
                        case 4:
                            aN = jieguoType.getRerurnValue().get(0).getLevelCode();
                            bN = jieguoType.getRerurnValue().get(1).getLevelCode();
                            cN = jieguoType.getRerurnValue().get(2).getLevelCode();
                            break;
                        case 5:
                        case 6:
                            aN = jieguoType.getRerurnValue().get(0).getLevelCode();
                            bN = jieguoType.getRerurnValue().get(1).getLevelCode();
                            cN = jieguoType.getRerurnValue().get(2).getLevelCode();
                            dN = jieguoType.getRerurnValue().get(3).getLevelCode();
                            break;
                    }
                    break;
                case FLAG3:
                    IntCheckResult = 0;
                    xjkpTvZongfen.setText("总分:" + IntCheckResult);
                    adapter.getIsSelectFen(true);
                    break;
            }
        }
    };


    @Override
    public void up_page() {

    }

    @Override
    public void down_page() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public void getMap(Map<Integer, Integer> map, int itme, int positon) {
        this.map = map;
        adapter.setMap(this.map);
        adapter.notifyDataSetChanged();
        setSelcetAdapter(itme, positon);
    }

    public void getFenMap(Map<Integer, Integer> map) {
        this.map = map;
        setSuanfen(map);
    }

    private void setSuanfen(Map<Integer, Integer> map) {
        int numbr = 0;
        for (int keys : map.keySet()) {
            numbr = numbr + map.get(keys);
        }
        IntCheckResult = numbr;
        xjkpTvZongfen.setText("总分:" + IntCheckResult);
    }

    /**
     * -----------------------------------------------------------------------------------------------
     * 以下代码为保存上传
     *
     * @param view
     */
    public void submit(View view) {

        String url = HTTPURL.API_TIJIAO;
        tijiao(url);
    }

    private HttpUtils httpUtils = new HttpUtils();
    private List<String> imurl = new ArrayList<>();

    private void tijiao(String url) {
        dialog.show();
        imurl = (List<String>) sp.queryForSharedToObject(Keyword.KEY_PHOTO_PATH);

        List<String> paths = imurl;
        if (paths != null && paths.size() != 0) {
            if (paths.get(0).equals("first")) {
                paths.remove(0);
            }
        } else {
            paths = new ArrayList<>();
        }
        TijiaoBean bean = new TijiaoBean();
        bean = getJson();
        Gson gson = new Gson();
        String beanString = gson.toJson(bean);
        LogUtils.d("TijiaoBean--" + beanString);
        RequestParams params = new RequestParams();
        params.addBodyParameter("kgid", 33 + "");
        params.addBodyParameter("jsonStr", beanString);
        for (int i = 0; i < paths.size(); i++) {

            String path = paths.get(i);

            if (new File(path).exists()) {
                PhotographUtils.compressPicture(path, path);  // 压缩图片
                params.addBodyParameter("photo" + i, new File(path));

                LogUtils.d("图片url:------" + path);
            }
        }

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                dialog.dismiss();
                LogUtils.d("json----+" + responseInfo.result);
                sp.setInt(Keyword.KEY_SELCET_OBJ, -1);
                ToastUtil.show("提交成功");
                sp.setboolean(Keyword.KEY_ISSHUAXIN, true);
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                dialog.dismiss();
                ToastUtil.show("提交失败");
            }
        });
    }

    //分数
    private int IntCheckResult = 100;

    public void setSelcetAdapter(int i, int position) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(i, position);
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int keys : map.keySet()) {
            int key = map.get(keys);
            if (key == 0) {
                a++;
            } else if (key == 1) {
                b++;
            } else if (key == 2) {
                c++;
            } else if (key == 3) {
                d++;
            }

        }
        IntCheckResult = getTotalNumber(a, b, c, d);
        xjkpTvZongfen.setText("总分:" + IntCheckResult);
    }

    /**
     * 获取评价总分
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    private int getTotalNumber(int a, int b, int c, int d) {

        switch (checkconclusionId) {
            case 0:
                break;
            case 1:
            case 2:
                int number1 = NumberUtils.Type12(a, aN, TotalNumber);
                return number1;
            case 3:
                int number2 = NumberUtils.Type3(a, aN, b, bN, TotalNumber);
                return number2;
            case 4:
                int number3 = NumberUtils.Type4(a, aN, b, bN, c, cN, TotalNumber);
                return number3;
            case 5:
            case 6:
                int number4 = NumberUtils.Type56(a, aN, b, bN, c, cN, d, dN, TotalNumber);
                return number4;
        }
        return 0;
    }

    private TijiaoBean getJson() {
        String time = TimeUtils.getCurTimeString();
        TijiaoBean bean = new TijiaoBean();
        bean.setCheckCategoryId(categoryId);//检查类型编号
        bean.setCheckResultsId(0);//检查结果编号
        bean.setCheckDate(time);//检查时间
        bean.setIntCheckResult(IntCheckResult);//数字检查结论
        bean.setVcharCheckResult(null);//文本检查结论
        if (!note.equals("")) {
            bean.setRemark(note);//备注
        } else {
            bean.setRemark(null);//备注
        }
        bean.setModifier(SpLogin.getUserId());//更新人
        bean.setModifyTime(time);
        bean.setState(1);
        bean.setOrganizationId(SpLogin.getKgId());
        TijiaoBean.xjkpCheckPerson xjkpCheckPerson = new TijiaoBean.xjkpCheckPerson();//检查人
        xjkpCheckPerson.setInstanceCheckPersonId(0);//实际检查人编号
        xjkpCheckPerson.setCheckResultsId(0);//检查结果编号
        xjkpCheckPerson.setWorkerExtensionId(SpLogin.getWorkerxtensionId());//职工信息编号
        TijiaoBean.xjkpCheckResponsiblePerson xjkpCheckResponsiblePerson = new TijiaoBean.xjkpCheckResponsiblePerson();//被检查人实体
        xjkpCheckResponsiblePerson.setInstanceResponsiblePersonId(0);//实际负责人编号
        xjkpCheckResponsiblePerson.setCheckResultsId(0);//检查结果编号
        xjkpCheckResponsiblePerson.setWorkerExtensionId(0);//职工信息编号
        TijiaoBean.xjkpCheckObject xjkpCheckObject = new TijiaoBean.xjkpCheckObject();//检查对象
        xjkpCheckObject.setCheckResultsId(0);//检查结果编号
        xjkpCheckObject.setCheckObject(DistrictId);//检查对象ID
        xjkpCheckObject.setCheckObjectTable(CheckObjectTable);//检查对象表
        xjkpCheckObject.setInstanceCheckObjectId(0);//检查对象编号
        xjkpCheckObject.setName(Name);//对象名称
        bean.setXjkpCheckPerson(xjkpCheckPerson);
        bean.setXjkpCheckResponsiblePerson(xjkpCheckResponsiblePerson);
        bean.setXjkpCheckObject(xjkpCheckObject);
        setXjkpCheckDetailList();
        bean.setXjkpCheckDetailLists(CheckDetailListxjkp);

        return bean;
    }

    private List<String> JiguoList = new ArrayList<>();
    private List<String> fenshu = new ArrayList<>();

    private void setXjkpCheckDetailList() {
        try {
            for (int keys : map.keySet()) {
                CheckDetailListxjkp.get(keys).setVcharCheckResult(JiguoList.get(map.get(keys)));
                CheckDetailListxjkp.get(keys).setIntCheckResult(Integer.valueOf(fenshu.get(map.get(keys))));
            }
        } catch (Exception e) {
            for (int keys : map.keySet()) {
                CheckDetailListxjkp.get(keys).setVcharCheckResult((map.get(keys)) + "");
                CheckDetailListxjkp.get(keys).setIntCheckResult((map.get(keys)));
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        imagePathList = new ArrayList<>();
        sp.saveToShared(Keyword.KEY_PHOTO_PATH, imagePathList);
        note = "";
    }
}
