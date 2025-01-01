package com.example.terminal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.LogUtils;
import com.example.terminal.util.ToastUtils;

/**
 * author : King
 * date   : 2022/8/169:21
 * desc   : 监听手机状态
 */
public class NetPowerBroadcastReceiver extends BroadcastReceiver {

    private BaseActivity mActivity;

    public NetPowerBroadcastReceiver(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.e("广播变化", intent.getAction());

        switch (intent.getAction()) {
            case Intent.ACTION_BATTERY_CHANGED:
                CommonUtils.writeNetStatusFile("当前电量:" + CommonUtils.powerPercentAge(mActivity));
                break;
            case ConnectivityManager.CONNECTIVITY_ACTION:
                if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
                    ToastUtils.showToast(R.string.toast_net_error);
                    CommonUtils.writeNetStatusFile("网络断开" + "....电量:" + CommonUtils.powerPercentAge(mActivity));
                } else {
                    CommonUtils.writeNetStatusFile("网络连接" + "....电量:" + CommonUtils.powerPercentAge(mActivity));
                }
                break;
        }
    }
}