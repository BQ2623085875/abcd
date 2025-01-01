package com.example.terminal.view;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FixedHeightLayoutManager extends LinearLayoutManager {
    private int mHeight = -1;

    public FixedHeightLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        if (mHeight == -1) {
            mHeight = getHeight(); // 假设RecyclerView完全填充父容器的高度
        }

        int itemCount = state.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChild(view, 0, 0);
            int widthSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(mHeight, View.MeasureSpec.EXACTLY);
            view.measure(widthSpec, heightSpec);
            view.layout(0, 0, getWidth(), mHeight);
        }
    }
}