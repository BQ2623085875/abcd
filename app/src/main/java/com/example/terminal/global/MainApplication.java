package com.example.terminal.global;

import android.app.Application;
import android.content.Context;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.listener.CrashHandler;
import com.example.terminal.receiver.BluetoothBootBroadcastReceiver;
import com.example.terminal.util.CommonUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;

import java.util.ArrayList;

public class MainApplication extends Application {

    private static Context mContext;

    private static ArrayList<BaseActivity> activityList;
    private BluetoothBootBroadcastReceiver broadcastReceiver;
    private int versionCode;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        getVersionCodeMethod();

        initUnusualList();

        broadcastReceiver = new BluetoothBootBroadcastReceiver();

        CrashHandler.getInstance().init();

        //初始化OkGo
        OkGo.getInstance().init(this);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("app_version", versionCode + "");
        OkGo.getInstance().addCommonHeaders(httpHeaders);

    }

    /**
     * 获取app版本
     */
    private void getVersionCodeMethod() {
        versionCode = CommonUtils.getVersionCode(mContext);
    }

    /**
     * 初始化集合
     */
    private void initUnusualList() {
        ConstantInfo.UnusualGradleList = new ArrayList<>();
        ConstantInfo.UnusualGradleList.add("D1");
        ConstantInfo.UnusualGradleList.add("D2");
        ConstantInfo.UnusualGradleList.add("D3");

        ConstantInfo.ContainerList = new ArrayList<>();
        ConstantInfo.ContainerList.add(getString(R.string.text_tug));
        ConstantInfo.ContainerList.add(getString(R.string.text_container));
    }

    public static Context getContext() {
        return mContext;
    }

    public static void addActivity(BaseActivity activity) {
        if (activityList == null)
            activityList = new ArrayList<>();

        activityList.add(activity);
    }

    public static void finishActivity() {
        for (int i = 0; i < activityList.size() - 1; i++) {
            activityList.get(i).onFinish();
        }
    }

    public static void removeActivity(BaseActivity activity) {
        activityList.remove(activity);
    }
}
