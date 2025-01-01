package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.util.DensityUtils;


/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class AlertDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private TextView mTvContent;//内容
    private Button mBtnCancel,//取消按钮
            mBtnConfirm;//确定按钮

    private static AlertDialog alertDialog;

    public static AlertDialog getInstance(Context mContext) {
        if (alertDialog == null)
            alertDialog = new AlertDialog(mContext);
        return new AlertDialog(mContext);
    }

    private AlertDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private AlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_alert, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mTvContent = view.findViewById(R.id.mTvContent);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        mTvContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams(DensityUtils.getScreenWidth((Activity) mContext) / 2, DensityUtils.getScreenHeight((Activity) mContext) / 2));

        //初始化取消按钮
        setCancelButton();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public AlertDialog setContent(String content) {
        mTvContent.setText(content);
        return this;
    }

    /**
     * 设置内容
     *
     * @param resource
     * @return
     */
    public AlertDialog setContent(int resource) {
        mTvContent.setText(mContext.getString(resource));
        return this;
    }

    /**
     * 设置内容
     *
     * @param resource
     * @return
     */
    public AlertDialog setContentResource(int resource) {
        mTvContent.setText(mContext.getString(resource));
        return this;
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public AlertDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public AlertDialog setConfirmButton(final OnClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConstantInfo.isReturn) {
                    if (ConstantInfo.IDS) {
                        NetworkUtils.securePageLock2(new OkGoBackListener((Activity) mContext) {
                            @Override
                            public void onSuccess(Object body) {
                                ConstantInfo.isReturn = false;
                                ConstantInfo.IDS = false;
                            }
                        }, ConstantInfo.ModuleID, ConstantInfo.StringID);
                    } else {
                        NetworkUtils.securePageLock(new OkGoBackListener((Activity) mContext) {
                            @Override
                            public void onSuccess(Object body) {
                                ConstantInfo.isReturn = false;
                                ConstantInfo.IDS = false;
                            }
                        }, ConstantInfo.ModuleID, ConstantInfo.ID);
                    }
                }
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }


    public AlertDialog setSingleButton() {
        mBtnConfirm.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public AlertDialog setCancelButton() {
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
    public AlertDialog setCancelButton(final OnClickListener listener) {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }
}
