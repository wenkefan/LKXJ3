package com.fwk.lkxj3.android.ui.version2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fwk.lkxj3.MyApplication;
import com.fwk.lkxj3.R;
import com.fwk.lkxj3.android.constant.Keyword;
import com.fwk.lkxj3.android.ui.adapter.Fragment_Photo_Adapter;
import com.fwk.lkxj3.android.ui.fragment.SetImage;
import com.fwk.lkxj3.common.activity.BaseActvity;
import com.fwk.lkxj3.common.util.LogUtils;
import com.fwk.lkxj3.common.util.ScreenUtils;
import com.fwk.lkxj3.common.util.SharedPreferencesUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class PaizhaoActivity extends BaseActvity implements AdapterView.OnItemClickListener {
    private static final int INTENT_CAMERA = 1;
    private GridView gridView;
    private Fragment_Photo_Adapter2 adapter;
    private ArrayList<String> imagePathList;
    private String PhotoPath = null;
    private String time = "";
    private SharedPreferencesUtils sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectimage_fragment);
        gridView = (GridView) findViewById(R.id.gv_photo);
        sp = new SharedPreferencesUtils();
        // 初始化屏幕像素
        ScreenUtils.initScreen(PaizhaoActivity.this);
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
        setNewTypeVisable(false);
        setTitleString("拍照");
        adapter = new Fragment_Photo_Adapter2(imagePathList,PaizhaoActivity.this);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        imagePathList = (ArrayList<String>) sp.queryForSharedToObject(Keyword.KEY_PHOTO_PATH);
        if (imagePathList == null || imagePathList.size() == 0){
            imagePathList = new ArrayList<>();
            imagePathList.add("first");
        }
        adapter.setImagePathList(imagePathList);
        adapter.notifyDataSetChanged();
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
            startActivityForResult(intent1, INTENT_CAMERA);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == INTENT_CAMERA) { // 拍照
            setImagemt();
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

    @Override
    public void initNewTypeListener() {

    }

    @Override
    public <T> void OnSucceed(int flag, T cla, String message) {

    }

    @Override
    public void Error() {

    }

    @Override
    public void up_page() {

    }

    @Override
    public void down_page() {

    }
}
