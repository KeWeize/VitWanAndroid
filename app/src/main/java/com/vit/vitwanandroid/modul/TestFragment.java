package com.vit.vitwanandroid.modul;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class TestFragment extends BaseFragment {

    @BindView(R.id.btn_test)
    Button btn;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_test;
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.btn_test})
    public void onViewClick(View view) {

    }

}
