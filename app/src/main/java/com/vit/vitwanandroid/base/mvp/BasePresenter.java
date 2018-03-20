package com.vit.vitwanandroid.base.mvp;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author kewz
 * @date 2018/3/19
 */

public abstract class BasePresenter<V> {

    protected Context mContext;
    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void attachView(V view, Context mContext) {
        mViewRef = new WeakReference<V>(view);
        this.mContext = mContext;
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
