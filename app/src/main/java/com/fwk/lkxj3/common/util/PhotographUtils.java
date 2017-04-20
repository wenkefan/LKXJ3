package com.fwk.lkxj3.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 图片压缩
 *
 * @author Anxu
 */
public class PhotographUtils {

	// 图片压缩方法
	public static void compressFile(String path) {
//		String subForder = path;
//		File foder = new File(subForder);
//		if (!foder.exists()) {
//			foder.mkdirs();
//		}
//		File myCaptureFile = new File(subForder, fileName);
//		if (!myCaptureFile.exists()) {
//			myCaptureFile.createNewFile();
//		}
//		BufferedOutputStream bos = new BufferedOutputStream(
//				new FileOutputStream(myCaptureFile));
//		bm.compress(Bitmap.CompressFormat.JPEG, 50, bos);
//		bos.flush();
//		bos.close();

		try {

			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}

			Bitmap bm = BitmapFactory.decodeFile(path);

			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bm.compress(Bitmap.CompressFormat.JPEG, 50, bos);
			bos.flush();
			bos.close();

		} catch (Exception e) {

		}



	}


	public static void compressPicture(String srcPath, String desPath) {
		FileOutputStream fos = null;
		BitmapFactory.Options op = new BitmapFactory.Options();

		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		op.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);
		op.inJustDecodeBounds = false;

		// 缩放图片的尺寸
		float w = op.outWidth;
		float h = op.outHeight;
		float hh = 1024f;//
		float ww = 1024f;//
		// 最长宽度或高度1024
		float be = 1.0f;
		if (w > h && w > ww) {
			be = (float) (w / ww);
		} else if (w < h && h > hh) {
			be = (float) (h / hh);
		}
		if (be <= 0) {
			be = 1.0f;
		}
		op.inSampleSize = (int) be;// 设置缩放比例,这个数字越大,图片大小越小.
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, op);
		int desWidth = (int) (w / be);
		int desHeight = (int) (h / be);
		bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
		try {
			fos = new FileOutputStream(desPath);
			if (bitmap != null) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}





}
