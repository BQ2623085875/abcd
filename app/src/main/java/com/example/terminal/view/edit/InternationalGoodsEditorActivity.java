package com.example.terminal.view.edit;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.terminal.global.Constant;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ToastUtils;

/**
 * 拆板操作
 */

public class InternationalGoodsEditorActivity extends BaseActivity {

    private EditText mEtCount, mEtWidget, mEtLength, mEtWide, mEtHeight;
    private TextView mTvLengthCm, mTvWideCm, mTvHeightCm;


    private static double length, width, height;
    private static EditorCallbackListener mEditorCallback;
    private static int mCount;
    private static double mWidget;
    private static int mPosition;

    @Override
    protected int setContentView() {
        return R.layout.international_editor_goods;
    }


    protected void initView() {
        mEtCount = findViewById(R.id.mEtCount);
        mEtWidget = findViewById(R.id.mEtWeight);


        mEtLength = findViewById(R.id.mEtLength);
        mEtWide = findViewById(R.id.mEtWide);
        mEtHeight = findViewById(R.id.mEtHeight);
        mTvLengthCm = findViewById(R.id.mTvLengthCm);
        mTvWideCm = findViewById(R.id.mTvWideCm);
        mTvHeightCm = findViewById(R.id.mTvHeightCm);
    }

    @Override
    protected void initData() {
        if (mCount != 0)
            mEtCount.setText(String.valueOf(mCount));
        if (mWidget != 0)
            mEtWidget.setText(String.valueOf(mWidget));

        if (length != 0)
            mEtLength.setText(String.valueOf(CommonUtils.getDouble(length * 100)));
        if (width != 0)
            mEtWide.setText(String.valueOf(CommonUtils.getDouble(width * 100)));
        if (height != 0)
            mEtHeight.setText(String.valueOf(CommonUtils.getDouble(height * 100)));


        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        CommonUtils.setScreenViewVisible(mTvLengthCm);
        CommonUtils.setScreenViewVisible(mTvWideCm);
        CommonUtils.setScreenViewVisible(mTvHeightCm);


        openKeyBoard(mEtCount);

        setEvent();
    }

    private void setEvent() {
        mEtCount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (TextUtils.isEmpty(mEtCount.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_count_null);
                        return true;
                    } else {
                        mEtWidget.requestFocus();
                        return false;
                    }
                }
                return false;
            }
        });

        mEtWidget.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (TextUtils.isEmpty(mEtCount.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_count_null);
                        return true;
                    } else if (TextUtils.isEmpty(mEtWidget.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_weight_null);
                        return true;
                    } else {
                        return clickFinish();
                    }
                }
                return false;
            }
        });


        mEtLength.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (TextUtils.isEmpty(mEtCount.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_count_null);
                        return true;
                    } else if (TextUtils.isEmpty(mEtWidget.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_weight_null);
                        return true;
                    } else if (TextUtils.isEmpty(mEtLength.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_length_null);
                        return true;
                    } else {
                        mEtWide.requestFocus();
                        return false;
                    }
                }
                return false;
            }
        });

        mEtWide.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (TextUtils.isEmpty(mEtCount.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_count_null);
                        return true;
                    } else if (TextUtils.isEmpty(mEtWidget.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_weight_null);
                        return true;
                    } else if (TextUtils.isEmpty(mEtLength.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_length_null);
                        return true;
                    } else if (TextUtils.isEmpty(mEtWide.getText().toString())) {
                        ToastUtils.showToast(R.string.toast_wide_null);
                        return true;
                    } else {
                        mEtHeight.requestFocus();
                        return false;
                    }
                }
                return false;
            }
        });

        mEtHeight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    return clickFinish();
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
        if (TextUtils.isEmpty(mEtCount.getText().toString()) || Integer.parseInt(mEtCount.getText().toString()) == 0) {
            ToastUtils.showToast(R.string.toast_count_null);
            return true;
        } else if (TextUtils.isEmpty(mEtWidget.getText().toString())) {
            ToastUtils.showToast(R.string.toast_weight_null);
            return true;
        } else if (TextUtils.isEmpty(mEtLength.getText().toString()) || Double.parseDouble(mEtLength.getText().toString()) == 0) {
            ToastUtils.showToast(R.string.toast_length_null);
            return true;
        } else if (TextUtils.isEmpty(mEtWide.getText().toString()) || Double.parseDouble(mEtWide.getText().toString()) == 0) {
            ToastUtils.showToast(R.string.toast_wide_null);
            return true;
        } else if (TextUtils.isEmpty(mEtHeight.getText().toString()) || Double.parseDouble(mEtHeight.getText().toString()) == 0) {
            ToastUtils.showToast(R.string.toast_height_null);
            return true;
        } else {
            mBundle = new Bundle();
            mBundle.putInt(Constant.Position, mPosition);
            mBundle.putInt(Constant.Count, Integer.parseInt(mEtCount.getText().toString()));
            mBundle.putDouble(Constant.Weight, CommonUtils.getDouble(Double.parseDouble(mEtWidget.getText().toString())));
            mBundle.putDouble(Constant.Length, CommonUtils.getDouble(Double.parseDouble(mEtLength.getText().toString()) / 100));
            mBundle.putDouble(Constant.Width, CommonUtils.getDouble(Double.parseDouble(mEtWide.getText().toString()) / 100));
            mBundle.putDouble(Constant.Height, CommonUtils.getDouble(Double.parseDouble(mEtHeight.getText().toString()) / 100));

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
     * @param bundle
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback, Bundle bundle) {
        Intent intent = new Intent(baseActivity, InternationalGoodsEditorActivity.class);

        mEditorCallback = editorCallback;
        mCount = bundle.getInt(Constant.Count);
        mWidget = bundle.getDouble(Constant.Weight);
        mPosition = bundle.getInt(Constant.Position);

        length = bundle.getDouble(Constant.Length);
        width = bundle.getDouble(Constant.Width);
        height = bundle.getDouble(Constant.Height);

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
