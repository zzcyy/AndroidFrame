package com.creatunion.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/27
 * @version: 1.0
 * @Description:
 */
public class SpUtils {
    
    private static final String FILE_NAME = "laopai";

    public static void setSP(Context context, String filename, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if ("String".equals(type)) {
            edit.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            edit.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            edit.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            edit.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            edit.putLong(key, (Long) object);
        }
        edit.commit();

    }

    public static Object getSp(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public static void cleanAllSP(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
