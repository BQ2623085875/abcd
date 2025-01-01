package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.view.SignView;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.util.DensityUtils;

import java.io.File;


/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class SignAlertDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private SignView mSignContent;//内容
    private TextView mBtnCancel,//取消按钮
            mBtnReset ,//重置
            mBtnConfirm;//确定按钮

    public static SignAlertDialog getInstance(Context mContext) {
        return new SignAlertDialog(mContext);
    }

    private SignAlertDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private SignAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sign_alert, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mSignContent = view.findViewById(R.id.mSignContent);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnReset = view.findViewById(R.id.mBtnReset);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( 9*DensityUtils.getScreenWidth((Activity) mContext) / 10, 9*DensityUtils.getScreenHeight((Activity) mContext) / 10));

        //初始化取消按钮
        setCancelButton();
        //点击空白处可消失
        setCancelable(false);
        setResetBitmap();

        return this;
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public SignAlertDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public SignAlertDialog setConfirmButton(final OnConfirmClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.getFile(mSignContent.getPanelFile());
            }
        });
        return this;
    }

    private SignAlertDialog setResetBitmap(){
        mBtnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignContent.clearPanel();
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public SignAlertDialog setCancelButton() {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public SignAlertDialog setCancelButton(final OnClickListener listener) {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }

    public interface OnConfirmClickListener {
        void getFile(File signFile);
    }
}
