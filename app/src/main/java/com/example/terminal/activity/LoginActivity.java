package com.example.terminal.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.bean.LoginBean.LoginData;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ToastUtils;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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

    }


    @Override
    public void onBackPressed() {
        onBackDoubleClick();
    }



}
