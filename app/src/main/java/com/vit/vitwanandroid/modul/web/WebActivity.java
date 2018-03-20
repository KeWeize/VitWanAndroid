package com.vit.vitwanandroid.modul.web;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.webkit.WebView;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseActivity;

/**
 * @author kewz
 * @date 2018/3/20
 */

public class WebActivity extends BaseActivity {

    WebFragment webFragment;
    WebView wvContent;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        webFragment = new WebFragment();
        wvContent = webFragment.getWebView();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_container, webFragment);
        ft.commitAllowingStateLoss();

        wvContent.loadUrl("http://blog.csdn.net/lmj623565791/article/details/52204039");
    }
}
