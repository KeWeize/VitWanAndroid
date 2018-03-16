package com.vit.vitwanandroid.utils.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.support.v4.widget.TextViewCompat;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author kewz
 * @date 2018/2/9
 */

public class ToastUtils {

    /**
     * 使用主线程的Looper对象构造Handler（此handler发送的消息都由主线程looper实例处理）
     */
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private static Toast sToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showShort(final CharSequence charSequence) {
        show(charSequence, Toast.LENGTH_SHORT);
    }

    public static void showLong(final CharSequence charSequence) {
        show(charSequence, Toast.LENGTH_LONG);
    }

    public static void show(@StringRes final int resId, final int duration) {
        show(VitApp.getApp().getResources().getText(resId), duration);
    }

    /**
     * 显示toast
     *
     * @param charSequence
     * @param duration
     */
    private static void show(final CharSequence charSequence, final int duration) {
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                sToast = Toast.makeText(VitApp.getApp(), charSequence, duration);
                // solve the font appearance
                TextView tvMessage = sToast.getView().findViewById(android.R.id.message);
                TextViewCompat.setTextAppearance(tvMessage, android.R.style.TextAppearance);
                sToast.show();
            }
        });
    }

    /**
     * 取消当前显示toast
     */
    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

}
