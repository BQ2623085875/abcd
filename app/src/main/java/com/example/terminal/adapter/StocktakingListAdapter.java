package com.example.terminal.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.StocktakingListBean;

import java.util.List;

public class StocktakingListAdapter extends BaseRecyclerAdapter<StocktakingListBean.RowsBean> {
    public StocktakingListAdapter(Context mContext, List<StocktakingListBean.RowsBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_list_stocktaking;
    }

    @Override
    protected void bindData(BaseViewHolder holder, StocktakingListBean.RowsBean data, int position) {
        LinearLayout mLl = holder.findViewById(R.id.mLl);
        TextView mTv_StocktakingName = holder.findViewById(R.id.mTv_StocktakingName);
        TextView mTv_stateText = holder.findViewById(R.id.mTv_stateText);//状态text
        TextView mTv_Staff = holder.findViewById(R.id.mTv_Staff);
        TextView mTv_Time = holder.findViewById(R.id.mTv_Time);//申请时间
        TextView mTv_state = holder.findViewById(R.id.mTv_state);//状态

        mTv_StocktakingName.setText(mList.get(position).getName());
        mTv_Staff.setText(mList.get(position).getCheckUserName());
        mTv_Time.setText(mList.get(position).getCreateTime());
        mTv_state.setText(mList.get(position).getCheckFlagName());

        setItemClickListener(mLl, position);
    }
}
