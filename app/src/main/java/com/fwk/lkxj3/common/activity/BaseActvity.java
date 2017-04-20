package com.fwk.lkxj3.common.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.ui.listener.OnSucceedListener;
import com.fwk.lkxj3.android.ui.listener.Page_up_down;
import com.fwk.lkxj3.android.okhttp.OKHttp;
import com.fwk.lkxj3.android.view.New_PopupWindow;
import com.fwk.lkxj3.common.util.DialogUtils;
import com.fwk.lkxj3.common.util.SoftInputUtil;


/**
 * Created by fanwenke on 16/9/22.
 */
public abstract class BaseActvity extends FragmentActivity implements OnSucceedListener,Page_up_down {
    private OKHttp okHttp;
    private BaseActvity mActivity = null;

    private ViewGroup rootView = null;
    private ViewGroup headView = null;
    public ImageView ivnewtype;
    private ImageView backView;
    public New_PopupWindow mPopup;
    public Dialog dialog;
    public static String LOGIN_NAME;
    public static int LOGIN_KGID = 0;
    private View.OnClickListener onNewTypeClickListener = null;

    private View.OnClickListener mOnBackListener = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        SoftInputUtil.hideSoftInputMode(this);
        dialog = DialogUtils.waitForDialog(BaseActvity.this,null);
        mActivity = this;
    }


    @Override
    public void setContentView(int layoutResID) {
        setContentView(mActivity.getLayoutInflater().inflate(layoutResID,null));

    }
    @Override
    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view == null) {
            try {
                throw new NullPointerException("View is Null Exception");
            } catch (Exception e) {
                return;
            }
        }
        rootView = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.main_content_layout,null);
        headView = (ViewGroup) rootView.findViewById(R.id.xjkp_rl_title);
        ivnewtype = (ImageView) headView.findViewById(R.id.xjkp_iv_new);
        ViewGroup contentView = (ViewGroup) rootView.findViewById(R.id.xjkp_fl_layout);
        contentView.addView(view);
        super.setContentView(rootView);

        //设置新增按钮点击事件
        initNewTypeListener();
        initTitleBack();
    }

    public abstract void initNewTypeListener();

    //隐藏显示title标题栏
    public void setTitleVisable(boolean visible){
        if(visible){
            headView.setVisibility(View.VISIBLE);
        }else {
            headView.setVisibility(View.GONE);
        }
    }
    //隐藏显示新增按钮
    public void setNewTypeVisable(boolean visable){
        if (visable){
            ivnewtype.setVisibility(View.VISIBLE);
        }else {
            ivnewtype.setVisibility(View.GONE);
        }
    }
    //设置标题文字
    public void setTitleString(int stringId){
        TextView textView = (TextView) headView.findViewById(R.id.xjkp_tv_title);
        textView.setText(stringId);
    }
    public void setTitleString(String title){
        TextView textView = (TextView) headView.findViewById(R.id.xjkp_tv_title);
        textView.setText(title);
    }

    public void setOkHttp(){
        okHttp = OKHttp.getInstance();
        okHttp.setListener(this);
    }
    public  void getOkhttp(int flag, String url, Class cla){
        okHttp.getAsynHttp(flag,url,cla);
    };
    public  void postOkhttp(int flag, String url, Class cla){

    }
    //返回按钮的事件
    private void initTitleBack() {
        backView = (ImageView) headView.findViewById(R.id.xjkp_iv_back);
        if (mOnBackListener != null){
            //每个Activity的返回按钮的操作的响应
            backView.setOnClickListener(mOnBackListener);
        }else{
            backView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    BaseActvity.this.finish();
                }
            });
        }
    }
    public void setBackViewVisable(boolean visable){
        if(visable){
            backView.setVisibility(View.VISIBLE);
        }else {
            backView.setVisibility(View.GONE);
        }
    }
}
