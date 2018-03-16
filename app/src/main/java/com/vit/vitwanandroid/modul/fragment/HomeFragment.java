package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxArticleItem;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;
import com.vit.vitwanandroid.modul.fragment.adapter.HomeArticleAdapter;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.utils.banner.GlideBannerImageLoader;
import com.vit.vitwanandroid.widget.VitStatusLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class HomeFragment extends BaseStatusFragment implements OnRefreshListener {

    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;
    @BindView(R.id.banner)
    Banner bannerHome;
    @BindView(R.id.rv_home_article)
    RecyclerView rvHomeArticle;

    private HomeArticleAdapter adapterHomeArticle;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected VitStatusLayout initVitStatusLayout() {
        return statusLayout;
    }

    @Override
    protected void initView() {
        //首页Banner
        refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        refreshLayout.setOnRefreshListener(this);
        bannerHome.setImageLoader(new GlideBannerImageLoader());
        //文章列表
        adapterHomeArticle = new HomeArticleAdapter();
        rvHomeArticle.setAdapter(adapterHomeArticle);
        rvHomeArticle.setLayoutManager(new LinearLayoutManager(mContext));

        showLoadingView();
        getHomeBanner();
        getHomeArticle();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getHomeBanner();
    }

    private void getHomeBanner() {
        ApiWrapper.getInstance().getHomeBanner().subscribe(new Consumer<List<RxHomeBannerItem>>() {
            @Override
            public void accept(List<RxHomeBannerItem> rxHomeBannerItems) throws Exception {
                List<String> imagePaths = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                for (int i=0; i<rxHomeBannerItems.size(); i++) {
                    imagePaths.add(rxHomeBannerItems.get(i).getImagePath());
                    titles.add(rxHomeBannerItems.get(i).getTitle());
                }
                bannerHome.releaseBanner();
                bannerHome.setImages(imagePaths);
                bannerHome.setBannerTitles(titles);
                showContentView();
                refreshLayout.finishRefresh();
                bannerHome.start();
            }
        });
    }

    private void getHomeArticle() {
        ApiWrapper.getInstance().getHomeArticle().subscribe(new Consumer<RxArticleData>() {
            @Override
            public void accept(RxArticleData rxArticleData) throws Exception {
                adapterHomeArticle.setNewData(rxArticleData.getDatas());
            }
        });
    }

}
