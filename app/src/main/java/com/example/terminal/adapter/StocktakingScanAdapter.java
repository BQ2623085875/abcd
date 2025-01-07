package com.example.terminal.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.StocktakingDetailsBean;
import com.example.terminal.bean.StocktakingListBean;

import java.util.List;

public class StocktakingScanAdapter extends BaseRecyclerAdapter<StocktakingDetailsBean.DataBean.TcheckDeviceListBean.DeviceDetailListBean> {
    public StocktakingScanAdapter(Context mContext, List<StocktakingDetailsBean.DataBean.TcheckDeviceListBean.DeviceDetailListBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_scan_stocktaking;
    }

    @Override
    protected void bindData(BaseViewHolder holder, StocktakingDetailsBean.DataBean.TcheckDeviceListBean.DeviceDetailListBean data, int position) {
        ImageView mIv_delete = holder.findViewById(R.id.mIv_delete);
        TextView mTv_position = holder.findViewById(R.id.mTv_position);
        TextView mTv_positionlist = holder.findViewById(R.id.mTv_positionlist);
        TextView mTv_state = holder.findViewById(R.id.mTv_state);
        TextView mTv_describe = holder.findViewById(R.id.mTv_describe);

        mTv_position.setText(position + 1 + "");
        mTv_positionlist.setText(data.getImei());
        mTv_state.setText(data.getErrorStatus());
        mTv_describe.setText(data.getRemark());

        setOnTextClickListener(mIv_delete, position);

    }
}
