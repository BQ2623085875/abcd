package com.example.terminal.listener;

import android.text.TextWatcher;

/**
 * 监听输入框内容辩护
 */
public abstract class EditTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
