package com.example.terminal.listener.RecyclerView;

/**
 * @author : YanKing
 * @date : 2019/3/21  10:34
 * @Description : RecyclerView 的刷新加载
 */
public abstract class RefreshListener {

    /**
     * 下拉刷新
     */
    protected abstract void onRefresh();

    /**
     * 上拉加载
     */
    protected void onLoadMore(){}
}
