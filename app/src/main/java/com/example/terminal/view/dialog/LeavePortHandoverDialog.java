package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.FileBean;
import com.example.terminal.bean.HandoverBean.HandoverData;
import com.example.terminal.global.Constant;
import com.example.terminal.http.GlideLoader;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.HandoverConfirmListener;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.JsonUtils;
import com.example.terminal.util.TimerPickerUtils;
import com.example.terminal.util.ToastUtils;
import com.example.terminal.view.SignView;
import com.example.terminal.view.edit.EditorActivity;

import java.util.Date;

/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 出港交接
 */
public class LeavePortHandoverDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private Button mBtnCancel,//取消按钮
            mBtnReset ,//重签
            mBtnConfirm;//确定按钮
    private TextView mTvContainerCount ,//货物总斗数
            mTvTransportTime ;//拉运时间
    private CheckBox mCbConfirmCheck ,//拉运前四确认检查
            mCbPreventionControl ;//拉运途中安全防控
    private SignView mSignContent ;//签字
    private ImageView mIvSignContent ;

    private boolean IsEditor ;
    private HandoverData mHandoverBean ;

    private static LeavePortHandoverDialog alertDialog ;

    public static LeavePortHandoverDialog getInstance(Context mContext) {
        if(alertDialog == null)
            alertDialog = new LeavePortHandoverDialog(mContext);
        return new LeavePortHandoverDialog(mContext);
    }

    private LeavePortHandoverDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private LeavePortHandoverDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_leave_port_handover, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnReset = view.findViewById(R.id.mBtnReset);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        mTvContainerCount = view.findViewById(R.id.mTvContainerCount);
        mTvTransportTime = view.findViewById(R.id.mTvTransportTime);
        mCbConfirmCheck = view.findViewById(R.id.mCbConfirmCheck);
        mCbPreventionControl = view.findViewById(R.id.mCbPreventionControl);
        mSignContent = view.findViewById(R.id.mSignContent);
        mIvSignContent = view.findViewById(R.id.mIvSignContent);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( 9 * DensityUtils.getScreenWidth((Activity) mContext) / 10, 9 * DensityUtils.getScreenHeight((Activity) mContext) / 10));

        //初始化取消按钮
        setCancelButton();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    public class OnItemClickListener implements OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.mBtnReset :
                    // 重签
                    mSignContent.clearPanel();
                    mIvSignContent.setVisibility(View.GONE);
                    mSignContent.setVisibility(View.VISIBLE);
                    break;
                case R.id.mTvContainerCount :
                    // 拖斗数量
                    EditorActivity.openActivity((BaseActivity) mContext, new EditorActivity.EditorCallbackListener() {
                        @Override
                        public void onSubmit(String content) {
                            mHandoverBean.setTransportCnt(TextUtils.isEmpty(content) ? 0 : Integer.parseInt(content));
                            mTvContainerCount.setText(content);
                        }
                    } , String.valueOf(mHandoverBean.getTransportCnt()));
                    break;
                case R.id.mTvTransportTime:
                    // 拉运时间
                    TimerPickerUtils.showTimePickerView(mContext, !TextUtils.isEmpty(mHandoverBean.getDeliveryTime()) ? mHandoverBean.getDeliveryTime() : CommonUtils.getTime(new Date()), new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            mHandoverBean.setDeliveryTime(CommonUtils.getTime(date));
                            mTvTransportTime.setText(CommonUtils.getTime(date));
                        }
                    }).show();
                    break;
            }
        }
    }

    /**
     * 获取数值
     * @param handoverBean
     * @return
     */
    public LeavePortHandoverDialog setData(HandoverData handoverBean , boolean IsEditor){
        this.IsEditor = IsEditor ;
        mHandoverBean = handoverBean ;

        initInfo();

        return this ;
    }

    private void initInfo() {
        // 根据是否可编辑设置是否隐藏部分功能
        mTvContainerCount.setClickable(IsEditor ? true : false);
        mTvTransportTime.setClickable(IsEditor ? true : false);
        mCbConfirmCheck.setClickable(IsEditor ? true : false);
        mCbPreventionControl.setClickable(IsEditor ? true : false);
        mBtnConfirm.setVisibility(IsEditor ? View.VISIBLE : View.GONE);
        mBtnReset.setVisibility(IsEditor ? View.VISIBLE : View.GONE);

        mIvSignContent.setVisibility((!TextUtils.isEmpty(mHandoverBean.getUrl()) || !IsEditor) ? View.VISIBLE : View.GONE);
        mSignContent.setVisibility((TextUtils.isEmpty(mHandoverBean.getUrl()) && IsEditor) ? View.VISIBLE : View.GONE);

        // 回显内容
        mTvContainerCount.setText(String.valueOf(mHandoverBean.getTransportCnt()));
        mTvTransportTime.setText(mHandoverBean.getDeliveryTime());
        mCbConfirmCheck.setChecked(mHandoverBean.getCheckState() == 1);
        mCbPreventionControl.setChecked(mHandoverBean.getSafeState() == 1);

        GlideLoader.loader(mHandoverBean.getUrl() , mIvSignContent);

        if(IsEditor) {
            mBtnReset.setOnClickListener(new OnItemClickListener());
            mTvContainerCount.setOnClickListener(new OnItemClickListener());
            mTvTransportTime.setOnClickListener(new OnItemClickListener());
        }
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public LeavePortHandoverDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public LeavePortHandoverDialog setConfirmButton(final HandoverConfirmListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mTvContainerCount.getText().toString())){
                    ToastUtils.showToast(R.string.toast_container_number_null);
                    return;
                }

                if(TextUtils.isEmpty(mTvTransportTime.getText().toString())){
                    ToastUtils.showToast(R.string.toast_transport_time_null);
                    return;
                }

                mHandoverBean.setTransportCnt(Integer.parseInt(mTvContainerCount.getText().toString()));
                mHandoverBean.setDeliveryTime(mTvTransportTime.getText().toString());
                mHandoverBean.setCheckState(mCbConfirmCheck.isChecked() ? 1 : 0);
                mHandoverBean.setSafeState(mCbPreventionControl.isChecked() ? 1 : 0);

                if(mSignContent.getPanelFile() == null){
                    saveHandoverData(listener);
                    return;
                }

                NetworkUtils.uploadFile(new OkGoBackListener((BaseActivity) mContext) {
                    @Override
                    public void onSuccess(Object body) {
                        FileBean mUploadFileData = (FileBean) body;

                        mHandoverBean.setUrl(mUploadFileData.getUrl());
                        mHandoverBean.setFileId(mUploadFileData.getFileId());
                        mHandoverBean.setFileName(mUploadFileData.getName());

                        saveHandoverData(listener);
                    }
                }, mSignContent.getPanelFile());
            }
        });
        return this;
    }


    /**
     * 上传交接信息
     * @param listener
     */
    private void saveHandoverData(HandoverConfirmListener listener){
        NetworkUtils.saveHandoverDara(new OkGoBackListener((BaseActivity)mContext) {
            @Override
            public void onSuccess(Object body) {
                ToastUtils.showToast(R.string.toast_submit_success);
                mDialog.dismiss();
                listener.onClick(Constant.CODE_SUCCESS);
            }
        }, JsonUtils.parseBeanToJson(mHandoverBean) , false);
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public LeavePortHandoverDialog setCancelButton() {
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
    public LeavePortHandoverDialog setCancelButton(final OnClickListener listener) {
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
