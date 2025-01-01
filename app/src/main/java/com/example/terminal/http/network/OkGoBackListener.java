package com.example.terminal.http.network;

import android.app.Activity;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.global.MainApplication;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ToastUtils;


/**
 * @author : YanKing
 * @date : 2019/3/22  15:41
 * @Description : OkGo请求结果
 */
public class OkGoBackListener {

    private BaseActivity mActivity;

    public OkGoBackListener(Activity mActivity) {
        this.mActivity = (BaseActivity) mActivity;
    }

    public void onSuccess(Object body) {
    }

    public void onWrong(String msg) {
        CommonUtils.writeCrashLog(MainApplication.getContext().getResources().getString(R.string.net_api_back_error) + ":" + msg);
        ToastUtils.showToast(msg);
    }

    public void onError(String body) {
        CommonUtils.writeCrashLog(MainApplication.getContext().getResources().getString(R.string.net_error) + ":" + body);
        ToastUtils.showToast(R.string.net_error);
    }

    public void onDataEmpty() {
        ToastUtils.showToast(MainApplication.getContext().getString(R.string.net_data_empty));
    }

    public void onChangeError(String error) {
        CommonUtils.writeCrashLog(MainApplication.getContext().getResources().getString(R.string.text_change_error) + ":" + error);
    }

    public void onError() {
    }

    public BaseActivity getActivity() {
        return mActivity;
    }
}
