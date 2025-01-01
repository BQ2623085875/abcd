package com.example.terminal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.listener.OnPopupWindowClickListener;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.TimerPickerUtils;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

import java.util.Date;

public class HeaderTitleView extends RelativeLayout {

    private Context mContext;
    private ImageButton mIbBack;
    private TextView mTvTitleName;
    private Switch mSAlreadyPart;
    private SpinnerTextView mStvCheckType,//待核，通过、未通过
            mStvTakeType,//进港提货
            mStvTypeSelected,//类型
            mStvStatusSelected,//状态
            mStvAccessType;//进港，出港
    private TextView mTvScan,//扫描
            mTvCommon,//通用
            mTvUnusual;// 异常
    // 搜索
    private RelativeLayout mRlSearchContainer, mRlFlightNumberSearchContainer, mRlWaybillNumberSearchContainer;
    private EditText mEtSearch, mEtFlightNumberSearch, mEtWaybillNumberSearch;
    private ImageButton mIbClearSearch, mIbFlightNumberClearSearch, mIbWaybillNumberClearSearch;
    // 时间
    private LinearLayout mLlDateContainer, mLlAnotherDateContainer;
    private ImageButton mIbLeft, mIbRight;
    private RelativeLayout mRlDateContainer, mRlAnotherDateContainer;
    private TextView mTvDateContent, mTvAnotherDateContent;

    private String mDate;
    private String mDates;
    private int checkTypeState = 0, takeTypeState = 0, accessTypeState = 1;

    private OnHeaderListener mTitleListener;

    public HeaderTitleView(Context context) {
        super(context);
        init(context, null);
    }

    public HeaderTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HeaderTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout mRlContainer = (RelativeLayout) layoutInflater.inflate(R.layout.view_header_title, this, true);
        // 标题
        mTvTitleName = mRlContainer.findViewById(R.id.mTvTitleName);
        // 全部、部分
        mSAlreadyPart = mRlContainer.findViewById(R.id.mSAlreadyPart);

        // 通过、未通过
        mStvCheckType = mRlContainer.findViewById(R.id.mStvCheckType);

        //进港,出港
        mStvAccessType = mRlContainer.findViewById(R.id.mStvAccessType);

        mStvTakeType = mRlContainer.findViewById(R.id.mStvTakeType);
        // 种类
        mStvTypeSelected = mRlContainer.findViewById(R.id.mStvTypeSelected);
        // 状态
        mStvStatusSelected = mRlContainer.findViewById(R.id.mStvStatusSelected);
        // 扫描
        mTvScan = mRlContainer.findViewById(R.id.mTvScan);
        // 异常
        mTvUnusual = mRlContainer.findViewById(R.id.mTvUnusual);
        // 通用
        mTvCommon = mRlContainer.findViewById(R.id.mTvCommon);
        // 返回
        mIbBack = mRlContainer.findViewById(R.id.mIbBack);

        // 搜索
        mRlSearchContainer = mRlContainer.findViewById(R.id.mRlSearchContainer);
        mEtSearch = mRlContainer.findViewById(R.id.mEtSearch);
        mIbClearSearch = mRlContainer.findViewById(R.id.mIbClearSearch);

        //航班号搜索
        mRlFlightNumberSearchContainer = mRlContainer.findViewById(R.id.mRlFlightNumberSearchContainer);
        mEtFlightNumberSearch = mRlContainer.findViewById(R.id.mEtFlightNumberSearch);
        mIbFlightNumberClearSearch = mRlContainer.findViewById(R.id.mIbFlightNumberClearSearch);

        //单号搜索
        mRlWaybillNumberSearchContainer = mRlContainer.findViewById(R.id.mRlWaybillNumberSearchContainer);
        mEtWaybillNumberSearch = mRlContainer.findViewById(R.id.mEtWaybillNumberSearch);
        mIbWaybillNumberClearSearch = mRlContainer.findViewById(R.id.mIbWaybillNumberClearSearch);

        // 时间
        mLlDateContainer = mRlContainer.findViewById(R.id.mLlDateContainer);
        mIbLeft = mRlContainer.findViewById(R.id.mIbLeft);
        mIbRight = mRlContainer.findViewById(R.id.mIbRight);
        mRlDateContainer = mRlContainer.findViewById(R.id.mRlDateContainer);
        mTvDateContent = mRlContainer.findViewById(R.id.mTvDateContent);

        //另一个时间
        mLlAnotherDateContainer = mRlContainer.findViewById(R.id.mLlAnotherDateContainer);
        mRlAnotherDateContainer = mRlContainer.findViewById(R.id.mRlAnotherDateContainer);
        mTvAnotherDateContent = mRlContainer.findViewById(R.id.mTvAnotherDateContent);

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.HeaderTitleView);

        // 设置标题文字
        mTvTitleName.setText(typedArray.getString(R.styleable.HeaderTitleView_title_text));

        // 待办、已办
        if (!TextUtils.isEmpty(typedArray.getString(R.styleable.HeaderTitleView_switch_off_text)))
            mSAlreadyPart.setTextOff(typedArray.getString(R.styleable.HeaderTitleView_switch_off_text));
        if (!TextUtils.isEmpty(typedArray.getString(R.styleable.HeaderTitleView_switch_on_text)))
            mSAlreadyPart.setTextOn(typedArray.getString(R.styleable.HeaderTitleView_switch_on_text));

        if (!TextUtils.isEmpty(typedArray.getString(R.styleable.HeaderTitleView_common_text))) {
            mTvCommon.setText(typedArray.getString(R.styleable.HeaderTitleView_common_text));
            mTvCommon.setVisibility(View.VISIBLE);

            mTvCommon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CommonUtils.isFastDoubleClick())
                        return;

                    if (mTitleListener != null)
                        mTitleListener.onClick(R.id.mTvCommon);
                }
            });
        }

        // 设置时间
        initDate(typedArray);

        //设置另一个时间
        initAnotherDate(typedArray);

        // 下拉选择框
        initGoodsSpinner(typedArray);

        // 部分全部监听
        initAllPartChange(typedArray);

        // 按钮点击
        initButton(typedArray);

        // 搜索事件
        initSearch(typedArray);

        //航班号搜索事件
        initFlightNumberSearch(typedArray);
        //单号搜索事件
        initWaybillNumberSearch(typedArray);
    }


    /**
     * 设置时间
     */
    private void initDate(TypedArray typedArray) {
        mLlDateContainer.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_date_view, false) ? VISIBLE : GONE);

        mDate = CommonUtils.getDateAdd(CommonUtils.getDate(), 0);
        mTvDateContent.setText(CommonUtils.getDateDay(mDate));

        enableChangeDateButton();

        mIbLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick())
                    return;

                mDate = CommonUtils.getDateCut(mDate, 1);
                mTvDateContent.setText(CommonUtils.getDateDay(mDate));

                enableChangeDateButton();

                if (mTitleListener != null)
                    mTitleListener.onDateChange(mDate);
            }
        });

        mIbRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick())
                    return;

                mDate = CommonUtils.getDateAdd(mDate, 1);
                mTvDateContent.setText(CommonUtils.getDateDay(mDate));

                enableChangeDateButton();

                if (mTitleListener != null)
                    mTitleListener.onDateChange(mDate);
            }
        });

        mRlDateContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mRlDateContainer);

                if (CommonUtils.isFastDoubleClick())
                    return;

                TimerPickerUtils.getDatePickerView(mContext, mDate, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if (!mDate.equals(CommonUtils.getDate(date))) {
                            mDate = CommonUtils.getDate(date);

                            mTvDateContent.setText(CommonUtils.getDateDay(mDate));

                            enableChangeDateButton();

                            if (mTitleListener != null)
                                mTitleListener.onDateChange(mDate);
                        }
                    }
                }).show();
            }
        });
    }

    private void enableChangeDateButton() {
        mIbRight.setEnabled(CommonUtils.compareDate(mDate));

        if (CommonUtils.compareDate(mDate)) {
            mIbRight.setImageDrawable(mContext.getDrawable(R.drawable.selector_arrow_right));
        } else {
            mIbRight.setImageDrawable(mContext.getDrawable(R.mipmap.icon_arrow_right_sel));
        }

        mIbLeft.setEnabled(!ConstantInfo.History_Start_Date.equals(mDate));

        if (!ConstantInfo.History_Start_Date.equals(mDate)) {
            mIbLeft.setImageDrawable(mContext.getDrawable(R.drawable.selector_arrow_left));
        } else {
            mIbLeft.setImageDrawable(mContext.getDrawable(R.mipmap.icon_arrow_left_sel));
        }
    }

    /**
     * 设置另一个时间
     */
    private void initAnotherDate(TypedArray typedArray) {
        mLlAnotherDateContainer.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_another_date_view, false) ? VISIBLE : GONE);

        mDates = CommonUtils.getDate(new Date());
        mTvAnotherDateContent.setText(mDates);

        mRlAnotherDateContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mRlAnotherDateContainer);

                if (CommonUtils.isFastDoubleClick())
                    return;

                TimerPickerUtils.getDatePickerView(mContext, mDates, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if (!mDates.equals(CommonUtils.getDate(date))) {
                            mDates = CommonUtils.getDate(date);

                            mTvAnotherDateContent.setText(mDates);

                            if (mTitleListener != null)
                                mTitleListener.onAnotherDateChange(mDates);
                        }
                    }
                }).show();
            }
        });
    }


    /**
     * 初始化下拉框
     *
     * @param typedArray
     */
    private void initGoodsSpinner(TypedArray typedArray) {

        mStvTypeSelected.setVisibilityBoolean(typedArray.getBoolean(R.styleable.HeaderTitleView_goods_type, false));
        // 类型
        mStvTypeSelected.setOnClickListener(0, new OnPopupWindowClickListener() {
            @Override
            public void onClick(int type, int index) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvTypeSelected);

                if (CommonUtils.isFastDoubleClick())
                    return;

                if (mTitleListener != null)
                    mTitleListener.onSwitchChange(type, index);
            }
        });

        mStvStatusSelected.setVisibilityBoolean(typedArray.getBoolean(R.styleable.HeaderTitleView_goods_status, false));
        // 状态
        mStvStatusSelected.setOnClickListener(1, new OnPopupWindowClickListener() {
            @Override
            public void onClick(int type, int index) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvStatusSelected);

                if (CommonUtils.isFastDoubleClick())
                    return;

                if (mTitleListener != null)
                    mTitleListener.onSwitchChange(type, index);
            }
        });

        // 状态
        mStvCheckType.setOnClickListener(2, new OnPopupWindowClickListener() {
            @Override
            public void onClick(int type, int index) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvCheckType);

                if (mTitleListener != null && checkTypeState != index) {
                    checkTypeState = index;
                    clearSearchContent();
                    mLlDateContainer.setVisibility(index == 0 ? View.GONE : View.VISIBLE);
                    mTitleListener.onSwitchChange(type, index);
                }
            }
        });

        // 状态
        mStvTakeType.setOnClickListener(3, new OnPopupWindowClickListener() {
            @Override
            public void onClick(int type, int index) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvTakeType);

                if (mTitleListener != null && takeTypeState != index) {
                    takeTypeState = index;
                    clearSearchContent();
                    mLlDateContainer.setVisibility(index != 2 ? View.GONE : View.VISIBLE);
                    mTitleListener.onSwitchChange(type, index);
                }
            }
        });

        mStvAccessType.setVisibilityBoolean(typedArray.getBoolean(R.styleable.HeaderTitleView_access_type, false));
        //进港，出港
        mStvAccessType.setOnClickListener(4, new OnPopupWindowClickListener() {
            @Override
            public void onClick(int type, int index) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvAccessType);

                if (mTitleListener != null) {
                    accessTypeState = index + 1;
                    mTitleListener.onSwitchChange(type, index);
                }
            }
        });
    }

    /**
     * 标题赋值
     *
     * @param title
     */
    public void setTitleName(String title) {
        mTvTitleName.setText(title);
    }

    /**
     * 设置搜索内容
     *
     * @param content
     */
    public void setSearchContent(String content) {
        if (TextUtils.isEmpty(content))
            return;

        mEtSearch.setText(content);
        mEtSearch.setSelection(content.length());
    }

    /**
     * 获取搜索内容
     */
    public String getSearchContent() {
        if (TextUtils.isEmpty(mEtSearch.getText().toString()))
            return "";

        return mEtSearch.getText().toString();
    }

    /**
     * 清除搜索内容
     */
    public void clearSearchContent() {
        mEtSearch.setText("");
    }


    /**
     * 扫描、异常点击事件
     */
    public void initButton(TypedArray typedArray) {
        mIbBack.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_back_button, false) ? VISIBLE : GONE);
        mTvScan.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_scan_button, false) ? VISIBLE : GONE);
        mTvUnusual.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_unusual_button, false) ? VISIBLE : GONE);
        mStvCheckType.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_check_type, false) ? VISIBLE : GONE);
        mStvTakeType.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_take_type, false) ? VISIBLE : GONE);
        mStvAccessType.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_access_type, false) ? VISIBLE : GONE);


        mIbBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mIbBack);

                if (CommonUtils.isFastDoubleClick())
                    return;

                ((BaseActivity) mContext).onFinish();
            }
        });

        mTvScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick())
                    return;

                CommonUtils.getPermissions((BaseActivity) mContext);

                if (Constant.MODEL_M5.equals(Build.MODEL)) {
                    mContext.sendBroadcast(new Intent(Constant.M5_Scan_Start_Scan));
                } else {
                    ScanUtil.startScan((BaseActivity) mContext, Constant.REQUEST_CODE, new HmsScanAnalyzerOptions.Creator().create());
                }
            }
        });

        mTvUnusual.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick())
                    return;

                if (mTitleListener != null)
                    mTitleListener.onClick(R.string.button_unusual);
            }
        });
    }

    /**
     * 部分、全部
     */
    private void initAllPartChange(TypedArray typedArray) {
        if (CommonUtils.isFastClick())
            return;
        mSAlreadyPart.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_switch_already_part, false) ? VISIBLE : GONE);
        mSAlreadyPart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mSAlreadyPart);

                mLlDateContainer.setVisibility(isChecked ? GONE : VISIBLE);
                clearSearchContent();

                if (mTitleListener != null)
                    mTitleListener.onPartChange(isChecked ? 1 : 0);
            }
        });
    }

    /**
     * 设置时间控件展示
     *
     * @param visible
     */
    public void setDateVisible(int visible) {
        mLlDateContainer.setVisibility(visible);
    }

    /**
     * 设置搜索
     */
    private void initSearch(TypedArray typedArray) {
        mRlSearchContainer.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_search_edit, false) ? VISIBLE : GONE);

//        mEtSearch.setInputType(typedArray.getBoolean(R.styleable.HeaderTitleView_edit_number, true) ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT);

        mEtSearch.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mIbClearSearch.setVisibility(TextUtils.isEmpty(s.toString()) ? GONE : VISIBLE);
            }
        });

        mIbClearSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mSAlreadyPart);

                if (CommonUtils.isFastDoubleClick())
                    return;

                mEtSearch.setText("");
                if (mTitleListener != null)
                    mTitleListener.onSearch("");
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mTitleListener != null)
                        mTitleListener.onSearch(v.getText().toString());
                    return false;
                }
                return false;
            }
        });
    }

    /**
     * 设置航班号搜索
     */
    private void initFlightNumberSearch(TypedArray typedArray) {
        mRlFlightNumberSearchContainer.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_flight_number_search_edit, false) ? VISIBLE : GONE);

//        mEtSearch.setInputType(typedArray.getBoolean(R.styleable.HeaderTitleView_edit_number, true) ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT);

        mEtFlightNumberSearch.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mIbFlightNumberClearSearch.setVisibility(TextUtils.isEmpty(s.toString()) ? GONE : VISIBLE);
            }
        });

        mIbFlightNumberClearSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvAccessType);

                if (CommonUtils.isFastDoubleClick())
                    return;

                mEtFlightNumberSearch.setText("");
                if (mTitleListener != null)
                    mTitleListener.onFlightNumberSearch("");
            }
        });

        mEtFlightNumberSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mTitleListener != null)
                        mTitleListener.onFlightNumberSearch(v.getText().toString());
                    return false;
                }
                return false;
            }
        });
    }

    /**
     * 设置单号搜索
     */
    private void initWaybillNumberSearch(TypedArray typedArray) {
        mRlWaybillNumberSearchContainer.setVisibility(typedArray.getBoolean(R.styleable.HeaderTitleView_waybill_number_search_edit, false) ? VISIBLE : GONE);

//        mEtSearch.setInputType(typedArray.getBoolean(R.styleable.HeaderTitleView_edit_number, true) ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT);

        mEtWaybillNumberSearch.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mIbWaybillNumberClearSearch.setVisibility(TextUtils.isEmpty(s.toString()) ? GONE : VISIBLE);
            }
        });

        mIbWaybillNumberClearSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏输入法
                CommonUtils.itHideInputMethod(mContext, mStvAccessType);

                if (CommonUtils.isFastDoubleClick())
                    return;

                mEtWaybillNumberSearch.setText("");
                if (mTitleListener != null)
                    mTitleListener.onWaybillNumberSearch("");
            }
        });

        mEtWaybillNumberSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mTitleListener != null)
                        mTitleListener.onWaybillNumberSearch(v.getText().toString());
                    return false;
                }
                return false;
            }
        });
    }

    public void addOnHeaderListener(OnHeaderListener listener) {
        mTitleListener = listener;
    }

    /**
     * 是否为已办
     *
     * @return
     */
    public boolean isDone() {
        return !mSAlreadyPart.isChecked();
    }

    /**
     * 获取当前时间
     */
    public String getSelectDate() {
        return mDate;
    }

    public String getSelectDates() {
        return mDates;
    }

    /**
     * 获取当前状态
     */
    public int getSelectTakeState() {
        return takeTypeState;
    }

    /**
     * 获取当前状态
     */
    public int getSelectCheckState() {
        return checkTypeState;
    }

    /**
     * 获取当前状态
     */
    public int getSelectAccessState() {
        return accessTypeState;
    }

    public static class OnHeaderListener {
        // 时间变化
        public void onDateChange(String date) {

        }

        // 另一个时间变化
        public void onAnotherDateChange(String date) {

        }

        // 搜索
        public void onSearch(String search) {
        }

        // 航班号搜索
        public void onFlightNumberSearch(String search) {
        }

        // 运单号搜索
        public void onWaybillNumberSearch(String search) {
        }

        // 按钮点击
        public void onClick(int resource) {
        }

        // 全部、部分切换
        public void onPartChange(int isChecked) {
        }

        // 状态，种类切换
        public void onSwitchChange(int type, int index) {

        }

    }


    //-------------------------------------航班号搜索

    /**
     * 设置搜索内容
     *
     * @param content
     */
    public void setFlightNumberSearchContent(String content) {
        if (TextUtils.isEmpty(content))
            return;

        mEtFlightNumberSearch.setText(content);
        mEtFlightNumberSearch.setSelection(content.length());
    }

    /**
     * 获取搜索内容
     */
    public String getFlightNumberSearchContent() {
        if (TextUtils.isEmpty(mEtFlightNumberSearch.getText().toString()))
            return "";

        return mEtFlightNumberSearch.getText().toString();
    }

    /**
     * 清除搜索内容
     */
    public void clearFlightNumberSearchContent() {
        mEtFlightNumberSearch.setText("");
    }

    //-------------------------------------单号搜索

    /**
     * 设置搜索内容
     *
     * @param content
     */
    public void setWaybillNumberSearchContent(String content) {
        if (TextUtils.isEmpty(content))
            return;

        mEtWaybillNumberSearch.setText(content);
        mEtWaybillNumberSearch.setSelection(content.length());
    }

    /**
     * 获取搜索内容
     */
    public String getWaybillNumberSearchContent() {
        if (TextUtils.isEmpty(mEtWaybillNumberSearch.getText().toString()))
            return "";

        return mEtWaybillNumberSearch.getText().toString();
    }

    /**
     * 清除搜索内容
     */
    public void clearWaybillNumberSearchContent() {
        mEtWaybillNumberSearch.setText("");
    }
}
