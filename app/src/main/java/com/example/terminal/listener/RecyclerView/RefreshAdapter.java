package com.example.terminal.listener.RecyclerView;

import android.os.Handler;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * @author : YanKing
 * @date : 2019/3/21  10:32
 * @Description : RecyclerView 的刷新加载
 */
public class RefreshAdapter extends RefreshListenerAdapter {

    private RefreshListener listener ;

    public RefreshAdapter(RefreshListener listener){
        this.listener = listener ;
    }

    @Override
    public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishRefreshing();
                listener.onRefresh();
            }
        },1000);
    }

    @Override
    public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishLoadmore();
                listener.onLoadMore();
            }
        },1000);
    }
}
