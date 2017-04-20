package com.fwk.lkxj3.android.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.bean.AutoUpdaterDTO;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.common.util.AutoUpdaterUtils;
import com.fwk.lkxj3.common.util.LogUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;


/**
 * 开屏页
 */
public class SplashActivity extends Activity implements Animation.AnimationListener {
    private LinearLayout rootLayout;
    private TextView versionText;

    private static final int sleepTime = 2000;
    private AutoUpdaterDTO auto;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.xjkp_activity_splash);
        super.onCreate(arg0);

        rootLayout = (LinearLayout) findViewById(R.id.splash_root);
        versionText = (TextView) findViewById(R.id.tv_version);

        versionText.setText(getVersion());
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        animation.setAnimationListener(this);
        rootLayout.startAnimation(animation);

    }

    /**
     * 获取当前应用程序的版本号名称
     */
    private String getVersion() {
        String st = getResources().getString(R.string.Version_number_is_wrong);
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    // 进入main
    public void loginMain() {
        // 进入主页面
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        check();
//        loginMain();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    public void check() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    auto = AutoUpdaterUtils.getInstance().http();
                } catch (IOException | XmlPullParserException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (auto != null) {
                    Log.d("ANXU",
                            auto.getAllowMinVersion() + "----"
                                    + auto.getCurrentVersion());
                    if (maxVersions(auto.getCurrentVersion())) {
                        dialog();
                    } else {
                        loginMain();
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(SplashActivity.this, "请连接网络", 0)
                                    .show();
                            SplashActivity.this.finish();
                        }
                    });
                }

            }
        }).start();

    }

    private boolean maxVersions(String max){
        int locallength= getVersion().split("\\.").length;
        long localversion = Integer.valueOf((getVersion().split("\\."))[locallength-1]);
        int networklength= max.split("\\.").length;
        long networkversion = Integer.valueOf((max.split("\\."))[networklength-1]);

        return localversion < networkversion ? true : false;
    }

    /**
     * 最低版本号
     *
     * @return
     */
    public boolean minVersions(String min) {
        int locallength= getVersion().split("\\.").length;
        long localversion = Integer.valueOf((getVersion().split("\\."))[locallength-1]);
        int networklength= min.split("\\.").length;
        long networkversion = Integer.valueOf((min.split("\\."))[networklength-1]);

        return localversion >= networkversion ? true : false;

    }

    /**
     * 版本更新提示
     */
    protected void dialog() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // 判断强制更新和费强制更新
                if (!minVersions(auto.getAllowMinVersion())) {
                    AlertDialog.Builder b = new AlertDialog.Builder(SplashActivity.this);
                    b.setTitle("版本更新提示");
                    b.setMessage("版本过低请更新");
                    b.setPositiveButton("是",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    if (fileIsExists(auto
                                            .getCurrentVersionFileName())) {
                                    } else
                                        downloadApp(
                                                auto.getCurrentVersionFileUrl(),
                                                auto.getCurrentVersionFileName());
                                }
                            });
                    b.setCancelable(false);// 设置点击屏幕Dialog不消失
                    b.create().show();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(SplashActivity.this);
                    b.setTitle("版本更新提示");
                    b.setMessage("您有新版本是否更新");
                    b.setPositiveButton("是",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
//                                    if (fileIsExists(auto
//                                            .getCurrentVersionFileName())) {
//                                    } else
                                    downloadApp(
                                            auto.getCurrentVersionFileUrl(),
                                            auto.getCurrentVersionFileName());
                                }
                            });
                    if (minVersions(auto.getAllowMinVersion())) {
                        b.setNegativeButton("否",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                        loginMain();

                                    }
                                });
                    }
                    b.setCancelable(false);// 设置点击屏幕Dialog不消失
                    b.create().show();
                }
            }
        });

    }

    ProgressDialog tDialog;
    HttpHandler<File> handler;
    /**
     * 下载最新安装包
     *
     * @param uri     地址
     * @param apkname 名字
     */
    @SuppressWarnings("deprecation")
    public void downloadApp(String uri, String apkname) {
        // 下载路径
        String weizhi = "";
        tDialog = new ProgressDialog(this);
        tDialog.setCanceledOnTouchOutside(false);
        tDialog.setProgressNumberFormat("%1d kb/%2d kb");
        tDialog.setCancelable(false);
        tDialog.setTitle("下载进度");
        tDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        tDialog.setButton("取消下载", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.cancel();
                finish();
            }
        });

        System.out.println(apkname);
        weizhi = Environment.getExternalStorageDirectory() + Keyword.SP_XIAZAIDIZHIAPK
                + apkname;

        File file = new File(weizhi);
        if (file.exists()) {

        }

        HttpUtils utils = new HttpUtils();
        handler = utils.download(uri, weizhi, new RequestCallBack<File>() {

            @Override
            public void onStart() {
                // 开始下载 super.onStart();
                System.out.println("开始下载");

            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                // 下载中 super.onLoading(total, current, isUploading);

                System.out.println("下载中。。。。");

                tDialog.setMax((int) (total / 1024));
                tDialog.show();
                tDialog.setProgress((int) (current / 1024));

            }

            @Override
            public void onCancelled() {
                // 取消下载 super.onCancelled();

                System.out.println("取消下载");
                Toast.makeText(SplashActivity.this, "取消下载", 1).show();
                tDialog.dismiss();

            }

            public void onSuccess(ResponseInfo<File> arg0) {
                // 下载成功
                System.out.println("下载成功:" + arg0.result.getPath());
                Toast.makeText(SplashActivity.this, "下载成功", 1).show();
                tDialog.dismiss();
                openFile(arg0.result);

            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // 下载失败
                Toast.makeText(SplashActivity.this,
                        "下载失败原因：" + arg0.getExceptionCode(), 1).show();
                tDialog.dismiss();

            }

        });

    }



    /**
     * 文件是否已下载
     *
     * @param apkname
     * @return
     */
    public boolean fileIsExists(String apkname) {
        String path = Environment.getExternalStorageDirectory() + Keyword.SP_XIAZAIDIZHIAPK
                + apkname;
        boolean exists = true;
        File file;
        try {
            file = new File(path);
            if (!file.exists()) {
                exists = false;
                return exists;
            }

        } catch (Exception e) {
            // TODO: handle exception
            exists = false;
            return exists;
        }
        if (exists) {
            if (file != null) {
                openFile(file);
            }
        }
        return exists;
    }

    /**
     * 打开安装包
     *
     * @param file
     */
    private void openFile(File file) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

}
