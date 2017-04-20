package com.fwk.lkxj3.android.okhttp;


import com.fwk.lkxj3.android.ui.listener.OnSucceedListener;
import com.fwk.lkxj3.common.util.LogUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/20.
 */
public class OKHttp {
    OnSucceedListener listener;
    private static final OkHttpClient client = new OkHttpClient();
    private static final OKHttp OK_HTTP = new OKHttp();

    public static OKHttp getInstance() {
        return OK_HTTP;
    }

    public void setListener(OnSucceedListener listener) {
        this.listener = listener;
    }

    public <T> void getAsynHttp(final int flag, String URL, final Class<T> clas) {
        Request request = new Request.Builder().url(URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.Error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                JSONObject jsonObject = null;
                try {
                    LogUtils.d("---" + json + "");
                    jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("Success") == 10000) {
                        Gson gson = new Gson();
                        listener.OnSucceed(flag, gson.fromJson(json, clas), null);
                    } else {
                        String message = (String) jsonObject.get("Message");
                        listener.OnSucceed(flag, null, message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public <T> void postAsynHttp(final int flag,String url,final Class<T> clas){
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.Error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("Success") == 10000) {
                        LogUtils.d("---" + json + "");
                        Gson gson = new Gson();
                        listener.OnSucceed(flag, gson.fromJson(json, clas), null);
                    } else {
                        String message = (String) jsonObject.get("Message");
                        listener.OnSucceed(flag, null, message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }


}
