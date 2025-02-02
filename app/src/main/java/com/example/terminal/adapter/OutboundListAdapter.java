package com.example.terminal.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.OutboundListBean;

import java.util.List;

public class OutboundListAdapter extends BaseRecyclerAdapter<OutboundListBean.RowsBean> {
    public OutboundListAdapter(Context mContext, List<OutboundListBean.RowsBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_list_outbound;
    }

    @Override
    protected void bindData(BaseViewHolder holder, OutboundListBean.RowsBean data, int position) {
        LinearLayout mLl = holder.findViewById(R.id.mLl);
        TextView mTv_libraryName = holder.findViewById(R.id.mTv_libraryName);//库名
        TextView mTv_stateText = holder.findViewById(R.id.mTv_stateText);//状态text
        TextView mTv_typeColumn = holder.findViewById(R.id.mTv_typeColumn);//类型
        TextView mTv_applicationTime = holder.findViewById(R.id.mTv_applicationTime);//申请时间
        TextView mTv_state = holder.findViewById(R.id.mTv_state);//状态
        TextView mTv_ReceivingUnitsIndividuals = holder.findViewById(R.id.mTv_ReceivingUnitsIndividuals);

        mTv_libraryName.setText(mList.get(position).getStoreHouseName());
        mTv_typeColumn.setText(mList.get(position).getTypeName());
        mTv_applicationTime.setText(mList.get(position).getApplyDate());
        mTv_state.setText(mList.get(position).getArrs().get(0));

        setItemClickListener(mLl, position);

    }
}
