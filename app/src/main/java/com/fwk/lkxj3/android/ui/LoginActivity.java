/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fwk.lkxj3.android.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.LoginBean;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.constant.SpLogin;
import com.fwk.lkxj3.android.okhttp.HTTPURL;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.DialogUtils;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;
import com.fwk.lkxj3.common.util.ToastUtil;
import com.google.gson.Gson;
/**
 * 登陆页面
 */
public class LoginActivity extends BaseActvity {
    private static final int Flag = 1;
    private static final String HXPASS = "123456";
    private static String clientid;
    public static final int REQUEST_CODE_SETNICK = 1;
    private EditText usernameEditText;
    private EditText passwordEditText;

    private boolean progressShow;
    private boolean autoLogin = false;

    private String currentUsername;
    private String currentPassword;

    private String gxs = "";
    private int kgId;
    private String kgName;
    private int teacherId;
    private Gson gson;
    private SharedPreferencesUtils sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xjkp_activity_login_1);
        setTitleVisable(false);
        sp = new SharedPreferencesUtils();
        dialog = DialogUtils.waitForDialog(this,null);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        // 如果用户名改变，清空密码
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                passwordEditText.setText("");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initNewTypeListener() {

    }

    /**
     * 登录
     */
    public void login(View view){
        currentUsername = usernameEditText.getText().toString().trim();
        currentPassword = passwordEditText.getText().toString().trim();
        LogUtils.d("currentUsername:"+currentUsername+"---currentPassword:"+currentPassword);
        if (TextUtils.isEmpty(currentUsername)) {
            ToastUtil.show("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            ToastUtil.show("密码不能为空");
            return;
        }
        dialog.show();
        questedURL();
    }

    private void questedURL() {
        OKHttp okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
        String url = String.format(HTTPURL.API_LONGIN,currentUsername,currentPassword);
        okHttp.getAsynHttp(Flag,url, LoginBean.class);
    }

    @Override
    public <T> void OnSucceed(int flag, T cla, final String message) {
        if (cla != null){
            final LoginBean bean = (LoginBean) cla;
            if (bean.getRerurnValue() != null){
                LoginBean.RerurnValueBean valueBean = bean.getRerurnValue();
                SpLogin.save(valueBean.getKgId(),valueBean.getKgName(),valueBean.getName(),
                        valueBean.getUserId(),valueBean.getWorkerExtensionId());
                SpLogin.setAlreadyLogin(true);
                handler.sendEmptyMessage(Flag);
            }else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        ToastUtil.show(bean.getMessage());
                    }
                });
            }
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    ToastUtil.show(message);
                }
            });
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Flag:
                    dialog.dismiss();
                    SpLogin.setAlreadyLogin(true);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
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
