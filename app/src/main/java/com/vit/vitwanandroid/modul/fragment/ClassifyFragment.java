package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ClassifyFragment extends BaseFragment {

    public static ClassifyFragment newInstance() {

        Bundle args = new Bundle();

        ClassifyFragment fragment = new ClassifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_classify;
    }

    @Override
    protected void initView() {
    }


}
