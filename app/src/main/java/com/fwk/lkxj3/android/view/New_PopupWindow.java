package com.fwk.lkxj3.android.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.ui.adapter.New_PopupAdapter;
import com.fwk.lkxj3.common.util.FastBlur;
import com.fwk.lkxj3.common.util.LogUtils;


/**
 * Created by fanwenke on 16/9/18.
 */
public class New_PopupWindow extends PopupWindow implements View.OnClickListener {
    private Activity mContext;

    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Bitmap mBitmap = null;
    private Bitmap overlay = null;

    private RecyclerView mRecyclerView;
    private ImageView mIvBack;
    private RecyclerView.LayoutManager mLayoutManager;
    private New_PopupAdapter mRecyclerViewAdapter;
    private static final int SPAN_COUNT = 3;
    public New_PopupWindow(Activity mContext){
        this.mContext = mContext;
    }
    public void init() {//设置popupWindow出现的位置  全屏
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);
    }
    public void showMoreWindow(View anchor) {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.xjkp_popupwindow, null,false);
        setContentView(layout);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.rv_xjkp_popupwindow);
        mIvBack = (ImageView) layout.findViewById(R.id.iv_xjkp_close);
        mIvBack.setOnClickListener(this);
        mLayoutManager = new GridLayoutManager(mContext, SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        mRecyclerViewAdapter = new New_PopupAdapter(mContext);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));
        setOutsideTouchable(true);
        setFocusable(true);
        showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
    }
    private Bitmap blur() {
        if (null != overlay) {
            return overlay;
        }
        long startMs = System.currentTimeMillis();

        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();

        float scaleFactor = 8;
        float radius = 10;
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        overlay = Bitmap.createBitmap((int) (width / scaleFactor), (int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        LogUtils.e( "blur time is:" + (System.currentTimeMillis() - startMs));
        return overlay;
    }
    public void destroy() {
        if (null != overlay) {
            overlay.recycle();
            overlay = null;
            System.gc();
        }
        if (null != mBitmap) {
            mBitmap.recycle();
            mBitmap = null;
            System.gc();
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
