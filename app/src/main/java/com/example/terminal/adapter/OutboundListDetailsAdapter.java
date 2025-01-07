package com.example.terminal.adapter;


import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.OutboudListDetailsBean;

import java.util.List;

public class OutboundListDetailsAdapter extends BaseRecyclerAdapter<OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean> {

    public OutboundListDetailsAdapter(Context mContext, List<OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_details_list_outbound;
    }

    @Override
    protected void bindData(BaseViewHolder holder, OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean data, int position) {
        LinearLayout mLl = holder.findViewById(R.id.mLl);
        TextView mTv_deviceName = holder.findViewById(R.id.mTv_deviceName);
        TextView mTv_typeColumn = holder.findViewById(R.id.mTv_typeColumn);
        TextView mTv_Model = holder.findViewById(R.id.mTv_Model);
        TextView mTv_Supplier = holder.findViewById(R.id.mTv_Supplier);
        TextView mTv_state = holder.findViewById(R.id.mTv_state);

        mTv_deviceName.setText(data.getDeviceName());
        mTv_Model.setText(data.getDeviceType());
        mTv_Supplier.setText(data.getDeviceCompany());
        mTv_state.setText("100/" + data.getDeviceNum());

        setItemClickListener(mLl, position);
    }
}
