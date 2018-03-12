package com.vit.vitwanandroid.utils;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * @author kewz
 * @date 2018/2/9
 *
 * 在程序Application中调用，用于初始化Application对象。
 */

public final class VitApp {

    @SuppressWarnings("StaticFieldLeck")
    private static Application application;

    private VitApp() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param application
     */
    public static void init(@NonNull final Application application) {
        VitApp.application = application;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Application getApp() {
        if (application != null) {
            return application;
        }
        throw new NullPointerException("u should init first");
    }

}
