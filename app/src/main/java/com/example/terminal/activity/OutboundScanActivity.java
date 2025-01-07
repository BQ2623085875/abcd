package com.example.terminal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.adapter.OutboundScanAdapter;
import com.example.terminal.adapter.StoreScanAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.OutboudListDetailsBean;
import com.example.terminal.bean.OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean;
import com.example.terminal.bean.StoreListDetailsBean;
import com.example.terminal.global.Constant;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnTextClickListener;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;
import com.example.terminal.view.CustomCircleProgress;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

/**
 * 出库办理--扫描页面
 */
public class OutboundScanActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRvContainer;
    private RelativeLayout mRlContentNull;
    private CompoundBarcodeView barcodeView;
    private CustomCircleProgress mIv_progress;
    private TextView mTv_titleBar, mTv_deviceName,
            mTv_typeColumn,
            mTv_Model,
            mTv_Supplier,
            mTv_Return;

    private TstoregeOutDeviceListBean tstoregeOutDeviceListBean;

    private int mPosition;
    private int mId;

    private OutboudListDetailsBean outboudListDetailsBean;
    private List<TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean> tstoregeOutDeviceDetailList;
    private OutboundScanAdapter outboundScanAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_outbound_scan;
    }

    @Override
    protected void initView() {
        mTv_titleBar = findViewById(R.id.mTv_TitleBar);
        mTv_deviceName = findViewById(R.id.mTv_deviceName);
        mTv_typeColumn = findViewById(R.id.mTv_typeColumn);
        mTv_Model = findViewById(R.id.mTv_Model);
        mTv_Supplier = findViewById(R.id.mTv_Supplier);

        mTv_Return = findViewById(R.id.mTv_Return);

        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        mRvContainer = findViewById(R.id.mRvContainer);
        mRlContentNull = findViewById(R.id.mRlContentNull);

        barcodeView = findViewById(R.id.barcodeView);

        mIv_progress = findViewById(R.id.mIv_progress);
    }

    @Override
    protected void initData() {
        //启动扫描
        // 检查相机权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, Constant.REQUEST_CODE);
        } else {
            // 如果有权限，直接启动扫描
            StartScan();
        }


        mPosition = mBundle.getInt(Constant.TurnType);
        mId = mBundle.getInt(Constant.ID);
        tstoregeOutDeviceListBean = (TstoregeOutDeviceListBean) mBundle.getSerializable(Constant.INFO);

        if (tstoregeOutDeviceListBean != null) {
            mTv_titleBar.setText(tstoregeOutDeviceListBean.getDeviceName());
            mTv_deviceName.setText(tstoregeOutDeviceListBean.getDeviceName());
            mTv_Model.setText(tstoregeOutDeviceListBean.getDeviceType());
            mTv_Supplier.setText(tstoregeOutDeviceListBean.getDeviceCompany());
            mIv_progress.increaseProgress(tstoregeOutDeviceListBean.getDeviceNum());
        }

        mTv_Return.setOnClickListener(this::onClickSort);
    }

    private void StartScan() {
        barcodeView.decodeContinuous(callback);
    }

    @Override
    protected void initConnect() {

        NetworkUtils.getOutboundDetails(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                outboudListDetailsBean = (OutboudListDetailsBean) body;

                tstoregeOutDeviceDetailList = outboudListDetailsBean.getData().getTstoregeOutDeviceList().get(mPosition).getTstoregeOutDeviceDetailList();

                initAdapter();
            }
        }, mId);
    }

    private void initAdapter() {
        if (tstoregeOutDeviceDetailList == null)
            return;


        mRlContentNull.setVisibility(tstoregeOutDeviceDetailList == null || tstoregeOutDeviceDetailList.size() == 0 ? View.VISIBLE : View.GONE);
        mRefreshLayout.setVisibility(tstoregeOutDeviceDetailList == null || tstoregeOutDeviceDetailList.size() == 0 ? View.GONE : View.VISIBLE);


        if (outboundScanAdapter == null) {
            outboundScanAdapter = new OutboundScanAdapter(mActivity, tstoregeOutDeviceDetailList);
            mRvContainer.setAdapter(outboundScanAdapter);
            mRvContainer.setLayoutManager(new LinearLayoutManager(mActivity));

            outboundScanAdapter.setOnTextClickListener(mItemTextClickListener);
        } else {
            outboundScanAdapter.updateAdapter(tstoregeOutDeviceDetailList);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被授予，执行相应操作
                    StartScan();
                } else {
                    // 权限被拒绝，执行相应操作
                    ToastUtils.showToast("用户拒绝访问相机权限");
                }
            }
        }
    }


    public void onClickSort(View view) {
        switch (view.getId()) {
            case R.id.mTv_Return:
                onFinish();
                break;
        }
    }

    private BarcodeCallback callback = new BarcodeCallback() {

        @Override
        public void barcodeResult(BarcodeResult result) {
            ToastUtils.showToast(result.getText().toString());
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }
}