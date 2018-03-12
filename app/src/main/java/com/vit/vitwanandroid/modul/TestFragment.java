package com.vit.vitwanandroid.modul;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.utils.ToastUtils;
import com.vit.vitwanandroid.widget.VitStatusLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class TestFragment extends BaseStatusFragment {

    @BindView(R.id.vsl_status)
    VitStatusLayout vslStatus;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment;
    }

    @Override
    protected void initView() {
        showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContentView();
                ToastUtils.showShort("数据加载成功");
            }
        }, 3000);
    }

    @Override
    protected VitStatusLayout initVitStatusLayout() {
        return vslStatus;
    }

    @OnClick({R.id.btn_content, R.id.btn_loading, R.id.btn_empty, R.id.btn_error, R.id.btn_netoff})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_content:
                showContentView();
                break;

            case R.id.btn_loading:
                showLoadingView();
                break;

            case R.id.btn_empty:
                showEmptyView();
                break;

            case R.id.btn_error:
                showErrorView();
                break;

            case R.id.btn_netoff:
                showNetoff();
                break;

            default:
                break;
        }
    }
}
