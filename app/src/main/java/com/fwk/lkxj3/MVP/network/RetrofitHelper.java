package com.fwk.lkxj3.MVP.network;


import com.fwk.lkxj3.MyApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by fanwenke on 16/11/15.
 */

public class RetrofitHelper {

//  private static final String API = "http://192.168.1.116:8083/WebServices/KPService.ashx?";
    private static final String API = "http://manage.youery.com/WebServices/KPService.ashx?";
    private static final String API_LOGIN = "http://manage.youery.com/WebServices/MobileAttendanceService.ashx?";
//  private static final String API_LOGIN = "http://192.168.1.116:8083/WebServices/MobileAttendanceService.ashx?";


    private static OkHttpClient okHttpClient;

    static {
        initOkHttpClient();
    }

//    public static


    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (okHttpClient == null){
            synchronized (RetrofitHelper.class){
                if (okHttpClient == null){
                    //设置Http缓存
                    Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(),
                            "HttpCache"),1024 * 1024 * 100);

                    okHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }


}
