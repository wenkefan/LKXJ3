package com.fwk.lkxj3.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;


import com.fwk.lkxj3.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 从SDCard异步加载图片
 *
 * @author hanj 2013-8-22 19:25:46
 */
public class SDCardImageLoader {
    // 缓存
    private LruCache<String, Bitmap> imageCache;
    // 固定2个线程来执行任务
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private Handler handler = new Handler();

    private int screenW, screenH;

    public SDCardImageLoader(int screenW, int screenH) {
        this.screenW = screenW;
        this.screenH = screenH;

        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;

        // 设置图片缓存大小为程序最大可用内存的1/8
        imageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    private Bitmap loadDrawable(final int smallRate, final String filePath,
                                final ImageCallback callback) {
        // 如果缓存过就从缓存中取出数据
        if (imageCache.get(filePath) != null) {
            Log.d("fwk", "1");
            return imageCache.get(filePath);
        }

        // 如果缓存没有则读取SD卡
        executorService.submit(new Runnable() {
            public void run() {
                try {
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, opt);
                    Log.d("fwk", "2");
                    // 获取到这个图片的原始宽度和高度
                    int picWidth = opt.outWidth;
                    int picHeight = opt.outHeight;

                    // 读取图片失败时直接返回
                    if (picWidth == 0 || picHeight == 0) {
                        Log.d("fwk", "3");
                        return;
                    }

                    // 初始压缩比例
                    opt.inSampleSize = smallRate;
                    // 根据屏的大小和图片大小计算出缩放比例
                    if (picWidth > picHeight) {
                        if (picWidth > screenW)
                            opt.inSampleSize *= picWidth / screenW;
                    } else {
                        if (picHeight > screenH)
                            opt.inSampleSize *= picHeight / screenH;
                    }
                    Log.d("fwk", "4");
                    // 这次再真正地生成一个有像素的，经过缩放了的bitmap
                    opt.inJustDecodeBounds = false;
                    final Bitmap bmp = BitmapFactory.decodeFile(filePath, opt);
                    // 存入map
                    Log.d("fwk", "5");
                    Log.d("fwk", filePath + "---filepath" + bmp.toString()
                            + "---bmp");
                    imageCache.put(filePath, bmp);

                    handler.post(new Runnable() {
                        public void run() {
                            callback.imageLoaded(bmp);
                            System.out.println(bmp.toString() + "---bmp");
                            Log.d("fwk", "6");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("fwk", "7");
                }
            }
        });

        return null;
    }

    /**
     * 异步读取SD卡图片，并按指定的比例进行压缩（最大不超过屏幕像素数）
     *
     * @param smallRate 压缩比例，不压缩时输入1，此时将按屏幕像素数进行输出
     * @param filePath  图片在SD卡的全路径
     * @param imageView 组件
     */
    public void loadImage(int smallRate, final String filePath,
                          final ImageView imageView) {

        Bitmap bmp = loadDrawable(smallRate, filePath, new ImageCallback() {

            @Override
            public void imageLoaded(Bitmap bmp) {
                if (imageView.getTag().equals(filePath)) {
                    Log.d("fwk", "1");
                    if (bmp != null) {
                        Log.d("fwk", "2");
                        imageView.setImageBitmap(bmp);
                    } else {
                        Log.d("fwk", "3");
                        imageView.setImageResource(R.mipmap.empty_photo_cj);
                    }
                }
            }
        });
        Log.d("fwk", "4");
        if (bmp != null) {
            if (imageView.getTag().equals(filePath)) {
                Log.d("fwk", "5");
                imageView.setImageBitmap(bmp);
            }
        } else {
            Log.d("fwk", "6");
            imageView.setImageResource(R.mipmap.empty_photo_cj);
        }

    }

    // 对外界开放的回调接口
    public interface ImageCallback {
        // 注意 此方法是用来设置目标对象的图像资源
        public void imageLoaded(Bitmap imageDrawable);
    }

}