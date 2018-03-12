package com.vit.vitwanandroid.net;


import com.vit.vitwanandroid.bean.RxHomeBannerItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author kewz
 * @date 2018/3/5
 */

public class ApiWrapper extends ApiBase {

    private static ApiWrapper apiWrapper;

    private ApiWrapper(){}

    public static ApiWrapper getInstance() {
        if (apiWrapper == null) {
            synchronized (ApiWrapper.class) {
                if (apiWrapper == null) {
                    apiWrapper = new ApiWrapper();
                }
            }
        }
        return apiWrapper;
    }

    /**
     * 获取首页Banner数据
     *
     * @return
     */
    public Observable<List<RxHomeBannerItem>> getHomeBanner() {
        return getDefault().getHomeBanner()
                .compose(this.<List<RxHomeBannerItem>>applySchedulers());
    }



    /*####################### 文件提交相关 #########################*/



}
