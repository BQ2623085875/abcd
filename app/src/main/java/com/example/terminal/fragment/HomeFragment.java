package com.example.terminal.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.activity.StoreListActivity;
import com.example.terminal.base.BaseFragment;

/**
 *
 */
public class HomeFragment extends BaseFragment {

    private TextView mTv_Store,//入库办理
            mTv_Outbound,//出库办理
            mTv_Stocktaking,//盘点管理
            mTv_Device;//设备实施

    @Override
    public int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mTv_Store = mView.findViewById(R.id.mTv_Store);
        mTv_Outbound = mView.findViewById(R.id.mTv_Outbound);
        mTv_Stocktaking = mView.findViewById(R.id.mTv_Stocktaking);
        mTv_Device = mView.findViewById(R.id.mTv_Device);
    }

    @Override
    public void initData() {

        mTv_Store.setOnClickListener(this::onClickSort);
        mTv_Outbound.setOnClickListener(this::onClickSort);
        mTv_Stocktaking.setOnClickListener(this::onClickSort);
        mTv_Device.setOnClickListener(this::onClickSort);
    }

    public void onClickSort(View view) {
        switch (view.getId()) {
            case R.id.mTv_Store:
                turnToAct(StoreListActivity.class,false);
                break;
            case R.id.mTv_Outbound:
                mTv_Outbound.setText("2");
                break;
            case R.id.mTv_Stocktaking:
                mTv_Stocktaking.setText("3");
                break;
            case R.id.mTv_Device:
                mTv_Device.setText("4");
                break;
        }
    }
}