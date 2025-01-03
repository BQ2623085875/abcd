package com.example.terminal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.terminal.R;
import com.example.terminal.adapter.StoreListDetailsAdapter;
import com.example.terminal.adapter.StoreScanAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.StoreListDetailsBean;
import com.example.terminal.bean.StoreListDetailsBean.DataBean.TstoregeInDeviceBean;
import com.example.terminal.global.Constant;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnTextClickListener;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;
import com.example.terminal.view.zxing.QRActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.List;

/**
 * 入库办理--扫描页面
 */
public class StoreScanActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRvContainer;
    private RelativeLayout mRlContentNull;
    private TextView mTv_titleBar, mTv_deviceName, mTv_typeColumn, mTv_Model, mTv_Supplier;


    private TstoregeInDeviceBean tstoregeInDeviceBean;
    private StoreListDetailsBean storeListDetailsBean;
    private int mPosition;
    private int mId;
    private List<TstoregeInDeviceBean.tstoregeInDeviceDetailListBean> tstoregeInDeviceDetailList;
    private StoreScanAdapter storeScanAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_store_scan;
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

        findViewById(R.id.mLl_StoreScan).setLayoutParams(new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.getScreenHeight((Activity) mActivity) / 3));


        IntentIntegrator intentIntegrator = new IntentIntegrator(StoreScanActivity.this);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setCaptureActivity(QRActivity.class); // 设置自定义的activity是QRActivity
        intentIntegrator.setRequestCode(Constant.REQUEST_CODE);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(resultCode, data);
            final String qrContent = scanResult.getContents();
            Toast.makeText(StoreScanActivity.this, "扫描结果:" + qrContent, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        mPosition = mBundle.getInt(Constant.TurnType);
        mId = mBundle.getInt(Constant.ID);
        tstoregeInDeviceBean = (TstoregeInDeviceBean) mBundle.getSerializable(Constant.INFO);

        if (tstoregeInDeviceBean != null) {
            mTv_titleBar.setText(tstoregeInDeviceBean.getDeviceName());
            mTv_deviceName.setText(tstoregeInDeviceBean.getDeviceName());
            mTv_Model.setText(tstoregeInDeviceBean.getDeviceType());
            mTv_Supplier.setText(tstoregeInDeviceBean.getDeviceCompany());
        }
    }

    @Override
    protected void initConnect() {

        NetworkUtils.getStoreDetails(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                storeListDetailsBean = (StoreListDetailsBean) body;
                tstoregeInDeviceDetailList = storeListDetailsBean.getData().getTstoregeInDevice().get(mPosition).getTstoregeInDeviceDetailList();
                initAdapter();
            }
        }, mId);
    }

    private void initAdapter() {
        if (storeListDetailsBean == null)
            return;


        mRlContentNull.setVisibility(tstoregeInDeviceDetailList == null || tstoregeInDeviceDetailList.size() == 0 ? View.VISIBLE : View.GONE);
        mRefreshLayout.setVisibility(tstoregeInDeviceDetailList == null || tstoregeInDeviceDetailList.size() == 0 ? View.GONE : View.VISIBLE);


        if (storeScanAdapter == null) {
            storeScanAdapter = new StoreScanAdapter(mActivity, tstoregeInDeviceDetailList);
            mRvContainer.setAdapter(storeScanAdapter);
            mRvContainer.setLayoutManager(new LinearLayoutManager(mActivity));

            storeScanAdapter.setOnTextClickListener(mItemTextClickListener);
        } else {
            storeScanAdapter.updateAdapter(tstoregeInDeviceDetailList);
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