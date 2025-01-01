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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.NFCContentAdapter;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.listener.GridItemDecoration;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;

import java.util.List;

/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class NFCContentDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private RecyclerView mRlStoreContainer;//区域
    private Button mBtnCancel,//取消按钮
            mBtnResetting ,//重置
            mBtnConfirm;//确定按钮

    private NFCContentAdapter nfcContentAdapter ;
    private List<String> nfcDataList ;
    private int mSelectPosition = -1;

    public static NFCContentDialog getInstance(Context mContext , List<String> mList) {
        return new NFCContentDialog(mContext , mList);
    }

    private NFCContentDialog(Context mContext , List<String> mList) {
        this.mContext = mContext;
        this.nfcDataList = mList ;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private NFCContentDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_storeage_area, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mRlStoreContainer = view.findViewById(R.id.mRlStoreContainer);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnResetting = view.findViewById(R.id.mBtnResetting);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( 4 * DensityUtils.getScreenWidth((Activity) mContext) / 5, 4 * DensityUtils.getScreenHeight((Activity) mContext) / 5));

        initAdapter();

        //初始化取消按钮
        setButtonClickListener();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    private void initAdapter(){
        if(nfcContentAdapter == null) {
            nfcContentAdapter = new NFCContentAdapter(mContext, nfcDataList , mSelectPosition);
            mRlStoreContainer.setAdapter(nfcContentAdapter);
            mRlStoreContainer.setLayoutManager(new GridLayoutManager(mContext, 4));
            mRlStoreContainer.addItemDecoration(new GridItemDecoration(new GridItemDecoration.Builder(mContext)));

            nfcContentAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    mSelectPosition = position ;
                    initAdapter();
                }
            });
        }else {
            nfcContentAdapter.updateAdapter(mSelectPosition);
        }
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public NFCContentDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public NFCContentDialog setConfirmButton(final OnStorageOnClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectPosition == -1){
                    ToastUtils.showToast(R.string.toast_please_content);
                }else {
                    listener.onClick(mSelectPosition);
                    mDialog.dismiss();
                }

            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public NFCContentDialog setButtonClickListener() {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        mBtnResetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPosition = -1 ;

                initAdapter();
            }
        });

        return this;
    }

    public interface OnStorageOnClickListener {
        void onClick(int position);
    }
}
