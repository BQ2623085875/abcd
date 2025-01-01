package com.example.terminal.listener;

/**
 * author : King
 * date   : 2022/5/3015:23
 * desc   :
 */

import android.text.method.ReplacementTransformationMethod;

public class InputCapLowerToUpper extends ReplacementTransformationMethod {
    @Override
    protected char[] getOriginal() {
        char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
        return lower;
    }

    @Override
    protected char[] getReplacement() {
        char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
        return upper;
    }
}

