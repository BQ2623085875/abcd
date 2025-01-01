package com.example.terminal.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.example.terminal.R;
import com.example.terminal.global.MainApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json对象和各个类型之间的互换
 * 
 * @author amulong
 */

public class JsonUtils {
	/**
	 * 把一个map变成json字符串
	 * @param map
	 * @return
	 */
	public static String parseMapToJson(Map<?, ?> map) {
		try {
			Gson gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把一个json字符串变成对象
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T parseJsonToBean(String json, Class<T> cls) {
		Gson gson = new Gson();
		T t = null;
		try {
			t = gson.fromJson(json, cls);
		} catch (Exception e) {
			LogUtils.e(MainApplication.getContext().getResources().getString(R.string.text_change_error), e.getMessage());
		}
		return t;
	}

	/**
	 * 把json字符串变成map
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> parseJsonToMap(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, Object>>() {
		}.getType();
		HashMap<String, Object> map = null;
		try {
			map = gson.fromJson(json, type);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 把json字符串变成集合
	 * params: new TypeToken<List<yourbean>>(){}.getType(),
	 * 
	 * @param json
	 * @param type  new TypeToken<List<yourbean>>(){}.getType()
	 * @return
	 */
	public static List<?> parseJsonToList(String json, Type type) {
		Gson gson = new Gson();
		List<?> list = gson.fromJson(json, type);
		return list;
	}

	/**
	 * 
	 * 获取json串中某个字段的值，注意，只能获取同一层级的value
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getFieldValue(String json, String key) {
		if (TextUtils.isEmpty(json))
			return null;
		if (!json.contains(key))
			return "";
		JSONObject jsonObject = null;
		String value = null;
		try {
			jsonObject = new JSONObject(json);
			value = jsonObject.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String parseBeanToJson(Object cls){
		return new Gson().toJson(cls);
	}

	public static String loadJSONFromAsset(Context context, String fileName) {
		String json = null;
		try {
			AssetManager manager = context.getAssets();
			InputStream inputStream = manager.open(fileName);
			int size = inputStream.available();
			byte[] buffer = new byte[size];
			inputStream.read(buffer);
			inputStream.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException e) {
			LogUtils.e("转换异常", e);
		}
		return json;
	}
}
