package com.example.terminal.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.OutboudListDetailsBean;

import java.util.List;

public class OutboundScanAdapter extends BaseRecyclerAdapter<OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean> {
    public OutboundScanAdapter(Context mContext, List<OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_scan_outbound;
    }

    @Override
    protected void bindData(BaseViewHolder holder, OutboudListDetailsBean.DataBean.TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean data, int position) {
        ImageView mIv_delete = holder.findViewById(R.id.mIv_delete);
        TextView mTv_position = holder.findViewById(R.id.mTv_position);
        TextView mTv_positionlist = holder.findViewById(R.id.mTv_positionlist);

        mTv_position.setText(position + 1 + "");
        mTv_positionlist.setText(data.getImei());

        setOnTextClickListener(mIv_delete, position);
    }
}
