package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ProjectFragment extends BaseFragment {

    public static ProjectFragment newInstance() {

        Bundle args = new Bundle();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_project;
    }

    @Override
    protected void initView() {
    }


}
