package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.widget.VitStatusLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class HomeFragment extends BaseStatusFragment {

    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;

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
    protected VitStatusLayout initVitStatusLayout() {
        return statusLayout;
    }

    @Override
    protected void initView() {
        showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContentView();
            }
        }, 3000);
    }

}
