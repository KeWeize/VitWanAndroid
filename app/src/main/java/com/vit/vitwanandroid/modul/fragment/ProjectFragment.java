package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.widget.VitStatusLayout;

import butterknife.BindView;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ProjectFragment extends BaseStatusFragment {

    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;

    private ImmersionBar immersionBar;

    public static ProjectFragment newInstance() {

        Bundle args = new Bundle();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersionBar = ImmersionBar.with(getActivity(), this)
                //使用该属性,必须指定状态栏颜色
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .addTag(TAG);
        immersionBar.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_project;
    }

    @Override
    protected void initView() {
        showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showNetoff();
            }
        }, 2000);
    }

    @Override
    protected VitStatusLayout initVitStatusLayout() {
        return statusLayout;
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
