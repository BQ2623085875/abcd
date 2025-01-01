package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.TugBean.TugData;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.EditTextWatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author : YanKing
 * @Description : 拖斗，集装器
 */

public class TugDialog extends BaseDialog {

    private TextView mTvTitle;
    private LinearLayout mLlSearchInfo;
    private EditText mEtSearch;
    private TextView mTvCancel;
    private LinearLayout mLayoutContent;
    private ScrollView mScrollView;
    private List<TugData> sheetItemList;
    private List<TugData> tempSheetItemList;

    private TugData disableContainer;
    private int TugType;
    private boolean isCustom;

    public static TugDialog getInstance(Context context) {
        return new TugDialog(context);
    }

    private TugDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private TugDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(mDisplay.getWidth());

        // 获取自定义Dialog布局中的控件
        mScrollView = view.findViewById(R.id.mScrollView);
        mLayoutContent = view.findViewById(R.id.mLayoutContent);
        mTvTitle = view.findViewById(R.id.mTvTitle);
        mEtSearch = view.findViewById(R.id.mEtSearch);
        mLlSearchInfo = view.findViewById(R.id.mLlSearchInfo);
        mTvCancel = view.findViewById(R.id.mTvCancel);

        mLlSearchInfo.setVisibility(View.VISIBLE);

        mTvCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mEtSearch.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                tempSheetItemList = new ArrayList<>();
                if (sheetItemList != null)
                    for (int i = 0; i < sheetItemList.size(); i++) {
                        if (sheetItemList.get(i).getCode().contains(s.toString()))
                            tempSheetItemList.add(sheetItemList.get(i));
                    }

                setSheetItems();
            }
        });

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    private TugDialog getContainerData() {
        NetworkUtils.getTugList(new OkGoBackListener((Activity) mContext) {
            @Override
            public void onSuccess(Object body) {
                sheetItemList = (List<TugData>) body;

                tempSheetItemList = sheetItemList;

                setSheetItems();
            }
        }, TugType);

        return this;
    }

    public TugDialog setTitle(String title) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
        return this;
    }

    /**
     * 是否展示搜索
     *
     * @param isVisible
     * @return
     */
    public TugDialog setSearchVisible(int isVisible) {
        mLlSearchInfo.setVisibility(isVisible);
        return this;
    }

    /**
     * 是否为拖斗
     *
     * @param TugType
     * @return
     */
    public TugDialog setTugType(int TugType) {
        if (this.TugType != TugType) {
            this.TugType = TugType;
            getContainerData();
        } else {
            if (sheetItemList == null || sheetItemList.size() == 0)
                getContainerData();
        }

        return this;
    }

    /**
     * 是否为拖斗
     *
     * @param TugType
     * @return
     */
    public TugDialog setTugType(int TugType, boolean isCustom) {
        this.isCustom = isCustom;
        if (this.TugType != TugType) {
            this.TugType = TugType;
            getContainerData();
        } else {
            if (sheetItemList == null || sheetItemList.size() == 0)
                getContainerData();
        }

        return this;
    }

    /**
     * 数据传递
     *
     * @param dataList
     * @return
     */
    public TugDialog setTugData(List<TugData> dataList) {
        sheetItemList = dataList;

        tempSheetItemList = sheetItemList;

        setSheetItems();

        return this;
    }

    public TugDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public TugDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public TugDialog setDisableItem(TugData channelData) {
        disableContainer = channelData;
        if (disableContainer != null)
            setSheetItems();
        return this;
    }

    /**
     * 设置条目布局
     */
    @SuppressWarnings("deprecation")
    private void setSheetItems() {
        if (tempSheetItemList == null)
            return;

        if (isCustom) {
            if (ConstantInfo.mPermissionsList.contains(Constant.ZDYTD) || ConstantInfo.IS_ADMIN)
                tempSheetItemList.add(0, new TugData(mContext.getString(TugType == 0 ? R.string.text_custom_tug : R.string.text_custom_pallet), -1000));
        }

        mLayoutContent.removeAllViews();

        // 根据排序对排序进行拖斗
        Collections.sort(tempSheetItemList, new SortComparator());

        int size = tempSheetItemList.size();

        // 添加条目过多的时候控制高度
        LayoutParams params = (LayoutParams) mScrollView.getLayoutParams();
        if (size > 5) {
            params.height = mDisplay.getHeight() / 2;
        } else {
            params = new LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
        }
        mScrollView.setLayoutParams(params);

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            TugData sheetItem = tempSheetItemList.get(i - 1);
            String strItem = sheetItem.getCode();

            boolean click = true;

            if (disableContainer != null)
                click = !sheetItem.getCode().equals(disableContainer.getCode());

            TextView textView = new TextView(mContext);
            textView.setText(strItem);
            textView.setTextSize(16);
            textView.setGravity(Gravity.CENTER);

            mLlSearchInfo.setBackgroundResource(mTvTitle.getVisibility() == View.VISIBLE ? R.color.white : R.drawable.shape_top_white);

            // 背景图片
            if (size == 1) {
                textView.setBackgroundResource(click ? R.drawable.selector_white_fd : R.color.white);
            } else {
                if (mTvTitle.getVisibility() == View.VISIBLE) {
                    textView.setBackgroundResource(click ? R.drawable.selector_white_fd : R.color.white);
                } else {
                    if (i == 1 && mLlSearchInfo.getVisibility() == View.GONE) {
                        textView.setBackgroundResource(click ? R.drawable.selector_top_white_gray_shape : R.drawable.shape_top_white);
                    } else {
                        textView.setBackgroundResource(click ? R.drawable.selector_white_fd : R.color.white);
                    }
                }
            }

            textView.setTextColor(click ? mContext.getResources().getColor(R.color.black) : mContext.getResources().getColor(android.R.color.darker_gray));

            // 高度
            float scale = mContext.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, height);
            textView.setLayoutParams(layoutParams);

            if (click) {
                // 点击事件
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        if (onSheetItemClickListener != null)
                            onSheetItemClickListener.onClick(tempSheetItemList.get(index - 1).getWeight() == -1000 ? null : tempSheetItemList.get(index - 1));
                    }
                });

                textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (onSheetItemClickListener != null && tempSheetItemList.get(index - 1).getWeight() != -1000) {
                            mDialog.dismiss();
                            onSheetItemClickListener.onLongClick(tempSheetItemList.get(index - 1));
                        }
                        return false;
                    }
                });
            }

            mLayoutContent.addView(textView);
        }
    }

    private OnSheetItemClickListener onSheetItemClickListener;

    public TugDialog setOnItemClickListener(OnSheetItemClickListener onItemClickListener) {
        this.onSheetItemClickListener = onItemClickListener;
        return this;
    }

    /**
     * 排序
     */
    private class SortComparator implements Comparator<TugData> {
        @Override
        public int compare(TugData data1, TugData data2) {
            return data1.getSort() - data2.getSort();
        }
    }

    @Override
    protected void setLayout() {
        setCancelable(false);
        setSheetItems();
    }

    public abstract static class OnSheetItemClickListener {
        public abstract void onClick(TugData channelData);

        public void onLongClick(TugData channelData) {
        }
    }
}
