package com.example.terminal.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.StocktakingDetailsBean;

import java.util.List;

public class StocktakingDetailsAdapter extends BaseRecyclerAdapter<StocktakingDetailsBean.DataBean.TcheckDeviceListBean> {
    public StocktakingDetailsAdapter(Context mContext, List<StocktakingDetailsBean.DataBean.TcheckDeviceListBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_details_list_stocktaking;
    }

    @Override
    protected void bindData(BaseViewHolder holder, StocktakingDetailsBean.DataBean.TcheckDeviceListBean data, int position) {
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
