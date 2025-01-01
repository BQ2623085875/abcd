package com.example.terminal.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.terminal.BuildConfig;

/**
 * 用于封装Log打印的方式
 *
 * @author amulong
 */

public class LogUtils {

    private static final boolean isDeBug = BuildConfig.DEBUG;

    /**
     * 根据传入进来的字符串打印e级别的Log
     *
     * @param key
     * @param value
     */
    public static void e(String key, String value) {
        longE(key, value);
    }

    /**
     * 根据传入进来的整数打印e级别的Log
     *
     * @param key
     * @param value
     */
    public static void e(String key, int value) {
        longE(key, String.valueOf(value));
    }

    /**
     * 根据传入进来的布尔值打印e级别的Log
     *
     * @param key
     * @param value
     */
    public static void e(String key, boolean value) {
        longE(key, String.valueOf(value));
    }

    /**
     * 根据传入进来的浮点数打印e级别的Log
     *
     * @param key
     * @param value
     */
    public static void e(String key, float value) {
        longE(key, String.valueOf(value));
    }

    /**
     * 根据传入进来的整数打印e级别的Log
     *
     * @param key
     * @param value
     */
    public static void e(Object key, Object value) {
        longE(String.valueOf(key), String.valueOf(value));
    }

    /**
     * 根据传入进来的字符串打印e级别的Log
     */
    private static void longE(String key, String value) {
        if (isDeBug)
            Log.e(key, TextUtils.isEmpty(value) ? "数据为空" : value);
    }

    /**
     * 打印带行数的Log
     *
     * @param info
     */
    public static void e(String info) {
        if (isDeBug) {
            String[] infoArray = getAutoJumpLogInfo();
            if(!TextUtils.isEmpty(info)) {
                Log.e(infoArray[0] + "类中" + infoArray[2] + "行", info);
            }else{
                Log.e(infoArray[0] + "类中" + infoArray[2] + "行", "打印数据为空");
            }
        }
    }

    /**
     * 打印带行数的Log
     *
     * @param info
     */
    public static void e(int info) {
        e(String.valueOf(info));
    }

    /**
     * 打印带行数的Log
     *
     * @param info
     */
    public static void e(Object info) {
        e(String.valueOf(info));
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return
     */
    private static String[] getAutoJumpLogInfo() {
        String[] infoArray = new String[]{"", "", ""};
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length < 5) {
            Log.e("LogUtils", "Stack is too shallow!!!");
            return infoArray;
        } else {
            infoArray[0] = elements[4].getClassName().substring(
                    elements[4].getClassName().lastIndexOf(".") + 1);
            infoArray[1] = elements[4].getMethodName() + "()";
            infoArray[2] = String.valueOf(elements[4].getLineNumber());
            return infoArray;
        }
    }
}
