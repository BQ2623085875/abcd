package com.example.terminal.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager.BadTokenException;

import com.example.terminal.R;
import com.example.terminal.global.MainApplication;

public class UploadProgressUtils {

	private static ProgressDialog progressDialog;
	/**
	 * 显示正在加载的进度条
	 */
	public static void showProgress(Context context) {
		if(context == null) return;
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();

			progressDialog = null;
		}
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(MainApplication.getContext().getResources().getString(R.string.text_upload_loading));
		progressDialog.setCanceledOnTouchOutside(false);
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}

	public static void showProgress(Context context , String msg) {
		if(context == null) return;
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(msg);
		progressDialog.setCanceledOnTouchOutside(false);
		try {
			progressDialog.show();
		} catch (BadTokenException exception) {
			exception.printStackTrace();
		}
	}


	/**
	 * 隐藏正在加载的进度条
	 */
	public static void dismissProgress() {
		if (null != progressDialog && progressDialog.isShowing() == true) {
			try {
				progressDialog.dismiss();
				progressDialog = null;
			}catch (Exception e){
				progressDialog = null;
			}
		}
	}
}