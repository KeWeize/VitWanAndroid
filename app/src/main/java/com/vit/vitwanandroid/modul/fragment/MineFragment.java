package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initView() {
    }


}
