package com.vit.vitwanandroid.modul.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;
import com.vit.vitwanandroid.base.BaseMvpFragment;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.modul.home.HomeAdapter;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.utils.common.EmptyUtils;
import com.vit.vitwanandroid.widget.fliodlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author kewz
 * @date 2018/3/19
 */

public class ClassifyListFragment extends BaseMvpFragment<ClassifyPresenter, ClassifyView>
        implements ClassifyView, OnRefreshListener, OnLoadmoreListener{

    private static final String KEY_RXCLASSIFYITEM = "key_rxclassifyitem";

    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;

    private FluidLayout tagsLayout;
    private HomeAdapter adapterArticle;
    //data
    private RxClassifyItem rxClassifyItem;
    private List<TextView> tvTags;

    public static ClassifyListFragment newInstance(RxClassifyItem rxClassifyItem) {
        
        Bundle args = new Bundle();
        args.putSerializable(KEY_RXCLASSIFYITEM, rxClassifyItem);
        ClassifyListFragment fragment = new ClassifyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rxClassifyItem = (RxClassifyItem) getArguments()
                    .getSerializable(KEY_RXCLASSIFYITEM);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_list_classify;
    }

    @Override
    protected void initView() {
        View headView = LayoutInflater.from(mContext)
                .inflate(R.layout.classify_view_headtag, null);
        tagsLayout = headView.findViewById(R.id.tags_layout);
        tvTags = new ArrayList<>();
        if (rxClassifyItem != null) {
            mPresenter.genTags(rxClassifyItem);
        }
        rvClassify.setLayoutManager(new LinearLayoutManager(mContext));
        adapterArticle = new HomeAdapter();
        adapterArticle.addHeaderView(headView);
        rvClassify.setAdapter(adapterArticle);
        //刷新
        initRefreshLayout(refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        //获取数据
        if (!EmptyUtils.isEmpty(tvTags)) {
            mPresenter.clickTag(tvTags.get(0));
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPresenter.loadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.refresh();
    }

    @Override
    public void showClsTag(List<RxClassifyItem> rxClassifyItems) {}

    @Override
    public void onGenTags(List<RxClassifyItem> rxChildClsItems) {
        List<RxClassifyItem> childItems = rxClassifyItem.getChildren();
        for (int i=0; i<childItems.size(); i++) {
            final TextView tvTag = new TextView(mContext);
            tvTag.setText(childItems.get(i).getName());
            tvTag.setTextSize(13);
            tvTag.setPadding(10, 10, 10, 10);
            tvTag.setGravity(Gravity.CENTER);
            tvTag.setBackground(ContextCompat
                    .getDrawable(mContext, R.drawable.home_item_bg_cls_lable));
            tvTag.setTextColor(ContextCompat
                    .getColor(mContext, R.color.color_5F8AFA));
            tvTag.setTag(childItems.get(i).getId());
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(18, 8, 18, 8);
            tagsLayout.addView(tvTag, params);
            tvTags.add(tvTag);
            tvTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.clickTag(tvTag);
                }
            });
        }
    }

    @Override
    public void onClickTag(TextView tvTag) {
        if (tvTags != null) {
            for (TextView tv : tvTags) {
                tv.setTextColor(ContextCompat.getColor(mContext, R.color.color_5F8AFA));
                tv.setBackgroundResource(R.drawable.home_item_bg_cls_lable);
            }
            tvTag.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            tvTag.setBackgroundResource(R.drawable.home_item_bg_cls_lable_press);
        }
    }

    @Override
    public void showArticleData(List<HomeMultiItem> homeMultiItems, boolean isRefresh) {
        if (EmptyUtils.isEmpty(homeMultiItems)) {
            //没有更多
            refreshLayout.finishLoadmoreWithNoMoreData();
            return ;
        }
        refreshLayout.setLoadmoreFinished(false);
        if (isRefresh) {
            //刷新替换
            adapterArticle.setNewData(homeMultiItems);
            refreshLayout.finishRefresh();
            return ;
        }
        adapterArticle.addData(homeMultiItems);
        refreshLayout.finishLoadmore();
    }

}
