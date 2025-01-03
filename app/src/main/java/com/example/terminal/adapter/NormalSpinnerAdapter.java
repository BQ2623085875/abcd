package com.example.terminal.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;

import java.util.List;

/**
 * 下拉框选择
 */
public class NormalSpinnerAdapter extends BaseRecyclerAdapter<String> {

    private boolean isBlueBackground;

    public NormalSpinnerAdapter(Context mContext, List<String> mList , boolean isBlueBackground) {
        super(mContext, mList);
        this.isBlueBackground = isBlueBackground ;
    }

    @Override
    public int setContentView() {
        return R.layout.view_pop_spinner_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        LinearLayout mLlContainerItem = holder.findViewById(R.id.mLlContainerItem);
        TextView mTvMsg = holder.findViewById(R.id.mTvMsg);

        if(!isBlueBackground)
            mTvMsg.setTextColor(mContext.getResources().getColor(R.color.black));

        mTvMsg.setText(data);

        setOnItemClickListener(mLlContainerItem , position);
    }
}
