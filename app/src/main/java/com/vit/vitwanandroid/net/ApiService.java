package com.vit.vitwanandroid.net;

import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
     * 文章
     *
     * @return
     */
    @GET(ApiUrl.HOME_ARTICLE + "{page}" + ApiUrl.BASE_SUF)
    Observable<HttpResult<RxArticleData>> getArticle(@Path("page") int page);

    /**
     * 首页文章
     *
     * @return
     */
    @GET(ApiUrl.HOME_ARTICLE + "{page}" + ApiUrl.BASE_SUF)
    Observable<HttpResult<RxArticleData>> getArticle(@Path("page") int page, @Query("cid") int cid);

    /**
     * 体系（分类列表）
     *
     * @return
     */
    @GET(ApiUrl.CLASSIFY)
    Observable<HttpResult<List<RxClassifyItem>>> getClassify();


}
