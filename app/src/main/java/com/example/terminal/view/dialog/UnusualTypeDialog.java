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
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.DictBean.DictData;
import com.example.terminal.bean.UnusualBean.UnusualData;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.listener.UnusualConfirmClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author : YanKing
 *  @Description : 底部弹框
 */

public class UnusualTypeDialog extends BaseDialog {

    private TextView mTvTitle;
    private LinearLayout mLlSearchInfo;
    private EditText mEtSearch ;
    private TextView mTvCancel;
    private LinearLayout mLayoutContent;
    private ScrollView mScrollView;
    private List<SheetItem> sheetItemList;
    private List<SheetItem> tempSheetItemList;
    private List<DictData> mUnusualTypeList;
    private boolean isCa ;

    public static UnusualTypeDialog getInstance(Context context , boolean isCa) {
        return new UnusualTypeDialog(context , isCa);
    }

    private UnusualTypeDialog(Context context , boolean isCa) {
        this.mContext = context;
        this.isCa = isCa ;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private UnusualTypeDialog builder() {
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

        getUnusualList();

        return this;
    }

    private void getUnusualList(){
        NetworkUtils.getDictList(new OkGoBackListener((BaseActivity) mContext) {
            @Override
            public void onSuccess(Object body) {
                mUnusualTypeList = (List<DictData>) body;

                addSheetItem();
            }
        }, ConstantInfo.UnusualType);
    }

    public UnusualTypeDialog setTitle(String title) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
        return this;
    }

    /**
     * 是否展示搜索
     * @param isVisible
     * @return
     */
    public UnusualTypeDialog setSearchVisible(int isVisible){
        mLlSearchInfo.setVisibility(isVisible);
        return this;
    }

    public UnusualTypeDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public UnusualTypeDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * @return
     */
    private UnusualTypeDialog addSheetItem() {
        if (sheetItemList == null)
            sheetItemList = new ArrayList<>();
        for(DictData item :mUnusualTypeList)
            sheetItemList.add(new SheetItem(item.getDictLabel()));
        tempSheetItemList = sheetItemList;

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
                        if(mUnusualBean == null)
                            mUnusualBean = new UnusualData();
                        mUnusualBean.setType(Integer.parseInt(mUnusualTypeList.get(index - 1).getDictValue()));
                        mUnusualBean.setTypeName(mUnusualTypeList.get(index - 1).getDictLabel());

                        mDialog.dismiss();
                        UnusualDialog.getInstance(mContext , isCa)
                                .setUnusualData(mUnusualBean , mPosition)
                                .setNetwork(isNetwork)
                                .setConfirmButton(unusualConfirmClickListener)
                                .show();
                    }
                });
            }

            mLayoutContent.addView(textView);
        }
    }

    private UnusualData mUnusualBean;
    private int mPosition ;
    private boolean isNetwork = true;

    /**IOS
     * @param unusualData
     * @return
     */
    public UnusualTypeDialog setUnusualData(UnusualData unusualData , int position){
        mPosition = position ;
        mUnusualBean = unusualData;

        return this ;
    }

    public UnusualTypeDialog setNetwork(boolean isNetwork){
        this.isNetwork = isNetwork ;
        return this ;
    }

    private UnusualConfirmClickListener unusualConfirmClickListener;

    public UnusualTypeDialog setConfirmClickListener(UnusualConfirmClickListener onItemClickListener){
        this.unusualConfirmClickListener = onItemClickListener;
        return this ;
    }

    @Override
    protected void setLayout() {
        setCancelable(false);
        setSheetItems();
    }

    public class SheetItem {
        String name;
        boolean click = true;

        public SheetItem(String name) {
            this.name = name;
        }
    }
}
