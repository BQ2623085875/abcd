package com.example.terminal.view.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.UpdateBean.UpdateData;
import com.example.terminal.global.Constant;
import com.example.terminal.service.DownAPKService;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class UpdateDialog extends BaseDialog {

    private LinearLayout mLayoutBg;
    private TextView mTvContent;//版本名

    private Button mBtnCancel ,
            mBtnConfirm ;
    private UpdateData updateData;

    public static UpdateDialog getInstance(Context mContext) {
        return new UpdateDialog(mContext);
    }

    private UpdateDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
    }

    public UpdateDialog builder(UpdateData commonBean) {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_update, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mTvContent = view.findViewById(R.id.mTvContent);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( DensityUtils.getScreenWidth((Activity) mContext) / 2, DensityUtils.getScreenHeight((Activity) mContext) / 2));

        //点击空白处可消失
        mDialog.setCancelable(false);

        this.updateData = commonBean ;

        mTvContent.setText("当前有新版本 " + updateData.getVersionName() + " ，是否确认升级？");

        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
                initApkDownload();
            }
        });

        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });

        return this;
    }

    private void initApkDownload() {
        if(!XXPermissions.isHasPermission(mContext , Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            XXPermissions.with((BaseActivity) mContext)
                    .constantRequest()
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE)
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            startServiceDownload();
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            if (quick) {
                                //如果是被永久拒绝就跳转到应用权限系统设置页面
                                XXPermissions.gotoPermissionSettings(mContext);
                            } else {
                                ToastUtils.showToast(mContext.getString(R.string.no_permission));
                            }
                        }
                    });
        }else{
            startServiceDownload();
        }
    }

    private void startServiceDownload(){
        if(TextUtils.isEmpty(updateData.getApkFileUrl())){
            ToastUtils.showToast(R.string.toast_data_error);
            return;
        }

        Intent intent = new Intent(mContext , DownAPKService.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.INFO , updateData.getApkFileUrl());
        bundle.putString(Constant.VersionName , updateData.getVersionName());
        intent.putExtras(bundle);
        mContext.startService(intent);
    }

    public void show() {
        mDialog.show();
        mDialog.setOnKeyListener(new BackOnKeyListener());//设置返回键
    }

    /**
     * 点击物理返回按钮
     */
    public class BackOnKeyListener implements DialogInterface.OnKeyListener {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                ToastUtils.showToast(mContext.getString(R.string.toast_not_back));
            }
            return false;
        }
    }
}
