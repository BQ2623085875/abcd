package com.example.terminal.util;

import android.widget.Toast;

import com.example.terminal.global.MainApplication;

/**
 * 打印强大的Toast，不用等待上一个Toast消失就打印新一个Toast 可以连续不断的打印Toast
 *
 * @author amulong
 */

public class ToastUtils {

    private static Toast toast;

    /**
     * 字符串显示短时间的Toast
     *
     * @param mString 字符串
     */
    public static void showToast(String mString) {
        show(mString, Toast.LENGTH_SHORT);
    }

    /**
     * 整数显示段时间的Toast
     *
     * @param resources ID
     */
    public static void showToast(int resources) {
        show(MainApplication.getContext().getResources().getString(resources), Toast.LENGTH_SHORT);
    }

    /**
     * 任意类型显示短时间的Toast
     *
     * @param mObject 任意类型
     */
    public static void showToast(Object mObject) {
        show(mObject != null ? mObject.toString() : "空", Toast.LENGTH_SHORT);
    }

    /**
     * 字符串显示长时间的Toast
     *
     * @param mString
     */
    public static void showLongToast(String mString) {
        show(mString, Toast.LENGTH_LONG);
    }

    /**
     * 整数显示长时间的Toast
     *
     * @param resources ID
     */
    public static void showLongToast(int resources) {
        show(MainApplication.getContext().getResources().getString(resources), Toast.LENGTH_LONG);
    }

    /**
     * 任意类型显示短时间的Toast
     *
     * @param mObject 任意类型
     */
    public static void showLongToast(Object mObject) {
        show(mObject != null ? mObject.toString() : "空", Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    private static void show(String msg, int duration) {

        if (toast == null)
            toast = Toast.makeText(MainApplication.getContext(), msg, duration);

        toast.setText(msg);

        toast.show();
    }
}
