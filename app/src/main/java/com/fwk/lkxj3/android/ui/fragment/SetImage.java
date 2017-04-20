package com.fwk.lkxj3.android.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.ui.NewTypeActivity;
import com.fwk.lkxj3.android.ui.adapter.Fragment_Photo_Adapter;
import com.fwk.lkxj3.common.activity.BaseFragment;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.ScreenUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanwenke on 16/10/13.
 */

public class SetImage extends BaseFragment implements AdapterView.OnItemClickListener {
    private static final int INTENT_CAMERA = 1;
    private GridView gridView;
    private Fragment_Photo_Adapter adapter;
    private ArrayList<String> imagePathList;
    private String PhotoPath = null;
    private String time = "";
    private SharedPreferencesUtils sp;
    public static final SetImage newInstance(Activity activity){
        SetImage setImage = new SetImage();
        return setImage;
    }
    @Override
    public void onFragmentCreate(ViewGroup rootView) {
        setContentView(R.layout.selectimage_fragment);
        gridView = (GridView) rootView.findViewById(R.id.gv_photo);
        sp = new SharedPreferencesUtils();
        // 初始化屏幕像素
        ScreenUtils.initScreen(getActivity());
        init();
        setDefoutPhoto();
        initView();
    }

    private void init() {
        // 图片路径初始化
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {  // 有内存卡
            PhotoPath = Environment.getExternalStorageDirectory().getPath() + "/lkxj/imgCamera/";
        } else {  // 无内存卡
            PhotoPath = MyApplication.getContext().getFilesDir().getPath() + "/lkxj/imgCamera/";
        }
    }

    private void setDefoutPhoto() {
        imagePathList = new ArrayList<>();
        imagePathList.add("first");
        File file = new File(PhotoPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void initView() {
        BJCRVisiblity(true);
        TIMEVisiblity(false);
        ZFVisiblity(false);
        adapter = new Fragment_Photo_Adapter(imagePathList,SetImage.this);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onFragmnetResume() {
        setJCR(NewTypeActivity.JC);
        setBJCR(NewTypeActivity.BJC);
        setTIME(NewTypeActivity.TIME);
        setBT(NewTypeActivity.TITLENAME);
        imagePathList = (ArrayList<String>) sp.queryForSharedToObject(Keyword.KEY_PHOTO_PATH);
        if (imagePathList == null || imagePathList.size() == 0){
            imagePathList = new ArrayList<>();
            imagePathList.add("first");
        }
        adapter.setImagePathList(imagePathList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFragmentDestory() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (imagePathList.get(position).equals("first")) {
            File file = new File(PhotoPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(PhotoPath, "IMG_" + time + ".png")));
            intent1.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            getActivity().startActivityForResult(intent1, INTENT_CAMERA);
        }
    }
    public void setImagemt(){

            String fileName = PhotoPath + "IMG_" + time + ".png";

            if (new File(fileName).exists()) {

                imagePathList.add(fileName);

                if (imagePathList.size() > 4) {  // 从相机拍照返回，如果张数大于4，就移除"+"图片
                    imagePathList.remove(0);
                }
                for (String i: imagePathList) {
                    LogUtils.d(i);
                }
                adapter.setImagePathList(imagePathList);
                adapter.notifyDataSetChanged();
                sp.saveToShared(Keyword.KEY_PHOTO_PATH,imagePathList);
            }
    }
    public void deleImageView(int position){
        imagePathList.remove(position);
        if (!imagePathList.get(0).equals("first")) {
            imagePathList.add(0, "first");
        }
        adapter.setImagePathList(imagePathList);
        adapter.notifyDataSetChanged();
        sp.saveToShared(Keyword.KEY_PHOTO_PATH,imagePathList);
    }
    public List<String> ImageUrlList(){
        return imagePathList;
    }
}
