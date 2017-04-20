package com.fwk.lkxj3.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.CreatType;
import com.fwk.lkxj3.android.bean.JieguoType;
import com.fwk.lkxj3.android.bean.TijiaoBean;
import com.fwk.lkxj3.android.bean.TijiaoBean.xjkpCheckDetailList;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.ui.fragment.OneFragment;
import com.fwk.lkxj3.android.ui.fragment.SelectJCObj;
import com.fwk.lkxj3.android.ui.fragment.SetImage;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.activity.NFCActivity;
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
import butterknife.OnClick;

/**
 * Created by fanwenke on 16/9/30.
 */

public class NewTypeActivity extends NFCActivity {
    private static final int API_CREAC_TYPE = 1;
    private static final int API_JIEGUO_OBJECT = 2;
    private static final int INTENT_CAMERA = 1;
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    public static String JC = LOGIN_NAME;
    public static String BJC;
    public static String TIME;
    public static int ZF;
    public static String TITLENAME;
    @InjectView(R.id.rl_xjkp_left)
    RelativeLayout rlXjkpLeft;
    @InjectView(R.id.rl_xjkp_right)
    RelativeLayout rlXjkpRight;
    @InjectView(R.id.xjkp_tv_page_tishi)
    TextView xjkpTvPageTishi;
    @InjectView(R.id.rl_xjkp_baocun)
    RelativeLayout rlXjkpBaocun;
    @InjectView(R.id.xjkp_ll_dibudanghang)
    LinearLayout Dibudanghang;

    private SharedPreferencesUtils sp;
    private Fragment[] fragments;
    private CreatType typeList;
    private int total_item;//总检查条目;
    private int total_number_pager;//总页数
    private int current_number_pager = 1;//当前页数
//    private int URL_Key;//请求详细检查内容的key
    private int checkcategoryid;//检查类别编号
    private int CheckConclusionId;//检查结果类型
    public static Map<Integer, Integer> map;
    private JieguoType type;
    private List<xjkpCheckDetailList> CheckDetailListxjkp = new ArrayList<>();


    private String CheckObjectTable;
    private int CheckObjectCategory;
    private static String aN;
    private static String bN;
    private static String cN;
    private static String dN;
    private static int TotalNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xjkp_new_type_activity);
        ButterKnife.inject(this);
        setTitleVisable(false);
        setNewTypeVisable(false);
        init();
        getURL();
    }

    @Override
    public void initNewTypeListener() {

    }

    private void init() {
        setOkHttp();
        sp = new SharedPreferencesUtils();
        Intent intent = getIntent();
//        URL_Key = intent.getIntExtra(Keyword.KEY_CHECKCATEGORYID, 0);
        CheckConclusionId = intent.getIntExtra(Keyword.KEY_CHECKCONCLUSIONID, 0);
        checkid = CheckConclusionId;
        checkcategoryid = intent.getIntExtra(Keyword.KEY_CHECKCATEGORYID, 0);
        TITLENAME = intent.getStringExtra(Keyword.KEY_TYPE_LIST_NAME);
        CheckObjectTable = intent.getStringExtra(Keyword.KEY_CHECKOBJECTTABLE);
        CheckObjectCategory = intent.getIntExtra(Keyword.KEY_CHECKOBJECTCATEGORY, 0);
    }

    //获取检查详细内容
    private void getURL() {
        String url = String.format(HTTPURL.API_CREAC_TYPE, sp.getInt(Keyword.LOGIN_KGID), checkcategoryid);
        LogUtils.d("加载内容URL：" + url);
        getOkhttp(API_CREAC_TYPE, url, CreatType.class);
        String url1 = String.format(HTTPURL.API_JIEGUOOBJECT, CheckConclusionId);
        LogUtils.d("检查结果类型url:" + url1);
        getOkhttp(API_JIEGUO_OBJECT, url1, JieguoType.class);
    }

    private void initFragment() {
        typeList = (CreatType) sp.queryForSharedToObject(Keyword.SP_XIANGXI_NEIRONG);
        total_item = typeList.getRerurnValue().size();
        total_number_pager = getTotal_number_pager(total_item, Keyword.FRAGMENT_ITME) + 2;
        fragments = new Fragment[total_number_pager];
        fragments[0] = SelectJCObj.newInstance(this);
        Bundle bundle1 = new Bundle();
        bundle1.putString(Keyword.KEY_CHECKOBJECTTABLE, CheckObjectTable);
        bundle1.putInt(Keyword.KEY_CHECKOBJECTCATEGORY, CheckObjectCategory);
        bundle1.putInt(Keyword.KEY_CHECKCATEGORYID,checkcategoryid);
        fragments[0].setArguments(bundle1);
        fragments[1] = SetImage.newInstance(this);
        for (int i = 2; i < total_number_pager; i++) {
            OneFragment fragment = new OneFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Keyword.KEY_FRAGMENT_TOTAL_NUMBER, total_number_pager);//总页数
            bundle.putInt(Keyword.KEY_CURRENT_NUMBER, i);//第几页
            bundle.putInt(Keyword.KEY_YUSHU, getIsYuShu(total_item));//是否填充满整个页数,求余数(0代表正好填充满)
            bundle.putSerializable(Keyword.SP_XIANGXI_NEIRONG, typeList);
            fragment.setArguments(bundle);
            fragments[i] = fragment;
        }
        setFragment();
        getPageNumber();

    }

    private int getIsYuShu(int item) {
        if (item < Keyword.FRAGMENT_ITME){
            return 1;
        }
        int yushu = item % Keyword.FRAGMENT_ITME;
        return yushu;
    }

    private void setFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.xjkp_fl_creat_type_fragment, fragments[0]).commit();
        if (total_number_pager == 1) {
            rlXjkpLeft.setVisibility(View.INVISIBLE);
            rlXjkpRight.setVisibility(View.INVISIBLE);
        }
    }

    private int getTotal_number_pager(int a, int b) {
        if (a == 0 || a <= b) {
            return 1;
        } else if (a > b) {
            if (a % b == 0) {
                return a / b;
            }
            return a / b + 1;
        }
        return 0;
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null) {
            switch (flag) {
                case API_CREAC_TYPE:
                    CreatType creatType;
                    creatType = (CreatType) cla;
                    if (creatType.getRerurnValue() != null) {
                        for (int i = 0; i < creatType.getRerurnValue().size(); i++) {
                            if (creatType.getRerurnValue().get(i).getParentId() == 0) {
                                creatType.getRerurnValue().remove(i);
                            }
                        }
                        sp.saveToShared(Keyword.SP_XIANGXI_NEIRONG, creatType);
                        List<CreatType.RerurnValueBean> getData = creatType.getRerurnValue();
                        for (int i = 0; i < creatType.getRerurnValue().size(); i++) {
                            xjkpCheckDetailList xjkpBean = new xjkpCheckDetailList();
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
                        }
                        TotalNumber = creatType.getRerurnValue().size();
                        creatType = null;
                        handler.sendEmptyMessage(API_CREAC_TYPE);
                    } else {
                        final CreatType finalCreatType = creatType;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(finalCreatType.getMessage());
                            }
                        });
                    }
                    break;
                case API_JIEGUO_OBJECT:
                    type = (JieguoType) cla;
                    sp.saveToShared(Keyword.SP_JIEGUO_TYPE, type);
                    for (int i = 0; i < type.getRerurnValue().size(); i++) {
                        JiguoList.add(type.getRerurnValue().get(i).getDataDictionaryName());
                        fenshu.add(type.getRerurnValue().get(i).getLevelCode());
                    }
                    handler.sendEmptyMessage(API_JIEGUO_OBJECT);
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
                case API_CREAC_TYPE:
                    initFragment();

                    break;
                case API_JIEGUO_OBJECT:

                    switch (CheckConclusionId) {
                        case 1:
                        case 2:
                            aN = type.getRerurnValue().get(0).getLevelCode();
                            break;
                        case 3:
                            aN = type.getRerurnValue().get(0).getLevelCode();
                            bN = type.getRerurnValue().get(1).getLevelCode();
                            break;
                        case 4:
                            aN = type.getRerurnValue().get(0).getLevelCode();
                            bN = type.getRerurnValue().get(1).getLevelCode();
                            cN = type.getRerurnValue().get(2).getLevelCode();
                            break;
                        case 5:
                        case 6:
                            aN = type.getRerurnValue().get(0).getLevelCode();
                            bN = type.getRerurnValue().get(1).getLevelCode();
                            cN = type.getRerurnValue().get(2).getLevelCode();
                            dN = type.getRerurnValue().get(3).getLevelCode();
                            break;
                    }
                    break;
            }
        }
    };

    @Override
    public void Error() {

    }

    @Override
    public void up_page() {
        //上一页
        RetrunNumber(UP);
        setFragmentNumber();
    }

    @Override
    public void down_page() {
        //下一页
        RetrunNumber(DOWN);
        setFragmentNumber();
    }

    private void setFragmentNumber() {
        int currentnumber = current_number_pager - 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.xjkp_fl_creat_type_fragment, fragments[currentnumber]).commit();
    }

    public void RetrunNumber(String str) {
        if (str.equals(UP)) {
            if (current_number_pager == 3) {
                rlXjkpLeft.setVisibility(View.INVISIBLE);
                rlXjkpRight.setVisibility(View.VISIBLE);
                current_number_pager = 2;
                getPageNumber();
                Dibudanghang.setVisibility(View.VISIBLE);
                return;
            }
            rlXjkpRight.setVisibility(View.VISIBLE);
            rlXjkpBaocun.setVisibility(View.GONE);
            current_number_pager--;
            getPageNumber();
            Dibudanghang.setVisibility(View.VISIBLE);
        } else if (str.equals(DOWN)) {
            if (current_number_pager == total_number_pager - 1) {
                current_number_pager = total_number_pager;
                rlXjkpRight.setVisibility(View.GONE);
                rlXjkpLeft.setVisibility(View.VISIBLE);
                rlXjkpBaocun.setVisibility(View.VISIBLE);
                getPageNumber();
                Dibudanghang.setVisibility(View.VISIBLE);
                return;
            }
            rlXjkpLeft.setVisibility(View.VISIBLE);
            current_number_pager++;
            if (current_number_pager == 2){
                rlXjkpLeft.setVisibility(View.INVISIBLE);
            }
            getPageNumber();
            Dibudanghang.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.rl_xjkp_left, R.id.rl_xjkp_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_xjkp_left:
                up_page();
                break;
            case R.id.rl_xjkp_right:
                down_page();
                break;
        }
    }

    private void getPageNumber() {
        xjkpTvPageTishi.setText((current_number_pager - 1) + "/" + (total_number_pager - 1));
    }

    public static int setSelcetAdapter(int i, int position) {
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
        return IntCheckResult;
    }

    private List<String> JiguoList = new ArrayList<>();
    private List<String> fenshu = new ArrayList<>();
    private void setXjkpCheckDetailList() {
        for (int keys : map.keySet()) {
            CheckDetailListxjkp.get(keys - 5).setVcharCheckResult(JiguoList.get(map.get(keys)));
            CheckDetailListxjkp.get(keys - 5).setIntCheckResult(Integer.valueOf(fenshu.get(map.get(keys))));
        }
    }

    private static int checkid = 0;

    /**
     * 获取评价总分
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    private static int getTotalNumber(int a, int b, int c, int d) {

        switch (checkid) {
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


    public static Map<Integer, Integer> getSelectAdapter() {
        if (map == null) {
            return map = new HashMap<>();
        }
        return map;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map = null;
        sp.setInt(Keyword.KEY_SELCET_OBJ, -1);
        BJC = "";
        TITLENAME = "";
        ZF = 0;
        IntCheckResult = 0;
        sp.saveToShared(Keyword.KEY_PHOTO_PATH, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == INTENT_CAMERA) { // 拍照
            ((SetImage) fragments[1]).setImagemt();
        }
    }

    public void objDown() {
        down_page();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String cardId = readICCardNo(intent);
        ((SelectJCObj) fragments[0]).NFCIdCard(cardId);
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
        imurl = ((SetImage) fragments[1]).ImageUrlList();

        List<String> paths = imurl;
        if (paths.get(0).equals("first")) {
            paths.remove(0);
        }

        TijiaoBean bean = new TijiaoBean();
        bean = getJson();
        Gson gson = new Gson();
        String beanString = gson.toJson(bean);
        RequestParams params = new RequestParams();
        params.addBodyParameter("kgid", 33 + "");
        params.addBodyParameter("jsonStr", beanString);
        for (int i = 0; i < paths.size(); i++) {

            String path = paths.get(i);

            if (new File(path).exists()) {
                PhotographUtils.compressPicture(path, path);  // 压缩图片
                params.addBodyParameter("photo" + i, new File(path));

                LogUtils.d("图片url:------"+path);
            }
        }

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                dialog.dismiss();
                LogUtils.d("json----+" + responseInfo.result);
                ToastUtil.show("提交成功");
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                dialog.dismiss();
                ToastUtil.show("提交失败");
            }
        });
    }

    private int DistrictId;
    private String Name;

    public void getSelectjCobjFramgent(int DistrictId, String Name) {
        this.DistrictId = DistrictId;
        this.Name = Name;
    }

    //分数
    private static int IntCheckResult;

    private TijiaoBean getJson() {
        String time = TimeUtils.getCurTimeString();
        TijiaoBean bean = new TijiaoBean();
        bean.setCheckCategoryId(checkcategoryid);//检查类型编号
        bean.setCheckResultsId(0);//检查结果编号
        bean.setCheckDate(time);//检查时间
        bean.setIntCheckResult(IntCheckResult);//数字检查结论
        bean.setVcharCheckResult(null);//文本检查结论
        bean.setRemark(null);//备注
        bean.setModifier(sp.getInt(Keyword.LOGIN_WORKEREXTENSIONID));//更新人
        bean.setModifyTime(time);
        bean.setState(1);
        TijiaoBean.xjkpCheckPerson xjkpCheckPerson = new TijiaoBean.xjkpCheckPerson();//检查人
        xjkpCheckPerson.setInstanceCheckPersonId(0);//实际检查人编号
        xjkpCheckPerson.setCheckResultsId(0);//检查结果编号
        xjkpCheckPerson.setWorkerExtensionId(sp.getInt(Keyword.LOGIN_WORKEREXTENSIONID));//职工信息编号
        TijiaoBean.xjkpCheckResponsiblePerson xjkpCheckResponsiblePerson = new TijiaoBean.xjkpCheckResponsiblePerson();//被检查人实体
        xjkpCheckResponsiblePerson.setInstanceResponsiblePersonId(0);//实际负责人编号
        xjkpCheckResponsiblePerson.setCheckResultsId(0);//检查结果编号
        xjkpCheckResponsiblePerson.setWorkerExtensionId(31);//职工信息编号
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
}
