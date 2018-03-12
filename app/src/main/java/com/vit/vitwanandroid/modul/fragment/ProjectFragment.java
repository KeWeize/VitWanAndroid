package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

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

    public static ProjectFragment newInstance() {

        Bundle args = new Bundle();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
