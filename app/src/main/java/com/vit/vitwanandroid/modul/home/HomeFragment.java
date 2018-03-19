package com.vit.vitwanandroid.modul.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseMvpFragment;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.utils.common.EmptyUtils;
import com.vit.vitwanandroid.widget.vitstatus.VitStatusLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter, HomeView> implements HomeView,
        OnRefreshListener, OnLoadmoreListener {

    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;

    private HomeAdapter adapterHome;
    private ImmersionBar immersionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {
        immersionBar = ImmersionBar.with(getActivity(), this)
                //使用该属性,必须指定状态栏颜色
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .addTag(TAG);
        immersionBar.init();
        //刷新
        initRefreshLayout(refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        //文章列表
        adapterHome = new HomeAdapter();
        rvHome.setAdapter(adapterHome);
        rvHome.setLayoutManager(new LinearLayoutManager(mContext));
        //加载数据
        statusLayout.showLoadingView();
        mPresenter.getHomeData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.getHomeData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.getMoreArticle();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && immersionBar != null) {
            immersionBar.getTag(TAG)
                    .init();
        }
    }

    @Override
    public void showHomeData(List<HomeMultiItem> homeMultiItems, boolean isRefresh) {
        if (EmptyUtils.isEmpty(homeMultiItems)) {
            //没有更多
            refreshLayout.finishLoadmoreWithNoMoreData();
            return;
        }
        refreshLayout.setLoadmoreFinished(false);
        if (isRefresh) {
            //刷新替换
            adapterHome.setNewData(homeMultiItems);
            refreshLayout.finishRefresh();
            statusLayout.showContentView();
            return;
        }
        adapterHome.addData(homeMultiItems);
        refreshLayout.finishLoadmore();
    }
}
