package com.example.terminal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.listener.OnPopupWindowClickListener;
import com.example.terminal.view.dialog.SpinnerPopWindow;

import java.util.List;

public class CommonSpinnerTextView extends RelativeLayout {

    private Context mContext;
    private RelativeLayout mRlContainer;
    private TextView mTvSpinner;

    private List<String> commonList ;

    public CommonSpinnerTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CommonSpinnerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonSpinnerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    }

    public void setList(List<String> mList , OnPopupWindowClickListener listener){
        this.commonList = mList ;

        mTvSpinner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerPopWindow(mContext, commonList , new OnPopupWindowClickListener() {
                    @Override
                    public void onClick(int position) {
                        mTvSpinner.setText(commonList.get(position));
                        listener.onClick(position);
                    }
                }).showAsDropDown(mTvSpinner);
            }
        });
    }

    public void setText(String text){
        mTvSpinner.setText(text);
    }

    public void setVisibilityBoolean(boolean visibility) {
        mRlContainer.setVisibility(visibility ? VISIBLE : GONE);
    }
}