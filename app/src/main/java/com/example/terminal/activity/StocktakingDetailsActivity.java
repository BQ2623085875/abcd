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
import com.example.terminal.adapter.StocktakingDetailsAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.OutboudListDetailsBean;
import com.example.terminal.bean.StocktakingDetailsBean;
import com.example.terminal.global.Constant;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnItemClickListener;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

public class StocktakingDetailsActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRvContainer;
    private RelativeLayout mRlContentNull;
    private LinearLayout mLl_pleaseSelect;
    private TextView mTv_TitleBar;

    private int mId;
    private String mName;
    private StocktakingDetailsBean stocktakingDetailsBean;
    private List<StocktakingDetailsBean.DataBean.TcheckDeviceListBean> tcheckDeviceList;
    private StocktakingDetailsAdapter stocktakingDetailsAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_stocktaking_details;
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        mRvContainer = findViewById(R.id.mRvContainer);
        mRlContentNull = findViewById(R.id.mRlContentNull);
        mLl_pleaseSelect = findViewById(R.id.mLl_pleaseSelect);
        mTv_TitleBar = findViewById(R.id.mTv_TitleBar);

    }

    @Override
    protected void initData() {
        mId = mBundle.getInt(Constant.ID);
        mName = mBundle.getString(Constant.INFO);

        mTv_TitleBar.setText(mName);


    }


    @Override
    protected void initConnect() {

        NetworkUtils.getStocktakingDetails(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                stocktakingDetailsBean = (StocktakingDetailsBean) body;

                tcheckDeviceList = stocktakingDetailsBean.getData().getTcheckDeviceList();

                initAdapter();
            }
        }, mId);
    }

    private void initAdapter() {
        if (stocktakingDetailsBean == null)
            return;


        mRlContentNull.setVisibility(tcheckDeviceList == null || tcheckDeviceList.size() == 0 ? View.VISIBLE : View.GONE);
        mRefreshLayout.setVisibility(tcheckDeviceList == null || tcheckDeviceList.size() == 0 ? View.GONE : View.VISIBLE);


        if (stocktakingDetailsAdapter == null) {
            stocktakingDetailsAdapter = new StocktakingDetailsAdapter(mActivity, tcheckDeviceList);
            mRvContainer.setAdapter(stocktakingDetailsAdapter);
            mRvContainer.setLayoutManager(new LinearLayoutManager(mActivity));

            stocktakingDetailsAdapter.setOnItemClickListener(new DetailsItemClickListener());
        } else {
            stocktakingDetailsAdapter.updateAdapter(tcheckDeviceList);
        }
    }

    /**
     * 条目点击事件
     */
    private class DetailsItemClickListener extends OnItemClickListener {
        @Override
        public void onClick(View view, int position) {
            mIntent = new Intent(mActivity, StocktakingScanActivity.class);
            mBundle = new Bundle();
            mBundle.putInt(Constant.TurnType, position);
            mBundle.putInt(Constant.ID, stocktakingDetailsBean.getData().getId());
            mBundle.putSerializable(Constant.INFO, tcheckDeviceList.get(position));
            mIntent.putExtras(mBundle);
            turnActForResult(mIntent);
        }
    }
}