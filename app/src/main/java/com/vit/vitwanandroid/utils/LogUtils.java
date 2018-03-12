package com.vit.vitwanandroid.utils;

import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author kewz
 * @date 2018/2/9
 */

public class LogUtils {

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;

    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Type{
    }

    private static final String TAG = "VitProjectLog";

    /**
     * 控制打印开关
     */
    private static boolean ISBUG = true;

    /**
     * 单行最大长度
     */
    private static final int MAX_LEN = 1500;

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void v(String msg) {
        if (ISBUG) {
            printMsg(V, TAG, msg);
        }
    }

    public static void d(String msg) {
        if (ISBUG) {
            printMsg(D, TAG, msg);
        }
    }

    public static void i(String msg) {
        if (ISBUG) {
            printMsg(I, TAG, msg);
        }
    }

    public static void w(String msg) {
        if (ISBUG) {
            printMsg(W, TAG, msg);
        }
    }

    public static void e(String msg) {
        if (ISBUG) {
            printMsg(E, TAG, msg);
        }
    }

    /**
     * 根据 type 打印 msg
     * 当 msg.leng 超过 MAX_LEN 时分行打印
     *
     * @param type
     * @param tag
     * @param msg
     */
    private static void printMsg(@Type final int type, final String tag, final String msg) {
        String message = createMessage(msg);
        int len = message.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            //分行
            int index = 0;
            for (int i=0; i<countOfSub; i++) {
                printSubMsg(type, TAG, message.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                printSubMsg(type, tag, message.substring(index, len));
            }
        } else {
            //不需要换行
            printSubMsg(type, tag, message);
        }
    }

    /**
     * 打印单行msg
     *
     * @param type
     * @param tag
     * @param msg
     */
    private static void printSubMsg(@Type final int type, final String tag, final String msg) {
        if (ISBUG) {
            Log.println(type, tag, msg);
        }
    }

    /**
     * 根据方法轨迹获取调用信息，生成 msg head
     *
     * @return
     */
    private static String getFunctionName() {
        //获取堆栈轨迹对象
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stes) {
            if (ste.isNativeMethod()) {
                //如果是原生方法
                continue;
            }

            if (ste.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (ste.getClassName().equals(LogUtils.class.getName())) {
                //如果是当前类
                continue;
            }

            return new StringBuffer().append("[")
                    .append(Thread.currentThread().getName())
                    .append("(")
                    .append(Thread.currentThread().getId())
                    .append(") ")
                    .append(ste.getFileName())
                    .append(" ")
                    .append(ste.getLineNumber()).append("]").toString();
        }
        return null;
    }

    /**
     * 生成完整的 msg
     *
     * @param msg
     * @return 生成带头部信息的msg
     */
    private static String createMessage(String msg) {
        String functionName = getFunctionName();
        String message = functionName == null ? msg : (functionName + "-" + msg);
        return message;
    }

}
