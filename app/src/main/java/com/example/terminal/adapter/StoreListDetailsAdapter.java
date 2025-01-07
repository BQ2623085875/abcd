package com.example.terminal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.StoreListDetailsBean;

import java.util.List;

public class StoreListDetailsAdapter extends BaseRecyclerAdapter<StoreListDetailsBean.DataBean.TstoregeInDeviceBean> {
    public StoreListDetailsAdapter(Context mContext, List<StoreListDetailsBean.DataBean.TstoregeInDeviceBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_details_list_store;
    }

    @Override
    protected void bindData(BaseViewHolder holder, StoreListDetailsBean.DataBean.TstoregeInDeviceBean data, int position) {
        LinearLayout mLl = holder.findViewById(R.id.mLl);
        TextView mTv_deviceName = holder.findViewById(R.id.mTv_deviceName);
        TextView mTv_typeColumn = holder.findViewById(R.id.mTv_typeColumn);
        TextView mTv_Model = holder.findViewById(R.id.mTv_Model);
        TextView mTv_Supplier = holder.findViewById(R.id.mTv_Supplier);
        TextView mTv_state = holder.findViewById(R.id.mTv_state);

        mTv_state.setText("100/" + data.getDeviceNum());
        mTv_deviceName.setText(data.getDeviceName());
        mTv_Model.setText(data.getDeviceType());
        mTv_Supplier.setText(data.getDeviceCompany());

        setItemClickListener(mLl, position);
    }
}
