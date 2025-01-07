package com.example.terminal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
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
import com.example.terminal.view.CustomCircleProgress;
import com.example.terminal.view.zxing.QRActivity;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
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
    private CompoundBarcodeView barcodeView;
    private CustomCircleProgress mIv_progress;
    private TextView mTv_titleBar,
            mTv_deviceName,
            mTv_typeColumn,
            mTv_Model,
            mTv_Supplier,
            mTv_Return;


    private TstoregeInDeviceBean tstoregeInDeviceBean;
    private StoreListDetailsBean storeListDetailsBean;

    private List<TstoregeInDeviceBean.tstoregeInDeviceDetailListBean> tstoregeInDeviceDetailList;

    private int mPosition;
    private int mId;

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

        mTv_Return = findViewById(R.id.mTv_Return);

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
        tstoregeInDeviceBean = (TstoregeInDeviceBean) mBundle.getSerializable(Constant.INFO);

        if (tstoregeInDeviceBean != null) {
            mTv_titleBar.setText(tstoregeInDeviceBean.getDeviceName());
            mTv_deviceName.setText(tstoregeInDeviceBean.getDeviceName());
            mTv_Model.setText(tstoregeInDeviceBean.getDeviceType());
            mTv_Supplier.setText(tstoregeInDeviceBean.getDeviceCompany());

            mIv_progress.increaseProgress(tstoregeInDeviceBean.getDeviceNum());
        }


        mTv_Return.setOnClickListener(this::onClickSort);
    }

    private void StartScan() {
        barcodeView.decodeContinuous(callback);
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