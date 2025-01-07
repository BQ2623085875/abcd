package com.example.terminal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.adapter.OutboundScanAdapter;
import com.example.terminal.adapter.StocktakingScanAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.OutboudListDetailsBean;
import com.example.terminal.bean.StocktakingDetailsBean;
import com.example.terminal.bean.StocktakingDetailsBean.DataBean.TcheckDeviceListBean;
import com.example.terminal.global.Constant;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnTextClickListener;
import com.example.terminal.util.ToastUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

/**
 * 盘点管理--扫描页面
 */
public class StocktakingScanActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRvContainer;
    private RelativeLayout mRlContentNull;
    private TextView mTv_titleBar, mTv_deviceName, mTv_typeColumn, mTv_Model, mTv_Supplier;

    private int mPosition;
    private int mId;

    private StocktakingDetailsBean.DataBean.TcheckDeviceListBean tcheckDeviceListBean;
    private StocktakingDetailsBean stocktakingDetailsBean;
    private List<TcheckDeviceListBean.DeviceDetailListBean> deviceDetailList;
    private StocktakingScanAdapter stocktakingScanAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_stocktaking_scan;
    }

    @Override
    protected void initView() {
        mTv_titleBar = findViewById(R.id.mTv_TitleBar);
        mTv_deviceName = findViewById(R.id.mTv_deviceName);
        mTv_typeColumn = findViewById(R.id.mTv_typeColumn);
        mTv_Model = findViewById(R.id.mTv_Model);
        mTv_Supplier = findViewById(R.id.mTv_Supplier);

        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        mRvContainer = findViewById(R.id.mRvContainer);
        mRlContentNull = findViewById(R.id.mRlContentNull);
    }

    @Override
    protected void initData() {
        mPosition = mBundle.getInt(Constant.TurnType);
        mId = mBundle.getInt(Constant.ID);
        tcheckDeviceListBean = (StocktakingDetailsBean.DataBean.TcheckDeviceListBean) mBundle.getSerializable(Constant.INFO);

        if (tcheckDeviceListBean != null) {
            mTv_titleBar.setText(tcheckDeviceListBean.getDeviceName());
            mTv_deviceName.setText(tcheckDeviceListBean.getDeviceName());
            mTv_Model.setText(tcheckDeviceListBean.getDeviceType());
            mTv_Supplier.setText(tcheckDeviceListBean.getDeviceCompany());
        }
    }

    @Override
    protected void initConnect() {

        NetworkUtils.getStocktakingDetails(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                stocktakingDetailsBean = (StocktakingDetailsBean) body;

                deviceDetailList = stocktakingDetailsBean.getData().getTcheckDeviceList().get(mPosition).getDeviceDetailList();

                initAdapter();
            }
        }, mId);
    }

    private void initAdapter() {
        if (stocktakingDetailsBean == null)
            return;


        mRlContentNull.setVisibility(deviceDetailList == null || deviceDetailList.size() == 0 ? View.VISIBLE : View.GONE);
        mRefreshLayout.setVisibility(deviceDetailList == null || deviceDetailList.size() == 0 ? View.GONE : View.VISIBLE);


        if (stocktakingScanAdapter == null) {
            stocktakingScanAdapter = new StocktakingScanAdapter(mActivity, deviceDetailList);
            mRvContainer.setAdapter(stocktakingScanAdapter);
            mRvContainer.setLayoutManager(new LinearLayoutManager(mActivity));

            stocktakingScanAdapter.setOnTextClickListener(mItemTextClickListener);
        } else {
            stocktakingScanAdapter.updateAdapter(deviceDetailList);
        }
    }

    private OnTextClickListener mItemTextClickListener = new OnTextClickListener() {

        @Override
        public void onClick(View view, int position) {
            switch (view.getId()) {
                case R.id.mIv_delete:
                    ToastUtils.showToast("删除");
                    break;
            }
        }
    };
}