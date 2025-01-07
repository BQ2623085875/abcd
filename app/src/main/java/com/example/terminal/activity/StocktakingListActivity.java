package com.example.terminal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.adapter.StocktakingListAdapter;
import com.example.terminal.adapter.StoreListAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.StocktakingListBean;
import com.example.terminal.bean.StoreListBean;
import com.example.terminal.global.Constant;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.util.ToastUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.model.HttpParams;

import java.util.ArrayList;
import java.util.List;

/**
 * 盘点管理--列表页面
 */
public class StocktakingListActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRvContainer;
    private RelativeLayout mRlContentNull;
    private TextView mTv_Return;

    private StocktakingListBean stocktakingListBean;
    private List<StocktakingListBean.RowsBean> rowsBeanList;

    private int pageNum = 1;
    private StocktakingListAdapter stocktakingListAdapter;

    @Override
    protected int setContentView() {
        return R.layout.activity_stocktaking_list;
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        mRvContainer = findViewById(R.id.mRvContainer);
        mRlContentNull = findViewById(R.id.mRlContentNull);
        mTv_Return = findViewById(R.id.mTv_Return);

    }

    @Override
    protected void initData() {
        mTv_Return.setOnClickListener(this::onClickSort);

        setRefreshListener(mRefreshLayout);

        rowsBeanList = new ArrayList<>();
        stocktakingListAdapter = new StocktakingListAdapter(mActivity, rowsBeanList);
        mRvContainer.setAdapter(stocktakingListAdapter);
        mRvContainer.setLayoutManager(new LinearLayoutManager(mActivity));

        stocktakingListAdapter.setOnItemClickListener(new StocktakingListItemClickListener());

        mRefreshLayout.setEnableLoadmore(true);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                pageNum = 1;
                initConnect();
                mRefreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageNum++;
                initConnect2();
                mRefreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    protected void initConnect() {

        valueParams = new HttpParams();
        valueParams.put(Constant.PAGENUM, pageNum);
        valueParams.put(Constant.PAGESIZE, 10);

        NetworkUtils.getStocktakingList(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                stocktakingListBean = (StocktakingListBean) body;

                rowsBeanList.clear();
                rowsBeanList.addAll(stocktakingListBean.getRows());

                mRlContentNull.setVisibility(rowsBeanList == null || rowsBeanList.size() == 0 ? View.VISIBLE : View.GONE);
                mRefreshLayout.setVisibility(rowsBeanList == null || rowsBeanList.size() == 0 ? View.GONE : View.VISIBLE);

                stocktakingListAdapter.notifyDataSetChanged();
            }
        }, valueParams);
    }

    private void initConnect2() {

        valueParams = new HttpParams();
        valueParams.put(Constant.PAGENUM, pageNum);
        valueParams.put(Constant.PAGESIZE, 10);

        NetworkUtils.getStocktakingList(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                stocktakingListBean = (StocktakingListBean) body;

                if (stocktakingListBean.getTotal() > rowsBeanList.size()) {
                    rowsBeanList.addAll(stocktakingListBean.getRows());

                    stocktakingListAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast("加载完毕");
                }
            }
        }, valueParams);
    }


    /**
     * 条目点击事件
     */
    private class StocktakingListItemClickListener extends OnItemClickListener {
        @Override
        public void onClick(View view, int position) {
            mIntent = new Intent(mActivity, StocktakingDetailsActivity.class);
            mBundle = new Bundle();
            mBundle.putInt(Constant.ID, rowsBeanList.get(position).getId());
            mBundle.putString(Constant.INFO, rowsBeanList.get(position).getName());
            mIntent.putExtras(mBundle);
            turnActForResult(mIntent);
        }
    }

    public void onClickSort(View view) {
        switch (view.getId()) {
            case R.id.mTv_Return:
                onFinish();
                break;
        }
    }


}