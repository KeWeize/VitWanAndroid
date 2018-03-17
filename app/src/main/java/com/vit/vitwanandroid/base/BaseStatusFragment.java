package com.vit.vitwanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vit.vitwanandroid.widget.vitstatus.VitStatusLayout;

/**
 * @author kewz
 * @date 2018/3/12
 */

public abstract class BaseStatusFragment extends BaseFragment {

    protected VitStatusLayout vitStatusLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        vitStatusLayout = initVitStatusLayout();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 子类提供布局上的VitStatusLayout对象
     *
     * @return
     */
    protected abstract VitStatusLayout initVitStatusLayout();

    /**
     * 显示内容
     */
    protected void showContentView() {
        if (vitStatusLayout != null) {
            vitStatusLayout.showContentView();
        }
    }

    /**
     * 显示进度条
     */
    protected void showLoadingView() {
        if (vitStatusLayout != null) {
            vitStatusLayout.showLoadingView();
        }
    }

    /**
     * 显示空视图
     */
    protected void showEmptyView() {
        if (vitStatusLayout != null) {
            vitStatusLayout.showEmptyView();
        }
    }

    /**
     * 显示错误View
     */
    protected void showErrorView() {
        if (vitStatusLayout != null) {
            vitStatusLayout.showErrorView();
        }
    }

    /**
     * 显示无网络状态
     */
    protected void showNetoff() {
        if (vitStatusLayout != null) {
            vitStatusLayout.showNetoff();
        }
    }

}
