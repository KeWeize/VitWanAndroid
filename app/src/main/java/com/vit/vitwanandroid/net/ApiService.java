package com.vit.vitwanandroid.net;

import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxArticleItem;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    /**
     * 首页文章
     *
     * @return
     */
    @GET(ApiUrl.HOME_ARTICLE + "{page}" + ApiUrl.BASE_SUF)
    Observable<HttpResult<RxArticleData>> getHomeArticle(@Path("page") int page);

}
