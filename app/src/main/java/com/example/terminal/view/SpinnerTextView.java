package com.example.terminal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.listener.OnPopupWindowClickListener;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.view.dialog.SpinnerPopWindow;

import java.util.ArrayList;

public class SpinnerTextView extends RelativeLayout {

    private Context mContext;
    private RelativeLayout mRlContainer;
    private TextView mTvSpinner;

    private ArrayList<String> goodsTypeList;
    private ArrayList<String> goodsStatusList;
    private ArrayList<String> checkTypeList;
    private ArrayList<String> takeTypeList;
    private ArrayList<String> containerType;
    private ArrayList<String> accessType;

    public SpinnerTextView(Context context) {
        super(context);
        init(context, null);
    }

    public SpinnerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SpinnerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.view_spinner_text, this, true);
        mTvSpinner = relativeLayout.findViewById(R.id.mTvSpinner);
        mRlContainer = relativeLayout.findViewById(R.id.mRlContainer);

        goodsTypeList = new ArrayList<>();
        goodsTypeList.add(context.getString(R.string.spinner_select));
        goodsTypeList.add(context.getString(R.string.text_not_precious));
        goodsTypeList.add(context.getString(R.string.text_precious));
        goodsTypeList.add(context.getString(R.string.text_not_dangerous));
        goodsTypeList.add(context.getString(R.string.text_dangerous));

        goodsStatusList = new ArrayList<>();
        goodsStatusList.add(context.getString(R.string.spinner_select));
        goodsStatusList.add(context.getString(R.string.text_normal));
        goodsStatusList.add(context.getString(R.string.text_torn));
        goodsStatusList.add(context.getString(R.string.text_shortage));
        goodsStatusList.add(context.getString(R.string.text_overcharge));

        checkTypeList = new ArrayList<>();
        checkTypeList.add(context.getString(R.string.text_be_verified));
        checkTypeList.add(context.getString(R.string.text_passed));
        checkTypeList.add(context.getString(R.string.text_fail));

        takeTypeList = new ArrayList<>();
        takeTypeList.add(context.getString(R.string.switch_no_channel));
        takeTypeList.add(context.getString(R.string.switch_take_part));
        takeTypeList.add(context.getString(R.string.switch_take_already));

        containerType = new ArrayList<>();
        containerType.add(context.getString(R.string.text_tug));
        containerType.add(context.getString(R.string.text_container));

        containerType = new ArrayList<>();
        containerType.add(context.getString(R.string.text_have));
        containerType.add(context.getString(R.string.text_not));

        accessType = new ArrayList<>();
        accessType.add("出港");
        accessType.add("进港");
    }

    /**
     * 点击事件
     *
     * @param type , 0 为货物种类，1为货物状态，2为运单状态，3为提货状态,4进港出港
     */
    public void setOnClickListener(int type, OnPopupWindowClickListener listener) {
        if (type == 2) {
            mTvSpinner.setText(checkTypeList.get(0));
        } else if (type == 3) {
            mTvSpinner.setText(takeTypeList.get(0));
        } else if (type == 4) {
            mTvSpinner.setText(accessType.get(0));
        }

        mTvSpinner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mTvSpinner);

                new SpinnerPopWindow(mContext, type == 0 ? goodsTypeList : (type == 1 ? goodsStatusList : (type == 2 ? checkTypeList : (type == 3 ? takeTypeList : (type == 4 ? accessType : containerType)))), new OnPopupWindowClickListener() {
                    @Override
                    public void onClick(int position) {
                        mTvSpinner.setText((type == 0 ? goodsTypeList : (type == 1 ? goodsStatusList : (type == 2 ? checkTypeList : (type == 3 ? takeTypeList : (type == 4 ? accessType : containerType))))).get(position));
                        listener.onClick(type, position);
                    }
                }).showAsDropDown(mTvSpinner);
            }
        });
    }

    public void setText(String text) {
        mTvSpinner.setText(text);
    }

    public void setVisibilityBoolean(boolean visibility) {
        mRlContainer.setVisibility(visibility ? VISIBLE : GONE);
    }
}