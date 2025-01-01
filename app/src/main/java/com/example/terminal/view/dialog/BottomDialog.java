package com.example.terminal.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.listener.OnSheetItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author : YanKing
 *  @date : 2019/5/19  9:53
 *  @Description : 底部弹框
 */

public class BottomDialog extends BaseDialog {

    private TextView mTvTitle;
    private LinearLayout mLlSearchInfo;
    private EditText mEtSearch ;
    private TextView mTvCancel;
    private LinearLayout mLayoutContent;
    private ScrollView mScrollView;
    private List<SheetItem> sheetItemList;
    private List<SheetItem> tempSheetItemList;

    public static BottomDialog getInstance(Context context) {
        return new BottomDialog(context);
    }

    private BottomDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private BottomDialog builder() {
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
                for (int i = 0; i < sheetItemList.size(); i++) {
                    if(sheetItemList.get(i).name.contains(s.toString()))
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

        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mLayoutContent != null)
                    mLayoutContent.removeAllViews();
                if (sheetItemList != null)
                    sheetItemList.clear();
                if(tempSheetItemList != null)
                    tempSheetItemList.clear();
            }
        });
        return this;
    }

    public BottomDialog setTitle(String title) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
        return this;
    }

    /**
     * 是否展示搜索
     * @param isVisible
     * @return
     */
    public BottomDialog setSearchVisible(int isVisible){
        mLlSearchInfo.setVisibility(isVisible);
        return this;
    }

    public BottomDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public BottomDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param listener
     * @return
     */
    public BottomDialog addSheetItem(String strItem, OnSheetItemClickListener listener) {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        sheetItemList.add(new SheetItem(strItem, true, listener));
        tempSheetItemList = sheetItemList;
        return this;
    }

    /**
     * @param resource  条目名称
     * @param listener
     * @return
     */
    public BottomDialog addSheetItem(int resource, OnSheetItemClickListener listener) {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        sheetItemList.add(new SheetItem(mContext.getString(resource), true, listener));
        tempSheetItemList = sheetItemList;
        return this;
    }

    /**
     * @param strItem 条目名称
     * @return
     */
    public BottomDialog addSheetItem(String strItem, boolean click) {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        sheetItemList.add(new SheetItem(strItem, click, null));
        tempSheetItemList = sheetItemList;
        return this;
    }

    /**
     * @return
     */
    public BottomDialog addSheetItem(int resource) {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        sheetItemList.add(new SheetItem(mContext.getString(resource)));
        tempSheetItemList = sheetItemList;
        return this;
    }

    /**
     * @param strItem 条目名称
     * @return
     */
    public BottomDialog addSheetItem(String strItem) {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        sheetItemList.add(new SheetItem(strItem));
        tempSheetItemList = sheetItemList;
        return this;
    }

    /**
     * @return
     */
    public BottomDialog addSheetItem(List<String> list) {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        for(String item :list)
            sheetItemList.add(new SheetItem(item));
        tempSheetItemList = sheetItemList;
        return this;
    }


    /**
     * 设置条目布局
     */
    @SuppressWarnings("deprecation")
    private void setSheetItems() {
        if (tempSheetItemList == null)
            return;

        mLayoutContent.removeAllViews();

        int size = tempSheetItemList.size();

        // 添加条目过多的时候控制高度
        LayoutParams params = (LayoutParams) mScrollView.getLayoutParams();
        if (size > 5) {
            params.height = mDisplay.getHeight() / 2;
        }else {
            params = new LayoutParams(ScrollView.LayoutParams.MATCH_PARENT , ScrollView.LayoutParams.WRAP_CONTENT);
        }
        mScrollView.setLayoutParams(params);

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = tempSheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            boolean click = sheetItem.click;

            TextView textView = new TextView(mContext);
            textView.setText(strItem);
            textView.setTextSize(12);
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
                        if(sheetItem.itemClickListener != null){
                            sheetItem.itemClickListener.onClick(index - 1);
                        }else if(onSheetItemClickListener != null){
                            onSheetItemClickListener.onClick(index - 1);
                        }
                    }
                });
            }

            mLayoutContent.addView(textView);
        }
    }

    private OnSheetItemClickListener onSheetItemClickListener ;

    public BottomDialog setOnItemClickListener(OnSheetItemClickListener onItemClickListener){
        this.onSheetItemClickListener = onItemClickListener;
        return this ;
    }

    @Override
    protected void setLayout() {
        setCancelable(false);
        setSheetItems();
    }

    public class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        boolean click = true;

        public SheetItem(String name) {
            this.name = name;
        }

        public SheetItem(String name, OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.itemClickListener = itemClickListener;
        }

        public SheetItem(String name, boolean click , OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.click = click ;
            this.itemClickListener = itemClickListener;
        }
    }
}
