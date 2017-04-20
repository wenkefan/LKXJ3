package com.fwk.lkxj3.common.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fwk.lkxj3.R;
import com.fwk.lkxj3.common.util.DialogUtils;
import com.fwk.lkxj3.common.util.KeyboardUtils;


/**
 * Created by fanwenke on 16/9/29.
 */

public abstract class BaseFragment extends Fragment implements IFragmentLoad {
    private ViewGroup rootView = null;
    private ViewGroup selectObject = null;
    public EditText etObj = null;
    public TextView JCR;
    public TextView BJCR;
    public TextView TIME;
    public TextView ZF;
    public TextView BT;
    public RelativeLayout rljcr;
    public RelativeLayout rlbjcr;
    public RelativeLayout rltime;
    public RelativeLayout rlzf;
    private ImageView back;

    private BaseActvity mActivity = null;

    private View.OnClickListener mObjListener = null;
    private View.OnClickListener mOnBackListener = null;
    public Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.xjkp_main_fragment_layout, null);
        mActivity = (BaseActvity) getActivity();
        dialog = DialogUtils.waitForDialog(mActivity,null);
        super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeyboardUtils.hideSoftInput(mActivity);
        onFragmentCreate(rootView);
    }

    @Override
    public void onResume() {
        super.onResume();
        onFragmnetResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onFragmentDestory();
    }

    public void setContentView(int layoutResID){
        setContentView(mActivity.getLayoutInflater().inflate(layoutResID,null));
    }
    public void setContentView(View view){
        setContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (view == null){
            try {
                throw new NullPointerException("View is Null Exception");
            }catch (Exception e){
                return;
            }
        }

        ViewGroup viewGroup = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.main_fragment_content_layout,null);
        selectObject = (ViewGroup) viewGroup.findViewById(R.id.xjkp_rl_title);
        ViewGroup contentView = (ViewGroup) viewGroup.findViewById(R.id.xjkp_fl_layout);
        contentView.addView(view,layoutParams);
        rootView.addView(viewGroup,layoutParams);
        BT = (TextView) selectObject.findViewById(R.id.rl_fragment_title);
        JCR = (TextView) selectObject.findViewById(R.id.xjkp_tv_jiancharen);
        BJCR = (TextView) selectObject.findViewById(R.id.xjkp_tv_beijiancharen);
        TIME = (TextView) selectObject.findViewById(R.id.xjkp_tv_time);
        ZF = (TextView) selectObject.findViewById(R.id.xjkp_tv_zongfen);
        rljcr = (RelativeLayout) selectObject.findViewById(R.id.rl_jiancharen);
        rlbjcr = (RelativeLayout) selectObject.findViewById(R.id.rl_beijiancharen);
        rltime = (RelativeLayout) selectObject.findViewById(R.id.rl_time);
        rlzf = (RelativeLayout) selectObject.findViewById(R.id.rl_zongfen);
        back = (ImageView) selectObject.findViewById(R.id.xjkp_iv_back);
        //初始化我们的返回按钮
        initTitleBack(back);
    }
    //初始化我们的返回按钮
    private void initTitleBack(ImageView back) {
        if (mOnBackListener != null){
            back.setOnClickListener(mOnBackListener);
        }else{
            //系统自带的 结束当前的Activity
            back.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }
    }
    public void setBT(String str){
        BT.setText(str);
    }
    public void setJCR(String str) {
        JCR.setText(str);
    }
    public void setBJCR(String str) {
        BJCR.setText(str);
    }
    public void setTIME(String str) {
        TIME.setText(str);
    }
    public void setZF(String str) {
        ZF.setText(str);
    }
    public String getJCR(){
        return JCR.getText().toString();
    }
    public String getBJCR(){
        return BJCR.getText().toString();
    }
    public String getTIME(){
        return TIME.getText().toString();
    }
    public String getZF(){
        return ZF.getText().toString();
    }
    public void BJCRVisiblity(boolean tf){
        if (tf){
            rlbjcr.setVisibility(View.VISIBLE);
        }else {
            rlbjcr.setVisibility(View.GONE);
        }
    }
    public void TIMEVisiblity(boolean tf){
        if (tf){
            rltime.setVisibility(View.VISIBLE);
        }else {
            rltime.setVisibility(View.GONE);
        }
    }
    public void ZFVisiblity(boolean tf){
        if (tf){
            rlzf.setVisibility(View.VISIBLE);
        }else {
            rlzf.setVisibility(View.GONE);
        }
    }
    //fragment显示隐藏对象
    public void setFragmeTitleVisibor(boolean boo){
        if (!boo){
            selectObject.setVisibility(View.VISIBLE);
        }else {
            selectObject.setVisibility(View.GONE);
        }
    }
}
