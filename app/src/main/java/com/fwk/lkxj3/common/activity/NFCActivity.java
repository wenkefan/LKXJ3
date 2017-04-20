package com.fwk.lkxj3.common.activity;


import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fwk.lkxj3.common.util.DataConversionUtils;


/**
 * 此类继承 BaseActivity [为了让CardActivity具有注销的方法]
 * 		带有NFC功能
 * */
public abstract class NFCActivity extends BaseActvity {

	private NfcAdapter nfcAdapter;
	private IntentFilter[] intentFilters = null;
	private String[][] techList = null;
	private PendingIntent pendingIntent;

	/**  改为在onStart中判断，只要此页面可见且没有开启NFC，就跳转到设置NFC的界面  */
	@Override
	protected void onStart() {
		super.onStart();
		// 获得NFC管理器

		nfcAdapter = NfcAdapter.getDefaultAdapter(this);

		// 判断设备上是否有NFC功能
		if (nfcAdapter == null) {
			Toast.makeText(this, "此设备不支持NFC功能，请更换设备！", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		// 判断设备上的NFC功能是否开启
		if (!nfcAdapter.isEnabled()) {
			Toast.makeText(this, "请先在系统设置中启用NFC功能！", Toast.LENGTH_SHORT).show();

			/**  跳转设置界面，开启NFC  by _wzz  */
//			Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);  // 飞行模式，无线网和网络设置界面

//			Intent intent =  new Intent(Settings.ACTION_SETTINGS);   // 跳转到设置界面
//			startActivity(intent);


		}

		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter ndefFilter = new IntentFilter(
				NfcAdapter.ACTION_NDEF_DISCOVERED);
		IntentFilter techFilter = new IntentFilter(
				NfcAdapter.ACTION_TECH_DISCOVERED);
		intentFilters = new IntentFilter[] { ndefFilter, techFilter };
		techList = new String[][] { new String[] { "android.nfc.tech.IsoDep" },
				new String[] { "android.nfc.tech.NfcA" },
				new String[] { "android.nfc.tech.NfcB" },
				new String[] { "android.nfc.tech.NfcF" },
				new String[] { "android.nfc.tech.NfcV" },
				new String[] { "android.nfc.tech.Ndef" },
				new String[] { "android.nfc.tech.NdefFormatable" },
				new String[] { "android.nfc.tech.MifareClassic" },
				new String[] { "android.nfc.tech.MifareUltralight" } };
	}

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}


	@Override
	public void onResume() {
		if (nfcAdapter != null)
			nfcAdapter.enableForegroundDispatch(this, pendingIntent,
					intentFilters, techList);
		super.onResume();
	}

	@Override
	public void onPause() {
		if (nfcAdapter != null)
			nfcAdapter.disableForegroundDispatch(this);
		super.onPause();
	}


	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	/**
	 * 读取IC卡的卡号
	 * 
	 * @return
	 */
	protected String readICCardNo(Intent intent) {
		String cardNo = "";
		try {
			byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
			if (id == null)
				return null;
			byte[] newByte = new byte[id.length];
			for (int i = 0; i < id.length; i++) {
				newByte[i] = id[id.length - (i + 1)];
			}
			cardNo = DataConversionUtils.toHexString(newByte);
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
		}

		Log.v("wzz------", cardNo);
		return cardNo;
	}



	/**
	 * 注销方法，弹出Dialog
	 */
	/*public void openDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("  注销当前教师 ?");
		builder.setTitle("提示");
		builder.setPositiveButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.setNegativeButton("确认",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								NFCActivity.this,
								TeacherActivity.class);
						startActivity(intent);
						dialog.dismiss();
						finish();

					}
				});
		builder.create().show();
	}*/


}
