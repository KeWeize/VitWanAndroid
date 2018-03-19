package com.vit.vitwanandroid.modul.home;

import android.util.Log;

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.mvp.BasePresenter;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.net.rxjava.RxZipModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * @author kewz
 * @date 2018/3/19
 */

public class HomePresenter extends BasePresenter<HomeView> {

    private int page = 0;

    /**
     * 获取Banner + 第一屏Article
     */
    public void getHomeData() {
        page = 0;
        Observable<List<RxHomeBannerItem>> obBanner = ApiWrapper.getInstance().getHomeBanner();
        Observable<RxArticleData> obArticle = ApiWrapper.getInstance().getHomeArticle(page);
        Observable.zip(obBanner, obArticle, new BiFunction<List<RxHomeBannerItem>,
                RxArticleData, RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData>>() {
            @Override
            public RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData> apply(List<RxHomeBannerItem> rxHomeBannerItems, RxArticleData rxArticleData) throws Exception {
                return new RxZipModel.Model2<>(rxHomeBannerItems, rxArticleData);
            }
        }).subscribe(new Consumer<RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData>>() {
            @Override
            public void accept(RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData> listRxArticleDataModel2) throws Exception {
                List<RxHomeBannerItem> rxHomeBannerItems = listRxArticleDataModel2.getModel1();
                RxArticleData rxArticleData = listRxArticleDataModel2.getModel2();
                List<HomeMultiItem> homeMultiItems = new ArrayList<>();
                homeMultiItems.add(new HomeMultiItem(rxHomeBannerItems));
                homeMultiItems.add(new HomeMultiItem(mContext.getString(R.string.home_new_article)));
                for (int i=0; i<rxArticleData.getDatas().size(); i++) {
                    homeMultiItems.add(new HomeMultiItem(rxArticleData.getDatas().get(i)));
                }
                getView().showHomeData(homeMultiItems, true);
            }
        });
    }

    /**
     * 显示给更多
     */
    public void getMoreArticle() {
        page ++;
        ApiWrapper.getInstance().getHomeArticle(page).subscribe(new Consumer<RxArticleData>() {
            @Override
            public void accept(RxArticleData rxArticleData) throws Exception {
                List<HomeMultiItem> homeMultiItems = new ArrayList<>();
                for (int i=0; i<rxArticleData.getDatas().size(); i++) {
                    homeMultiItems.add(new HomeMultiItem(rxArticleData.getDatas().get(i)));
                }
                getView().showHomeData(homeMultiItems, false);
            }
        });
    }

}
