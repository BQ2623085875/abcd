package com.example.terminal.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.KeyEvent;

/**
 * @author : YanKing
 * @date : 2019/5/6  15:35
 * @Description : 弹框基类
 */

public abstract class BaseDialog {

    protected Context mContext;
    protected Dialog mDialog;
    protected Display mDisplay;

    /**
     * 设置必须要做的布局设置
     */
    protected void setLayout() {
    }

    public void show() {
        setLayout();
        if (mDialog != null && !mDialog.isShowing() && !((Activity) mContext).isFinishing()) {
            mDialog.show();
            mDialog.setOnKeyListener(new BackOnKeyListener());//设置返回键
        }
    }

    /**
     * 点击物理返回按钮
     */
    public class BackOnKeyListener implements DialogInterface.OnKeyListener {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mDialog.dismiss();
            }
            return false;
        }
    }

    public BaseDialog dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        return this;
    }

    /**
     * 是否为展示状态
     *
     * @return
     */
    public boolean isShowing() {
        return mDialog.isShowing();
    }
}
