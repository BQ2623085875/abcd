package com.example.terminal.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.NormalSpinnerAdapter;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.listener.OnPopupWindowClickListener;
import com.example.terminal.util.DensityUtils;

import java.util.List;

public class SpinnerPopWindow extends PopupWindow {
    private Context mContext;
    private RecyclerView mRvSpinner;
    private NormalSpinnerAdapter mAdapter;
    private OnPopupWindowClickListener mItemSelectListener;
    private List<String> mItemList;
    private boolean isBlueBackground = true ;

    public SpinnerPopWindow(Context context , List<String> mItemList , OnPopupWindowClickListener listener){
        super(context);

        this.mItemList = mItemList ;
        mContext = context;
        this.mItemSelectListener = listener ;
        init();
    }

    public SpinnerPopWindow(Context context , List<String> mItemList ,boolean isBlueBackground , OnPopupWindowClickListener listener){
        super(context);

        this.mItemList = mItemList ;
        mContext = context;
        this.mItemSelectListener = listener ;
        this.isBlueBackground = isBlueBackground ;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_pop_spinner, null);
        setContentView(view);
        setWidth(DensityUtils.dip2px(95));
        setHeight(DensityUtils.dip2px(30 * mItemList.size() + 10));

        setFocusable(true);

        setBackgroundDrawable(null);

        mRvSpinner = view.findViewById(R.id.mRvSpinner);

        if(!isBlueBackground){
            mRvSpinner.setBackground(mContext.getDrawable(R.drawable.shape_white));
        }
        mRvSpinner.getBackground().setAlpha(192);

        mAdapter = new NormalSpinnerAdapter(mContext , mItemList , isBlueBackground);
        mRvSpinner.setAdapter(mAdapter);
        mRvSpinner.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                mItemSelectListener.onClick(position);
                dismiss();
            }
        });
    }

    public void setColorType(){

    }

    public interface OnItemSelectListener {
        void onClick(int position);
    }
}
