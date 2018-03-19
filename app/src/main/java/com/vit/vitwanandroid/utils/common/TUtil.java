package com.vit.vitwanandroid.utils.common;

import android.util.Log;

import java.lang.reflect.ParameterizedType;

/**
 * 类转换初始化
 */
public class TUtil {
    private static String TAG = "TUtil";

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            Log.i(TAG, "InstantiationException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            Log.i(TAG, "IllegalAccessException: " + e.getMessage());
        } catch (ClassCastException e) {
            Log.i(TAG, "ClassCastException: " + e.getMessage());
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
