package com.example.terminal.view.edit;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.ContainerBean.ContainerData;
import com.example.terminal.bean.TugBean.TugData;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.listener.InputCapLowerToUpper;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ToastUtils;

/**
 * 拆板操作
 */

public class CustomContainerEditorActivity extends BaseActivity {

    private TextView mTvContainerNoText, mTvContainerWeightText;
    private EditText mEtContainerNo, mEtContainerWeight;
    private static EditorCallbackListener mEditorCallback;
    private static ContainerData mContainerData;
    private static TugData mTugData;
    ;
    private static int isContainer = 1;// 1拖斗新增、2集装器新增、3托盘新增、4自定义拖斗、5自定义集装器、6自定义托盘

    @Override
    protected int setContentView() {
        return R.layout.editor_custom_container;
    }

    protected void initView() {
        mTvContainerNoText = findViewById(R.id.mTvContainerNoText);
        mTvContainerWeightText = findViewById(R.id.mTvContainerWeightText);
        mEtContainerNo = findViewById(R.id.mEtContainerNo);
        mEtContainerWeight = findViewById(R.id.mEtContainerWeight);
    }

    @Override
    protected void initData() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        int containerNoText = R.string.text_custom_no;
        int containerWeightText = R.string.text_custom_weight;

        mEtContainerNo.setText("");
        mEtContainerWeight.setText("");

        switch (isContainer) {
            case 2:
                containerNoText = R.string.text_uld_no;
                containerWeightText = R.string.text_uld_weight;

                mEtContainerNo.setTransformationMethod(new InputCapLowerToUpper());
                break;
            case 4:
                containerNoText = R.string.text_tug_no;
                containerWeightText = R.string.text_tug_weight;

                mEtContainerNo.setEnabled(false);
                mEtContainerNo.setBackgroundResource(R.drawable.editor_line_sel);

                if (mTugData != null) {
                    mEtContainerNo.setText(mTugData.getCode());
                    mEtContainerWeight.setText(String.valueOf(mTugData.getWeight()));
                }
                break;
            case 5:
                containerNoText = R.string.text_container_no;
                containerWeightText = R.string.text_container_weight;

                mEtContainerNo.setEnabled(false);
                mEtContainerNo.setBackgroundResource(R.drawable.editor_line_sel);

                if (mContainerData != null) {
                    mEtContainerNo.setText(mContainerData.getCode());
                    mEtContainerWeight.setText(String.valueOf(mContainerData.getWeight()));
                }
                break;
            case 6:
                containerNoText = R.string.text_pallet_no;
                containerWeightText = R.string.text_pallet_weight;

                mEtContainerNo.setEnabled(false);
                mEtContainerNo.setBackgroundResource(R.drawable.editor_line_sel);

                if (mTugData != null) {
                    mEtContainerNo.setText(mTugData.getCode());
                    mEtContainerWeight.setText(String.valueOf(mTugData.getWeight()));
                }
                break;
        }

        mTvContainerNoText.setText(getString(containerNoText));
        mTvContainerWeightText.setText(getString(containerWeightText));

        openKeyBoard(mEtContainerNo);

        setEvent();
    }

    @Override
    protected void initConnect() {
    }

    private void setEvent() {
        mEtContainerNo.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mEtContainerNo.getText().toString()) || mEtContainerNo.getText().toString().endsWith("."))
                    return;
            }
        });

        mEtContainerNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    String count = mEtContainerNo.getText().toString();

                    if (TextUtils.isEmpty(count)) {
                        ToastUtils.showToast(R.string.toast_no_null);
                        return true;
                    } else {
                        mEtContainerWeight.requestFocus();

                        return false;
                    }
                }
                return false;
            }
        });

        mEtContainerWeight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String volume = mEtContainerNo.getText().toString();

                    if (TextUtils.isEmpty(volume) || Double.parseDouble(volume) == 0) {
                        ToastUtils.showToast(R.string.toast_weight_null);
                        return true;
                    } else {
                        return clickFinish();
                    }
                }
                return false;
            }
        });

    }

    /**
     * 点击确定
     *
     * @param view
     * @return
     */
    public void onClick(View view) {
        clickFinish();
    }

    public boolean clickFinish() {
        String mContainerNo = mEtContainerNo.getText().toString();
        String mContainerWeight = mEtContainerWeight.getText().toString();

        if (TextUtils.isEmpty(mContainerNo)) {
            ToastUtils.showToast(R.string.toast_no_null);
            return true;
        } else if (TextUtils.isEmpty(mContainerWeight) || Double.parseDouble(mContainerWeight) == 0) {
            ToastUtils.showToast(R.string.toast_weight_null);
            return true;
        } else if (Double.parseDouble(mContainerWeight) >= 100000) {
            ToastUtils.showToast("重量超出限制，不能大于等于100000！");
            return true;
        } else {
            mBundle = new Bundle();
            mBundle.putString(Constant.Number, CommonUtils.toLowerCase(ConstantInfo.TEMPLE_START + mEtContainerNo.getText().toString()));
            mBundle.putDouble(Constant.Weight, Double.parseDouble(mEtContainerWeight.getText().toString()));
            mBundle.putBoolean(Constant.INFO, mContainerData == null);
            mBundle.putInt(Constant.TurnType, isContainer);
            mEditorCallback.onSubmit(mBundle);
            onFinish();
            return false;
        }
    }

    /**
     * 打开页面
     *
     * @param baseActivity
     * @param editorCallback
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback, int container) {
        Intent intent = new Intent(baseActivity, CustomContainerEditorActivity.class);

        mEditorCallback = editorCallback;
        isContainer = container;
        mContainerData = null;
        mTugData = null;

        baseActivity.turnActForResult(intent);
    }


    /**
     * 打开页面
     *
     * @param baseActivity
     * @param editorCallback
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback, ContainerData containerData, int container) {
        Intent intent = new Intent(baseActivity, CustomContainerEditorActivity.class);

        mEditorCallback = editorCallback;
        isContainer = container;
        mContainerData = containerData;
        mTugData = null;

        baseActivity.turnActForResult(intent);
    }

    /**
     * 打开页面
     *
     * @param baseActivity
     * @param editorCallback
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback, TugData containerData, int container) {
        Intent intent = new Intent(baseActivity, CustomContainerEditorActivity.class);

        mEditorCallback = editorCallback;
        isContainer = container;
        mTugData = containerData;

        baseActivity.turnActForResult(intent);
    }

    public interface EditorCallbackListener {
        void onSubmit(Bundle bundle);
    }

    @Override
    protected void changeSystemUi() {
    }

    @Override
    protected void onDestroy() {
        mEditorCallback = null;
        super.onDestroy();
    }
}
