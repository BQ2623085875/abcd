package com.example.terminal.base;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : YanKing
 * @date : 2019/6/12  16:42
 * @Description : ViewHolder的封装
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public BaseViewHolder(View view) {
        super(view);
        this.views = new SparseArray<>();
    }

    public <T extends View> T findViewById(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getView() {
        return itemView;
    }
}
