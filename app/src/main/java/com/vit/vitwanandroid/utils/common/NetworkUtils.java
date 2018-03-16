package com.vit.vitwanandroid.utils.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author kewz
 * @date 2018/2/9
 *
 * 网络相关工具类
 */

public class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     * </p>
     */
    @SuppressLint("MissingPermission")
    private static NetworkInfo getActiveNetworkInfo() {
        //获取系统连接服务
        ConnectivityManager manager =
                (ConnectivityManager) VitApp.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return null;
        }
        return manager.getActiveNetworkInfo();
    }

    /**
     * 网络是否连接
     * 需添加权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     *
     * @return
     */
    public static boolean isConnected() {
        NetworkInfo networkInfo = getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



}
