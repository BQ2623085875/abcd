package com.example.terminal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.adapter.OutboundListDetailsAdapter;
import com.example.terminal.adapter.StoreListDetailsAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.OutboudListDetailsBean;
import com.example.terminal.bean.StoreListDetailsBean;
import com.example.terminal.global.Constant;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnItemClickListener;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

/**
 * 出库办理--详情页面
 */
public class OutboundDetailsActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRvContainer;
    private RelativeLayout mRlContentNull;
    private LinearLayout mLl_pleaseSelect;
    private TextView mTv_return,
            mTv_Save,
            mTv_Submit,
            mTv_pleaseSelect,
            mTv_Titlelibrary;

    private int mId;
    private String mStoreHouseName;

    private OutboudListDetailsBean outboudListDetailsBean;
    private List<OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean> tstoregeOutDeviceList;
    private OutboundListDetailsAdapter outboundListDetailsAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_outbound_details;
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        mRvContainer = findViewById(R.id.mRvContainer);
        mRlContentNull = findViewById(R.id.mRlContentNull);
        mTv_return = findViewById(R.id.mTv_Return);//返回
        mTv_Save = findViewById(R.id.mTv_Save);//保存草稿
        mTv_Submit = findViewById(R.id.mTv_Submit);//提交
        mTv_pleaseSelect = findViewById(R.id.mTv_pleaseSelect);//选择
        mTv_Titlelibrary = findViewById(R.id.mTv_Titlelibrary);
        mLl_pleaseSelect = findViewById(R.id.mLl_pleaseSelect);

    }

    @Override
    protected void initData() {
        mId = mBundle.getInt(Constant.ID);
        mStoreHouseName = mBundle.getString(Constant.INFO);

        mTv_Titlelibrary.setText(mStoreHouseName);

        mTv_return.setOnClickListener(this::onClickSort);
        mTv_Save.setOnClickListener(this::onClickSort);
        mTv_Submit.setOnClickListener(this::onClickSort);
        mLl_pleaseSelect.setOnClickListener(this::onClickSort);
    }

    @Override
    protected void initConnect() {

        NetworkUtils.getOutboundDetails(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                outboudListDetailsBean = (OutboudListDetailsBean) body;

                tstoregeOutDeviceList = outboudListDetailsBean.getData().getTstoregeOutDeviceList();

                initAdapter();
            }
        }, mId);
    }

    private void initAdapter() {
        if (outboudListDetailsBean == null)
            return;


        mRlContentNull.setVisibility(tstoregeOutDeviceList == null || tstoregeOutDeviceList.size() == 0 ? View.VISIBLE : View.GONE);
        mRefreshLayout.setVisibility(tstoregeOutDeviceList == null || tstoregeOutDeviceList.size() == 0 ? View.GONE : View.VISIBLE);


        if (outboundListDetailsAdapter == null) {
            outboundListDetailsAdapter = new OutboundListDetailsAdapter(mActivity, tstoregeOutDeviceList);
            mRvContainer.setAdapter(outboundListDetailsAdapter);
            mRvContainer.setLayoutManager(new LinearLayoutManager(mActivity));

            outboundListDetailsAdapter.setOnItemClickListener(new DetailsItemClickListener());
        } else {
            outboundListDetailsAdapter.updateAdapter(tstoregeOutDeviceList);
        }
    }

    /**
     * 条目点击事件
     */
    private class DetailsItemClickListener extends OnItemClickListener {
        @Override
        public void onClick(View view, int position) {
            mIntent = new Intent(mActivity, OutboundScanActivity.class);
            mBundle = new Bundle();
            mBundle.putInt(Constant.TurnType, position);
            mBundle.putInt(Constant.ID, outboudListDetailsBean.getData().getId());
            mBundle.putSerializable(Constant.INFO, tstoregeOutDeviceList.get(position));
            mIntent.putExtras(mBundle);
            turnActForResult(mIntent);
        }
    }


    public void onClickSort(View view) {
        switch (view.getId()) {
            case R.id.mTv_Return:
                onFinish();
                break;
            case R.id.mTv_Save:

                break;
            case R.id.mTv_Submit:

                break;
            case R.id.mLl_pleaseSelect:

                break;
        }
    }
}