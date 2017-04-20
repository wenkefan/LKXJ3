package com.fwk.lkxj3.MVP.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.fwk.lkxj3.MVP.KeyValue;
import com.fwk.lkxj3.MVP.base.MVPBasePresenter;
import com.fwk.lkxj3.MVP.listener.SuccessListener;
import com.fwk.lkxj3.MVP.model.MainActivityModel;
import com.fwk.lkxj3.MVP.ui.MVP_MainActivity;
import com.fwk.lkxj3.MVP.ui.MVP_TypeList;
import com.fwk.lkxj3.MVP.view.IMain_Activity_view;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.ui.TypeList;
import com.fwk.lkxj3.android.ui.adapter.Adapter_main_activity;
import com.fwk.lkxj3.common.util.LogUtils;

/**
 * Created by fanwenke on 16/11/10.
 */

public class MainActivityPresenter extends MVPBasePresenter<IMain_Activity_view> implements SuccessListener, AdapterView.OnItemClickListener {

    private Context context;
    private IMain_Activity_view view;
    private MainActivityModel model;
    private Adapter_main_activity adapter;
    private ImageView imageView;
    private GridView gridView;

    public MainActivityPresenter(Context context){
        this.context = context;
        model = new MainActivityModel();
        model.setSuccessListener(this);
    }

    public void initView(){

        view = getView();
        if (view != null) {
            imageView = view.getImageView();
            gridView = view.getGridView();
        }
    }

    public void setTypeList(int flag, String url){
        model.mainurl(flag,url);
    }

    @Override
    public void URLSuccess(int flag) {
        switch (flag){
            case KeyValue.SP_TYPE_FLAG:
                setAdapter();
                break;
        }
    }
    private void setAdapter() {
        adapter = new Adapter_main_activity();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        LogUtils.d(adapter.getCount()+"---");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String name = list.get(position).getCategoryName();
//        int CheckCategoryId = list.get(position).getCheckCategoryId();
//        int checkConclusionid = list.get(position).getCheckConclusionId();
//        String CheckObjectTable = list.get(position).getCheckObjectTable();
//        int CheckObjectCategory = list.get(position).getCheckObjectCategory();
        Intent intent = new Intent(context, MVP_TypeList.class);
//        intent.putExtra(Keyword.KEY_TYPE_LIST_NAME,name);
//        intent.putExtra(Keyword.KEY_CHECKCATEGORYID,CheckCategoryId);
//        intent.putExtra(Keyword.KEY_CHECKCONCLUSIONID,checkConclusionid);
//        intent.putExtra(Keyword.KEY_CHECKOBJECTTABLE,CheckObjectTable);
//        intent.putExtra(Keyword.KEY_CHECKOBJECTCATEGORY,CheckObjectCategory);
        context.startActivity(intent);
    }
}
