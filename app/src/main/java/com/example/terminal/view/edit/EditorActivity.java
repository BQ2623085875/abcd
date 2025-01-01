package com.example.terminal.view.edit;

import android.content.Intent;
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

/**
 * 只有一个输入框
 */
public class EditorActivity extends BaseActivity {

    private EditText mEtNumber , mEtContent;
    private static EditorCallbackListener mEditorCallback;
    private static String mContent ;
    private static boolean IsNumberInput = true;

    @Override
    protected int setContentView() {
        return R.layout.editor_view;
    }


    protected void initView() {
        mEtNumber = findViewById(R.id.mEtNumber);
        mEtContent = findViewById(R.id.mEtContent);
    }

    @Override
    protected void initData() {
        mEtNumber.setVisibility(IsNumberInput ? View.VISIBLE : View.GONE);
        mEtContent.setVisibility(IsNumberInput ? View.GONE : View.VISIBLE);

        if(!TextUtils.isEmpty(mContent)) {
            mEtNumber.setHint(mContent);
            mEtContent.setHint(mContent);
        }

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        openKeyBoard(mEtNumber);
        openKeyBoard(mEtContent);

        setEvent() ;
    }

    private void setEvent() {
        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    return clickFinish();
                }
                return false;
            }
        });
        mEtNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    return clickFinish();
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
        String content = (IsNumberInput ? mEtNumber : mEtContent).getText().toString();

        if(mEditorCallback != null)
            mEditorCallback.onSubmit(TextUtils.isEmpty(content) ? "" : content);

        onFinish();
        return false;
    }

    /**
     * 打开页面
     * @param baseActivity
     * @param editorCallback
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback , String content){
        Intent intent = new Intent(baseActivity, EditorActivity.class);

        IsNumberInput = true;
        mEditorCallback = editorCallback;
        mContent = content;

        baseActivity.turnActForResult(intent);
    }

    /**
     * 打开页面
     * @param baseActivity
     * @param editorCallback
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback , String content , boolean isNumberInput){
        Intent intent = new Intent(baseActivity, EditorActivity.class);
        mEditorCallback = editorCallback;
        mContent = content;
        IsNumberInput = isNumberInput;

        baseActivity.turnActForResult(intent);
    }

    public interface EditorCallbackListener {
        void onSubmit(String content);
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
