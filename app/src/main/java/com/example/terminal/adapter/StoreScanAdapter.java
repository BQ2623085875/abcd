package com.example.terminal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseRecyclerAdapter;
import com.example.terminal.base.BaseViewHolder;
import com.example.terminal.bean.StoreListDetailsBean;

import java.util.List;

public class StoreScanAdapter extends BaseRecyclerAdapter<StoreListDetailsBean.DataBean.TstoregeInDeviceBean.tstoregeInDeviceDetailListBean> {
    public StoreScanAdapter(Context mContext, List<StoreListDetailsBean.DataBean.TstoregeInDeviceBean.tstoregeInDeviceDetailListBean> mList) {
        super(mContext, mList);
    }

    @Override
    public int setContentView() {
        return R.layout.adapter_scan_store;
    }

    @Override
    protected void bindData(BaseViewHolder holder, StoreListDetailsBean.DataBean.TstoregeInDeviceBean.tstoregeInDeviceDetailListBean data, int position) {
        ImageView mIv_delete = holder.findViewById(R.id.mIv_delete);
        TextView mTv_position = holder.findViewById(R.id.mTv_position);
        TextView mTv_positionlist = holder.findViewById(R.id.mTv_positionlist);

        mTv_position.setText(position + 1 + "");
        mTv_positionlist.setText(data.getImei());

        setOnTextClickListener(mIv_delete, position);

    }
}
