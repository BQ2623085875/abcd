package com.example.terminal.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseFragment;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.MainDrawerListener;
import com.example.terminal.receiver.NetPowerBroadcastReceiver;

import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ProgressUtils;
import com.example.terminal.view.dialog.AlertDialog;
import com.example.terminal.view.dialog.MessageDialog;
import com.example.terminal.view.dialog.UpdateDialog;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void initData() {

    }

    @Override
    protected void initConnect() {

    }


    @Override
    protected void initRefresh(int count) {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        if (CommonUtils.isFastDoubleClick())
            return;
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}