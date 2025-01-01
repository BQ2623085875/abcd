package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.util.DensityUtils;

/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class AlertMessageDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private TextView mTvTitle ,// 标题
            mTvContent;//内容
    private TextView mBtnNeglect,//取消按钮
            mBtnReceive;//确定按钮

    private static AlertMessageDialog alertDialog ;

    public static AlertMessageDialog getInstance(Context mContext) {
        return new AlertMessageDialog(mContext);
    }

    public static AlertMessageDialog getSingleInstance(Context mContext) {
        if(alertDialog == null)
            alertDialog = new AlertMessageDialog(mContext);
        return alertDialog;
    }

    private AlertMessageDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private AlertMessageDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_alert_message, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mTvTitle = view.findViewById(R.id.mTvTitle);
        mTvContent = view.findViewById(R.id.mTvContent);
        mBtnNeglect = view.findViewById(R.id.mBtnNeglect);
        mBtnReceive = view.findViewById(R.id.mBtnReceive);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        mTvContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( DensityUtils.getScreenWidth((Activity) mContext) / 2, DensityUtils.getScreenHeight((Activity) mContext) / 2));

        //初始化取消按钮
        setNeglectButton();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public AlertMessageDialog setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    /**
     * 设置内容
     *
     * @param resource
     * @return
     */
    public AlertMessageDialog setTitle(int resource) {
        mTvTitle.setText(mContext.getString(resource));
        return this;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public AlertMessageDialog setContent(String content) {
        mTvContent.setText(content);
        return this;
    }

    /**
     * 设置内容
     *
     * @param resource
     * @return
     */
    public AlertMessageDialog setContent(int resource) {
        mTvContent.setText(mContext.getString(resource));
        return this;
    }

    public AlertMessageDialog setBackgroundResource(int drawable){
        if(drawable != 0) {
            mLayoutBg.setBackground(mContext.getDrawable(drawable));
            mTvContent.setTextColor(mContext.getColor(R.color.white));
            mTvTitle.setTextColor(mContext.getColor(R.color.white));
        }else {
            mLayoutBg.setBackground(mContext.getDrawable(R.drawable.shape_white));
            mTvContent.setTextColor(mContext.getColor(R.color.black));
            mTvTitle.setTextColor(mContext.getColor(R.color.black));
        }
        return this ;
    }

    /**
     * 设置内容
     *
     * @param resource
     * @return
     */
    public AlertMessageDialog setContentResource(int resource) {
        mTvContent.setText(mContext.getString(resource));
        return this;
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public AlertMessageDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public AlertMessageDialog setReceiveButton(final OnClickListener listener) {
        mBtnReceive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public AlertMessageDialog setNeglectButton() {
        mBtnNeglect.setOnClickListener(new OnClickListener() {
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
    public AlertMessageDialog setNeglectButton(final OnClickListener listener) {
        mBtnNeglect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }
}
