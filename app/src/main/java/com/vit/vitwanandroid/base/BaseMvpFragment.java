package com.vit.vitwanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vit.vitwanandroid.base.mvp.BaseView;
import com.vit.vitwanandroid.base.mvp.BasePresenter;
import com.vit.vitwanandroid.utils.common.TUtil;

/**
 * @author kewz
 * @date 2018/3/19
 */

public abstract class BaseMvpFragment<P extends BasePresenter, V extends BaseView> extends BaseFragment {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.attachView((V) this, mContext);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
