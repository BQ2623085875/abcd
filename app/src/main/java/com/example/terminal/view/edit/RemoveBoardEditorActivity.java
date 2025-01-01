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
import com.example.terminal.global.Constant;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ToastUtils;

/**
 * 拆板操作
 */

public class RemoveBoardEditorActivity extends BaseActivity {

    private EditText mEtCount,mEtWidget,mEtVolume;
    private static EditorCallbackListener mEditorCallback;
    private static int mCount ,mPosition;
    private static double mWidget , mVolume;

    @Override
    protected int setContentView() {
        return R.layout.editor_remove_board;
    }


    protected void initView() {
        mEtCount = findViewById(R.id.mEtCount);
        mEtWidget = findViewById(R.id.mEtWeight);
        mEtVolume = findViewById(R.id.mEtVolume);
    }

    @Override
    protected void initData() {
        mEtCount.setHint(String.valueOf(mCount));
        mEtWidget.setHint(String.valueOf(mWidget));
        mEtVolume.setHint(CommonUtils.getDoubleFour(mVolume));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        openKeyBoard(mEtCount);

        setEvent() ;
    }

    private void setEvent() {
        mEtCount.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(mEtCount.getText().toString()) || mEtCount.getText().toString().endsWith("."))
                    return;

                if(Integer.parseInt(mEtCount.getText().toString()) > mCount) {
                    mEtCount.setText(mEtCount.getText().toString().substring(0, mEtCount.getText().toString().length() - 1));
                    mEtCount.setSelection(mEtCount.getText().toString().length());
                    ToastUtils.showToast(R.string.toast_board_count_min);
                }
            }
        });

        mEtWidget.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(mEtWidget.getText().toString()))
                    return;

                if(Double.parseDouble(mEtWidget.getText().toString()) > mWidget) {
                    mEtWidget.setText(mEtWidget.getText().toString().substring(0, mEtWidget.getText().toString().length() - 1));
                    mEtWidget.setSelection(mEtWidget.getText().toString().length());
                    ToastUtils.showToast(R.string.toast_board_weight_min);
                }
            }
        });

        mEtVolume.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(mEtVolume.getText().toString()) || mEtVolume.getText().toString().endsWith("."))
                    return;

                if(Double.parseDouble(mEtVolume.getText().toString()) > mVolume) {
                    mEtVolume.setText(mEtVolume.getText().toString().substring(0, mEtVolume.getText().toString().length() - 1));
                    mEtVolume.setSelection(mEtVolume.getText().toString().length());
                    ToastUtils.showToast(R.string.toast_board_volume_min);
                }
            }
        });

        mEtCount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    String count = mEtCount.getText().toString();

                    if(TextUtils.isEmpty(count) || Integer.parseInt(count) == 0){
                        ToastUtils.showToast(R.string.toast_board_count_null);
                        return true;
                    }else {
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
                if(actionId == EditorInfo.IME_ACTION_NEXT) {
                    String wight = mEtWidget.getText().toString();

                    if(TextUtils.isEmpty(wight) || Double.parseDouble(wight) == 0){
                        ToastUtils.showToast(R.string.toast_board_weight_null);
                        return true;
                    }else {
                        return clickFinish();
//                        mEtVolume.requestFocus();
//                        return false;
                    }
                }
                return false;
            }
        });

        mEtVolume.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    String volume = mEtVolume.getText().toString();

                    if(TextUtils.isEmpty(volume) || Double.parseDouble(volume) == 0){
                        ToastUtils.showToast(R.string.toast_board_volume_null);
                        return true;
                    }else {
                        return clickFinish();
                    }
                }
                return false;
            }
        });
    }

    /**
     * 点击确定
     * @param view
     * @return
     */
    public void onClick(View view){
        clickFinish();
    }

    public boolean clickFinish(){
        if(TextUtils.isEmpty(mEtCount.getText().toString()) || Integer.parseInt(mEtCount.getText().toString()) == 0){
            ToastUtils.showToast(R.string.toast_board_count_null);
            return true;
        }else if(TextUtils.isEmpty(mEtWidget.getText().toString()) || Double.parseDouble(mEtWidget.getText().toString()) == 0){
            ToastUtils.showToast(R.string.toast_board_weight_null);
            return true;
        }
        else if(Double.parseDouble(mEtCount.getText().toString()) == mCount && (Double.parseDouble(mEtWidget.getText().toString()) != mWidget)){
            ToastUtils.showToast(R.string.toast_board_count_null_weight);
            return true;
        }else if((Double.parseDouble(mEtCount.getText().toString()) != mCount  && Double.parseDouble(mEtWidget.getText().toString()) == mWidget)){
            ToastUtils.showToast(R.string.toast_board_weight_null_count);
            return true;
        }else {
            mBundle = new Bundle();
            mBundle.putInt(Constant.Count , Integer.parseInt(mEtCount.getText().toString()));
            mBundle.putDouble(Constant.Weight , Double.parseDouble(mEtWidget.getText().toString()));
            mBundle.putDouble(Constant.Volume , 0);
            mBundle.putInt(Constant.Position , mPosition);
            mEditorCallback.onSubmit(mBundle);
            onFinish();
            return false;
        }
    }

    /**
     * 打开页面
     * @param baseActivity
     * @param editorCallback
     * @param bundle
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback , Bundle bundle){
        Intent intent = new Intent(baseActivity, RemoveBoardEditorActivity.class);

        mEditorCallback = editorCallback;
        mCount = bundle.getInt(Constant.Count) ;
        mWidget = bundle.getDouble(Constant.Weight) ;
        mVolume = bundle.getDouble(Constant.Volume) ;
        mPosition = bundle.getInt(Constant.Position);

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
