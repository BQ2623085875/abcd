package com.example.terminal.util;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.example.terminal.global.MainApplication;

/**
 * 通过代码完成dp与px的相互转换
 * 1.dp转px
 * 2.px转dp
 */
public class DensityUtils {
    /**
     * dp转px
     * @param dip       dp
     * @return
     */
    public static int dip2px(float dip) {
        float density = MainApplication.getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);// 4.9->4, 4.1->4, 四舍五入
        return px;
    }

    /**
     * px转dp
     * @param px        px
     * @return
     */
    public static float px2dip(int px) {
        float density = MainApplication.getContext().getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

    /**
     * 得到屏幕宽度
     *
     * @return 宽度
     */
    public static int getScreenWidth(Activity mActivity) {
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    /**
     * 得到屏幕高度
     *
     * @return 高度
     */
    public static int getScreenHeight(Activity mActivity) {
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }
}

