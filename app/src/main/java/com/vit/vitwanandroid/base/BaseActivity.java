package com.vit.vitwanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author kewz
 * @date 2018/3/8
 */

public abstract class BaseActivity extends FragmentActivity {

    View rootView;
    Unbinder unbinder;
    //沉浸式操作对象
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(getContentLayout(), null);
        setContentView(rootView);
        //黄牛刀配置
        unbinder = ButterKnife.bind(this);
        //初始化状态栏
        if (isInitImmersionBar()) {
            initImmersionBar();
        }
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 初始化状态栏操作对象
     */
    private void initImmersionBar() {
        mImmersionBar = ImmersionBar
                .with(this)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f);
        mImmersionBar.init();
    }

    /**
     * 是否初始状态栏操作对象
     *
     * @return
     */
    protected boolean isInitImmersionBar() {
        return true;
    }

    protected abstract int getContentLayout();

    protected abstract void initView();

}
