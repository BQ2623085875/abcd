package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.OutboundAndInboundStatisticsPopUpBoxAdapter;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.LeavePortWarehousingDetailBean.WarehousingDetailData.WarehousingMainInfoBean;
import com.example.terminal.util.DensityUtils;

import java.util.List;


/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 出港入库统计弹框
 */
public class OutboundAndInboundStatisticsPopUpBoxAlertDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private TextView tv_oddNumbers;//单号
    private TextView tv_destinationStation;//目的站
    private TextView tv_Total;//总件数
    private TextView tv_TotalWeight;//总重量
    private RecyclerView Rview;
    private Button mBtnCancel,//取消按钮
            mBtnConfirm;//确定按钮

    private static OutboundAndInboundStatisticsPopUpBoxAlertDialog alertDialog;

    public static OutboundAndInboundStatisticsPopUpBoxAlertDialog getInstance(Context mContext) {
        if (alertDialog == null)
            alertDialog = new OutboundAndInboundStatisticsPopUpBoxAlertDialog(mContext);
        return new OutboundAndInboundStatisticsPopUpBoxAlertDialog(mContext);
    }

    private OutboundAndInboundStatisticsPopUpBoxAlertDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private OutboundAndInboundStatisticsPopUpBoxAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_outbound, null);

        // 获取自定义Dialog布局中的控件

        Rview = view.findViewById(R.id.Rview);
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        tv_oddNumbers = view.findViewById(R.id.tv_oddNumbers);
        tv_destinationStation = view.findViewById(R.id.tv_destinationStation);
        tv_Total = view.findViewById(R.id.tv_Total);
        tv_TotalWeight = view.findViewById(R.id.tv_TotalWeight);


        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        int screenHeight = DensityUtils.getScreenHeight((Activity) mContext);
        if (800 <= screenHeight) {
            mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams(DensityUtils.getScreenWidth((Activity) mContext) / 2, DensityUtils.getScreenHeight((Activity) mContext) / 2));
        } else {
            mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams(DensityUtils.getScreenWidth((Activity) mContext) / 2, DensityUtils.getScreenHeight((Activity) mContext) - 100));
        }

        //初始化取消按钮
        setCancelButton();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    /**
     * 设置内容
     *
     * @param
     * @return
     */
    public OutboundAndInboundStatisticsPopUpBoxAlertDialog setContent(List<WarehousingMainInfoBean> mListString, String OddNumber, String DestinationStation, String Total, String TotalWeight) {
        tv_oddNumbers.setText(OddNumber);//单号
        tv_destinationStation.setText(DestinationStation);//目的站
        tv_Total.setText(Total);//总件数
        tv_TotalWeight.setText(TotalWeight);//总重量
        if (mListString != null && mListString.size() > 0) {
            OutboundAndInboundStatisticsPopUpBoxAdapter popUpBoxAdapter = new OutboundAndInboundStatisticsPopUpBoxAdapter(mContext, mListString);
            Rview.setAdapter(popUpBoxAdapter);
            Rview.setLayoutManager(new LinearLayoutManager(mContext));
        }
        return this;
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public OutboundAndInboundStatisticsPopUpBoxAlertDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public OutboundAndInboundStatisticsPopUpBoxAlertDialog setConfirmButton(final OnClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }


    public OutboundAndInboundStatisticsPopUpBoxAlertDialog setSingleButton() {
        mBtnConfirm.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public OutboundAndInboundStatisticsPopUpBoxAlertDialog setCancelButton() {
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
    public OutboundAndInboundStatisticsPopUpBoxAlertDialog setCancelButton(final OnClickListener listener) {
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
