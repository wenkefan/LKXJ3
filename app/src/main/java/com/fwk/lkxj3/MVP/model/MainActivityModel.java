package com.fwk.lkxj3.MVP.model;

import android.os.Handler;
import android.os.Message;

import com.fwk.lkxj3.MVP.KeyValue;
import com.fwk.lkxj3.MVP.listener.SuccessListener;
import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.android.bean.NewTypeBean;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.ui.listener.OnSucceedListener;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by fanwenke on 16/11/10.
 */

public class MainActivityModel implements OnSucceedListener{

    private SharedPreferencesUtils sp;

    private OKHttp okHttp;

    SuccessListener successListener;

    public MainActivityModel(){
        sp = new SharedPreferencesUtils();
        setURL();
    }

    public void setSuccessListener(SuccessListener successListener){
        this.successListener = successListener;
    }

    /**
     * 请求网络
     */
    private void setURL(){
        okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
    }

    /**
     * 获取集合列表
     * @param flag
     * @param url
     */
    public void mainurl(int flag,String url){
        okHttp.getAsynHttp(flag,url,NewTypeBean.class);
    }


    @Override
    public <T> void OnSucceed(int flag, T cla, String message) {
        if (cla != null){
            switch (flag){
                case 100:
                    NewTypeBean bean = (NewTypeBean) cla;
                    sp.saveToShared(KeyValue.SP_TYPE,bean);
                    handler.sendEmptyMessage(KeyValue.SP_TYPE_FLAG);
                    break;
            }
        } else {

        }
    }

    @Override
    public void Error() {

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case KeyValue.SP_TYPE_FLAG:
                    getList();
                    successListener.URLSuccess(KeyValue.SP_TYPE_FLAG);
                    break;
            }
        }
    };

    private void getList() {
        NewTypeBean bean = (NewTypeBean) sp.queryForSharedToObject(KeyValue.SP_TYPE);
        List<NewTypeBean.RerurnValueBean> listBeen = bean.getRerurnValue();
        sp.saveToShared(KeyValue.SP_TYPE_LIST,listBeen);
    }

}
