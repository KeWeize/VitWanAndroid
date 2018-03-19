package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class MineFragment extends BaseFragment {

    private ImmersionBar immersionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersionBar = ImmersionBar.with(getActivity(), this)
                .transparentStatusBar()
                .addTag(TAG);
        immersionBar.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initView() {
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
