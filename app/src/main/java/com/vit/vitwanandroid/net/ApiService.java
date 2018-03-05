package com.vit.vitwanandroid.net;

import com.vit.vitwanandroid.bean.RxHomeBannerItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author kewz
 * @date 2018/3/5
 */

public interface ApiService {

    /**
     * 首页Banner
     *
     * @return
     */
    @GET(ApiUrl.HOME_BANNER)
    Observable<HttpResult<List<RxHomeBannerItem>>> getHomeBanner();

}
