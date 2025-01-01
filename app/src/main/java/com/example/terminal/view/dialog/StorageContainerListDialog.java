package com.example.terminal.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.DialogStorageContainerListAdapter;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.EnterPortOrderBean;

import java.util.List;


public class StorageContainerListDialog extends BaseDialog implements View.OnClickListener {

    private TextView mContainerCode;
    private TextView mTvCloseBtn;
    private RecyclerView mRlStorageContainer;
    private ImageView mImgContentNull;
    private DialogStorageContainerListAdapter listAdapter;

    public StorageContainerListDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private void builder() {
        //获取dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_storage_container_list, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(mDisplay.getWidth());

        //获取view
        mContainerCode = view.findViewById(R.id.mContainerCode);
        mTvCloseBtn = view.findViewById(R.id.mTvCloseBtn);
        mTvCloseBtn.setOnClickListener(this);
        mRlStorageContainer = view.findViewById(R.id.mRlStorageContainer);
        mImgContentNull = view.findViewById(R.id.mImgContentNull);

        //定义dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public static StorageContainerListDialog getInstance(Context context) {
        return new StorageContainerListDialog(context);
    }

    public StorageContainerListDialog setSelectDataList(List<EnterPortOrderBean.EnterPortOrderData> data, String transportCode) {
        if (data == null || data.size() == 0) {
            mImgContentNull.setVisibility(View.VISIBLE);
            mRlStorageContainer.setVisibility(View.GONE);
        } else {
            mImgContentNull.setVisibility(View.GONE);
            mRlStorageContainer.setVisibility(View.VISIBLE);
        }

        mContainerCode.setText("#" + transportCode);

        initAdapter(data);
        return this;
    }

    private void initAdapter(List<EnterPortOrderBean.EnterPortOrderData> list) {
        if (listAdapter == null) {
            listAdapter = new DialogStorageContainerListAdapter(mContext, list);
            mRlStorageContainer.setAdapter(listAdapter);
            mRlStorageContainer.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        } else {
            listAdapter.updateAdapter(list);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvCloseBtn:
                mDialog.dismiss();
                break;
        }
    }
}
