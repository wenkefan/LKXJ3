package com.fwk.lkxj3;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.StrictMode;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fwk.lkxj3.photo.ImagePipelineConfigFactory;

public class MyApplication extends Application {
	
	private static MyApplication mInstance ;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		getApplicationContext();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
			StrictMode.setVmPolicy(builder.build());
		}
		Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this));
	}
	
	public static Context getContext(){
		return mInstance.getApplicationContext();
	}

	/**
	 * 获取资源对象
	 */
	public static Resources getResourcesObj(){
		return mInstance.getResources();
	}
	
	/**
	 * 判断程序是否在Debug模式下运行
	 * @return
	 */
	public static boolean isDebug(){
		ApplicationInfo info = mInstance.getApplicationInfo();
		return ( info.flags & ApplicationInfo.FLAG_DEBUGGABLE ) != 0;
	}
}
