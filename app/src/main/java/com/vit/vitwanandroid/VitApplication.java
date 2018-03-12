package com.vit.vitwanandroid;

import android.app.Application;
import android.content.Context;

import com.vit.vitwanandroid.utils.VitApp;

/**
 * @author kewz
 * @date 2018/3/12
 */

public class VitApplication extends Application {

    private static Context context;
    public static int widthPixels;
    public static int heightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        VitApp.init(this);
        context = getContext();
        initPixels();
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 初始化屏幕像素
     */
    private void initPixels() {
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
    }

}
