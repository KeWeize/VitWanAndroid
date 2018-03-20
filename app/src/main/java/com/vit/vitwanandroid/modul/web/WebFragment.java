package com.vit.vitwanandroid.modul.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author kewz
 * @date 2018/3/20
 */

public class WebFragment extends Fragment {

    WebView wvContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wvContent = new WebView(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        wvContent = new WebView(getActivity());
        initSetting(wvContent);
        wvContent.setWebViewClient(initWebViewClient());
        wvContent.setWebChromeClient(initWebChromeClient());
        return wvContent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Called when the fragment is no longer resumed. Pauses the WebView.
     */
    @Override
    public void onResume() {
        wvContent.onResume();
        wvContent.resumeTimers();
        super.onResume();
    }

    /**
     * Called when the fragment is visible to the user and actively running. Resumes the WebView.
     */
    @Override
    public void onPause() {
        super.onPause();
        wvContent.onPause();
        wvContent.pauseTimers();
    }

    /**
     * Called when the WebView has been detached from the fragment.
     * The WebView is no longer available after this time.
     */
    @Override
    public void onDestroyView() {
//        mIsWebViewAvailable = false;
        super.onDestroyView();
    }

    /**
     * Called when the fragment is no longer in use. Destroys the internal state of the WebView.
     */
    @Override
    public void onDestroy() {
        if (wvContent != null) {
            wvContent.destroy();
            wvContent = null;
        }
        super.onDestroy();
    }

    private void initSetting(WebView wvContent) {
        if (wvContent == null) {
            return ;
        }
        WebSettings settings = wvContent.getSettings();
        //支持js
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    /**
     * WebViewClient 用于帮助WebView处理各种通知、请求事件
     */
    private WebViewClient initWebViewClient() {
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        return webViewClient;
    }

    /**
     * WebChromeClient 用于辅助WebView处理JavaScript的对话框、网站图标、网页title、加载进度等
     */
    private WebChromeClient initWebChromeClient() {
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //处理加载进度
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页标题。当加载出错时返回“找不到网页”。建议当触发onReceiveError时处理一下
                super.onReceivedTitle(view, title);
            }

        };
        return webChromeClient;
    }

    /**
     * Gets the WebView.
     */
    public WebView getWebView() {
        return wvContent;
    }

}
