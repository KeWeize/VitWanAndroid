package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.os.Handler;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.widget.VitStatusLayout;

import butterknife.BindView;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ClassifyFragment extends BaseStatusFragment {

    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;

    public static ClassifyFragment newInstance() {

        Bundle args = new Bundle();

        ClassifyFragment fragment = new ClassifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected VitStatusLayout initVitStatusLayout() {
        return statusLayout;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_classify;
    }

    @Override
    protected void initView() {
        showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showEmptyView();
            }
        }, 2000);
    }

}
