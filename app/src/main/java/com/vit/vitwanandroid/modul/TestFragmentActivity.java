package com.vit.vitwanandroid.modul;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseActivity;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class TestFragmentActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.base_activity_simple;
    }

    @Override
    protected void initView() {
        Fragment fragment = TestFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.add(R.id.fl_container, fragment, "FRAGMENT");
        transaction.commitAllowingStateLoss();
    }
}
