package com.example.terminal.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.terminal.global.Constant;
import com.example.terminal.global.MainApplication;

/**
 *  @author : YanKing
 *  @date : 2019/5/22  10:35
 *  @Description : SharedPreferences并存取不同的数据
 */

public class SharedPreUtils {

	private static final String SP_NAME = Constant.SP_NAME ;
	private static SharedPreferences sp;

	/**
	 * 取布尔值
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defValue) {
		if (sp == null)
			sp = getSp();
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 取布尔值
	 *
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		if (sp == null)
			sp = getSp();
		return sp.getBoolean(key, false);
	}

	/**
	 * 存布尔值
	 * 
	 * @param key
	 * @param value
	 */
	public static void putBoolean(String key, boolean value) {
		if (sp == null)
			sp = getSp();
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 取字符串
	 *
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		if (sp == null)
			sp = getSp();
		return sp.getString(key, null);
	}

	/**
	 * 存储字符串
	 * 
	 * @param key
	 * @param value
	 */
	public static void putString(String key, String value) {
		if (sp == null)
			sp = getSp();
		sp.edit().putString(key, value).commit();
	}

	/**
	 * 取字长数字
	 *
	 * @param key
	 * @return
	 */
	public static long getLong(String key) {
		if (sp == null)
			sp = getSp();
		return sp.getLong(key ,-1);
	}

	/**
	 * 获取数字
	 *
	 * @param key
	 * @return
	 */
	public static int getInteger(String key) {
		if (sp == null)
			sp = getSp();
		return sp.getInt(key ,-1);
	}

	/**
	 * 存储字符串
	 *
	 * @param key
	 * @param value
	 */
	public static void putLong(String key, long value) {
		if (sp == null)
			sp = getSp();
		sp.edit().putLong(key, value).commit();
	}

	/**
	 * 存储整数
	 *
	 * @param key
	 */
	public static void putInteger(String key) {
		if (sp == null)
			sp = getSp();
		sp.edit().putInt(key, -1).commit();
	}

	/**
	 * 存储整数
	 * 
	 * @param key
	 * @param value
	 */
	public static void putInteger(String key, int value) {
		if (sp == null)
			sp = getSp();
		sp.edit().putInt(key, value).commit();
	}

	private static SharedPreferences getSp(){
		return MainApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE) ;
	}
}
