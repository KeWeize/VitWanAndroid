package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {
    }


}
