package com.vit.vitwanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author kewz
 * @date 2018/3/8
 */

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    private static final String ISLAZYMODE = "ls_lazy_mode";
    private static final String ISPREPARED = "ls_prepared";

    protected Context mContext;
    private View rootView;
    private Unbinder unbinder;

    /**
     * 是否需要懒加载
     */
    private boolean isLazyMode;

    /**
     * 控件是否准备好（已初始化）
     * 由于懒加载基于setUserVisibleHint(boolean isVisibleToUser) 实现，
     * 而setUserVisibleHint 方法先于onCreateView 执行，需要isPrepared 来标志当前是否准备好执行懒加载的逻辑
     * isPrepared 为protected 修饰，需要子类手动更改
     */
    protected boolean isPrepared = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isLazyMode = savedInstanceState.getBoolean(ISLAZYMODE);
            isPrepared = savedInstanceState.getBoolean(ISPREPARED);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //加入统计等代码
    }

    @Override
    public void onPause() {
        super.onPause();
        //加入统计等代码
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        //加入统计等代码
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isLazyMode) {
            if (getUserVisibleHint()) {
                onFragmentVisible();
            } else {
                onFragmentInvisible();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ISLAZYMODE, isLazyMode);
        outState.putBoolean(ISPREPARED, isPrepared);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void onFragmentVisible() {
        //懒加载模式下，Fragment可见。由子类实现
        lazyLoad(isPrepared);
    }

    protected void onFragmentInvisible() {
        //懒加载模式下，Fragment可见。由子类实现
    }

    protected void lazyLoad(boolean isPrepared) {
        //执行懒加载逻辑
    }


    /**-------------------- 公共方法 start ---------------------**/

    protected void showShortToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    protected void showLongToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
    }

}
