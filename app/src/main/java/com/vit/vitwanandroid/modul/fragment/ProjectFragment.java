package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.widget.vitstatus.VitStatusLayout;

import butterknife.BindView;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ProjectFragment extends BaseFragment {

    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;

    private ImmersionBar immersionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_project;
    }

    @Override
    protected void initView() {
        immersionBar = ImmersionBar.with(getActivity(), this)
                //使用该属性,必须指定状态栏颜色
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .addTag(TAG);
        immersionBar.init();
        statusLayout.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusLayout.showNetoff();
            }
        }, 2000);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && immersionBar != null) {
            immersionBar.getTag(TAG)
                    .init();
        }
    }
}
