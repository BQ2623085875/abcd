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
import com.example.terminal.util.ToastUtils;

/**
 * 拆板操作
 */
public class EditorOrderNoActivity extends BaseActivity {

    private EditText mEtNumber ;
    private static EditorCallbackListener mEditorCallback;
    private static String mContent ;

    @Override
    protected int setContentView() {
        return R.layout.editor_order_no_view;
    }


    protected void initView() {
        mEtNumber = findViewById(R.id.mEtNumber);
    }

    @Override
    protected void initData() {
        if(!TextUtils.isEmpty(mContent))
            mEtNumber.setHint(mContent);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        openKeyBoard(mEtNumber);

        setEvent() ;
    }

    private void setEvent() {
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
        String content = mEtNumber.getText().toString();

        if(TextUtils.isEmpty(content) && TextUtils.isEmpty(mContent)){
            ToastUtils.showToast(R.string.toast_content_null);
            return true;
        }else {
            if(mEditorCallback != null)
                mEditorCallback.onSubmit(TextUtils.isEmpty(content) ? mContent : content);

            onFinish();
            return false;
        }
    }

    /**
     * 打开页面
     * @param baseActivity
     * @param editorCallback
     */
    public static void openActivity(BaseActivity baseActivity, EditorCallbackListener editorCallback , String content){
        Intent intent = new Intent(baseActivity, EditorOrderNoActivity.class);

        mEditorCallback = editorCallback;
        mContent = content;

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
