package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;
import com.vit.vitwanandroid.modul.fragment.adapter.HomeAdapter;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.net.rxjava.RxZipModel;
import com.vit.vitwanandroid.widget.vitstatus.VitStatusLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class HomeFragment extends BaseStatusFragment implements OnRefreshListener, OnLoadmoreListener {

    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;

    private int page;
    private HomeAdapter adapterHome;
    private ImmersionBar immersionBar;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersionBar = ImmersionBar.with(getActivity(), this)
                //使用该属性,必须指定状态栏颜色
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .addTag(TAG);
        immersionBar.init();
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
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setRefreshFooter(new ClassicsFooter(mContext)
                .setSpinnerStyle(SpinnerStyle.Translate));
        //文章列表
        adapterHome = new HomeAdapter();
        rvHome.setAdapter(adapterHome);
        rvHome.setLayoutManager(new LinearLayoutManager(mContext));

        showLoadingView();
        getHomeData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 0;
        getHomeData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page ++;
        getMoreArticle();
    }

    private void getHomeData() {
        Observable<List<RxHomeBannerItem>> obBanner = ApiWrapper.getInstance().getHomeBanner();
        Observable<RxArticleData> obArticle = ApiWrapper.getInstance().getHomeArticle(page);
        Log.d(TAG, "getHomeData: ");
        Observable.zip(obBanner, obArticle, new BiFunction<List<RxHomeBannerItem>,
                RxArticleData, RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData>>() {
            @Override
            public RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData> apply(List<RxHomeBannerItem> rxHomeBannerItems, RxArticleData rxArticleData) throws Exception {
                return new RxZipModel.Model2<>(rxHomeBannerItems, rxArticleData);
            }
        }).subscribe(new Consumer<RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData>>() {
            @Override
            public void accept(RxZipModel.Model2<List<RxHomeBannerItem>, RxArticleData> listRxArticleDataModel2) throws Exception {
                setHomeData(listRxArticleDataModel2.getModel1(), listRxArticleDataModel2.getModel2());
            }
        });
    }

    private void setHomeData(List<RxHomeBannerItem> rxHomeBannerItems, RxArticleData rxArticleData) {
        List<HomeMultiItem> homeMultiItems = new ArrayList<>();
        homeMultiItems.add(new HomeMultiItem(rxHomeBannerItems));
        homeMultiItems.add(new HomeMultiItem(getString(R.string.home_new_article)));
        for (int i=0; i<rxArticleData.getDatas().size(); i++) {
            homeMultiItems.add(new HomeMultiItem(rxArticleData.getDatas().get(i)));
        }
        adapterHome.setNewData(homeMultiItems);
        showContentView();
        refreshLayout.finishRefresh();
    }

    private void getMoreArticle() {
        ApiWrapper.getInstance().getHomeArticle(page).subscribe(new Consumer<RxArticleData>() {
            @Override
            public void accept(RxArticleData rxArticleData) throws Exception {
                List<HomeMultiItem> homeMultiItems = new ArrayList<>();
                for (int i=0; i<rxArticleData.getDatas().size(); i++) {
                    homeMultiItems.add(new HomeMultiItem(rxArticleData.getDatas().get(i)));
                }
                adapterHome.addData(homeMultiItems);
                refreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && immersionBar != null) {
            immersionBar.getTag(TAG)
                    .init();
        }
    }

}
