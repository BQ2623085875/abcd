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
import com.example.terminal.adapter.StorageAreaAdapter;
import com.example.terminal.bean.StorageAreaBean;
import com.example.terminal.bean.StorageAreaBean.StorageAreaData;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.GridItemDecoration;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class StorageAreaDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private RecyclerView mRlStoreContainer;//区域
    private Button mBtnCancel,//取消按钮
            mBtnResetting ,//重置
            mBtnConfirm;//确定按钮

    private List<StorageAreaData> mStorageList;
    private StorageAreaAdapter storageAdapter ;

    private boolean IsSingleSelect;
    private int WarehouseID ;

    private StorageAreaData selectedArea ;
    private List<StorageAreaData> selectedAreaList ;

    public static StorageAreaDialog getInstance(Context mContext , int warehouseID) {
        return new StorageAreaDialog(mContext , warehouseID);
    }

    private StorageAreaDialog(Context mContext , int warehouseID) {
        this.mContext = mContext;
        this.WarehouseID = warehouseID ;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private StorageAreaDialog builder() {
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

        // 获取数据
        getStorageAreaData();

        //初始化取消按钮
        setButtonClickListener();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    public StorageAreaDialog setSingleSelect(boolean isSingleSelect){
        IsSingleSelect = isSingleSelect;
        return this ;
    }

    private StorageAreaDialog getStorageAreaData(){
        NetworkUtils.getStorageAreaList(new OkGoBackListener((Activity) mContext) {
            @Override
            public void onSuccess(Object body) {
                mStorageList = (List<StorageAreaData>) body;

                if (mStorageList != null && mStorageList.size() > 0) {
                    // 根据序号排序 小到大
                    Collections.sort(mStorageList, new Comparator<StorageAreaData>() {
                        @Override
                        public int compare(StorageAreaBean.StorageAreaData o1, StorageAreaBean.StorageAreaData o2) {
                            return Integer.compare(o1.getSort(), o2.getSort());
                        }
                    });
                }

                initSelected();

                initAdapter();
            }
        } , WarehouseID);
        return this ;
    }

    public StorageAreaDialog setSelectedArea(StorageAreaData selectedArea){
        this.selectedArea = selectedArea ;
        return this;
    }

    public StorageAreaDialog setSelectedArea(List<StorageAreaData> selectedAreaList){
        this.selectedAreaList = selectedAreaList ;
        return this;
    }

    /**
     * 初始化选择
     */
    private void initSelected(){
        if(mStorageList == null)
            return;

        if(selectedArea != null)
            for (int i = 0; i < mStorageList.size(); i++) {
                if(mStorageList.get(i).getId() == selectedArea.getId()){
                    mStorageList.get(i).setSelected(true);
                    continue;
                }
            }

        if(selectedAreaList != null && selectedAreaList.size() != 0)
            for (int i = 0; i < mStorageList.size(); i++) {
                for (int j = 0; j < selectedAreaList.size(); j++) {
                    if(mStorageList.get(i).getId() == selectedAreaList.get(j).getId()){
                        mStorageList.get(i).setSelected(true);
                        continue;
                    }
                }
            }
    }

    private void initAdapter(){
        if(storageAdapter == null) {
            storageAdapter = new StorageAreaAdapter(mContext, mStorageList);
            mRlStoreContainer.setAdapter(storageAdapter);
            mRlStoreContainer.setLayoutManager(new GridLayoutManager(mContext, 4));
            mRlStoreContainer.addItemDecoration(new GridItemDecoration(new GridItemDecoration.Builder(mContext)));

            storageAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    List<StorageAreaData> storageAreaBeanList = new ArrayList<>();
                    for (int i = 0; i < mStorageList.size(); i++) {
                        StorageAreaData storageAreaBean = mStorageList.get(i);
                        if(position == i){
                            storageAreaBean.setSelected(!storageAreaBean.isSelected());
                        }else {
                            storageAreaBean.setSelected(IsSingleSelect ? false : storageAreaBean.isSelected());
                        }
                        storageAreaBeanList.add(storageAreaBean);
                    }
                    initAdapter();
                }
            });
        }else {
            storageAdapter.updateAdapter(mStorageList);
        }
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public StorageAreaDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public StorageAreaDialog setConfirmButton(final OnStorageOnClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<StorageAreaData> storageAreaBeans = new ArrayList<>();
                for (int i = 0; i < mStorageList.size(); i++) {
                    if(mStorageList.get(i).isSelected())
                        storageAreaBeans.add(mStorageList.get(i));
                }

                if(storageAreaBeans.size() == 0 && IsSingleSelect){
                    ToastUtils.showToast(R.string.toast_please_select_area);
                }else {
                    listener.onClick(storageAreaBeans);
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
    public StorageAreaDialog setButtonClickListener() {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        mBtnResetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mStorageList.size(); i++) {
                    mStorageList.get(i).setSelected(false);
                }

                initAdapter();
            }
        });

        return this;
    }

    public interface OnStorageOnClickListener {
        void onClick(List<StorageAreaData> storageAreaList);
    }
}
