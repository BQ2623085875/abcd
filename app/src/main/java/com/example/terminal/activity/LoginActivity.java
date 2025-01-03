package com.example.terminal.activity;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.LoginBean;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.SharedPreUtils;
import com.example.terminal.util.ToastUtils;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtUserName,// 用户名
            mEtPassword;//密码
    private ImageButton mIbClearUser,//清除
            mIbDisplayPassword;//显示密码

    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mEtUserName = findViewById(R.id.mEtUserName);
        mEtPassword = findViewById(R.id.mEtPassword);
        mIbClearUser = findViewById(R.id.mIbClearUser);
        mIbDisplayPassword = findViewById(R.id.mIbDisplayPassword);

        findViewById(R.id.Login_Background_LinearLayout).setLayoutParams(new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.getScreenHeight((Activity) mActivity) / 2));

        mEtUserName.setText("admin");
        mEtPassword.setText("admin123");

        String token = SharedPreUtils.getString("TOKEN");
        if (!token.equals("")) {
            ConstantInfo.TOKEN = token;
            turnToAct(MainActivity.class);
        }
    }

    @Override
    protected void initData() {
        mEtUserName.addTextChangedListener(new OnEditTextWatcher(mIbClearUser));
        mEtPassword.addTextChangedListener(new OnEditTextWatcher(mIbDisplayPassword));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ConstantInfo.TOKEN != null) {
            turnToAct(MainActivity.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIbClearUser:
                if (CommonUtils.isFastDoubleClick())
                    return;
                // 清除
                mEtUserName.setText("");
                mEtPassword.setText("");
                break;
            case R.id.mIbDisplayPassword:
                if (CommonUtils.isFastDoubleClick())
                    return;
                // 显示隐藏/密码
                if (mEtPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                    // 隐藏
                    mEtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mIbDisplayPassword.setImageDrawable(getDrawable(R.drawable.selector_hide_button));
                } else {
                    // 隐藏
                    mEtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mIbDisplayPassword.setImageDrawable(getDrawable(R.drawable.selector_display_login));
                }
                mEtPassword.setSelection(mEtPassword.getText().toString().length());
                break;
            case R.id.mBtnLogin:
                // 登录
                clickLogin();
                break;
        }
    }

    private void clickLogin() {
        String userName = mEtUserName.getText().toString();
        String password = mEtPassword.getText().toString();

        if (CommonUtils.isSixClick() && TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
            userName = "admin";
            password = "admin123";
            ToastUtils.showToast("测试账号，请勿使用。");
        }

        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showToast(R.string.toast_user_empty);
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast(R.string.toast_password_empty);
        } else {
            valueMap = new HashMap<>();
            valueMap.put(Constant.USER_NAME, userName);
            valueMap.put(Constant.PASSWORD, password);

            String finalUserName = userName;
            NetworkUtils.login(new OkGoBackListener(mActivity) {
                @Override
                public void onSuccess(Object body) {
                    LoginBean loginBean = (LoginBean) body;
                    ConstantInfo.TOKEN = loginBean.getToken();
                    ConstantInfo.USER_NAME = finalUserName;
                    SharedPreUtils.putString("TOKEN", loginBean.getToken());
                    turnToAct(MainActivity.class);
                }
            }, valueMap);
        }
    }

    /**
     * 控制显示隐藏
     */
    private class OnEditTextWatcher extends EditTextWatcher {
        private ImageButton imageButton;

        public OnEditTextWatcher(ImageButton imageButton) {
            this.imageButton = imageButton;
        }

        @Override
        public void afterTextChanged(Editable s) {
            imageButton.setVisibility(TextUtils.isEmpty(s.toString()) ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void changeSystemUi() {
        CommonUtils.changeSystemUi(mActivity);
    }

    @Override
    public void onBackPressed() {
        onBackDoubleClick();
    }


}
