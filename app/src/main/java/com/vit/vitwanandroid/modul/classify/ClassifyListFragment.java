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

import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseFragment;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.modul.fragment.adapter.HomeAdapter;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.widget.fliodlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by vitar on 2018/3/18.
 */

public class ClassifyListFragment extends BaseFragment {

    private static final String KEY_RXCLASSIFYITEM = "key_rxclassifyitem";

    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;

    private int page;
    private HomeAdapter adapterArticle;
    //data
    private RxClassifyItem rxClassifyItem;

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
        FluidLayout tagsLayout = headView.findViewById(R.id.tags_layout);
        if (rxClassifyItem != null) {
            genTags(tagsLayout, rxClassifyItem);
        }
        rvClassify.setLayoutManager(new LinearLayoutManager(mContext));
        adapterArticle = new HomeAdapter();
        adapterArticle.addHeaderView(headView);
        rvClassify.setAdapter(adapterArticle);
        getArticle(rxClassifyItem.getChildren().get(0).getId());
    }

    private void genTags(FluidLayout tagsLayout, RxClassifyItem rxClassifyItem) {
        if (rxClassifyItem == null || rxClassifyItem.getChildren() == null) {
            return ;
        }
        List<RxClassifyItem> childItems = rxClassifyItem.getChildren();
        for (int i=0; i<childItems.size(); i++) {
            TextView tvTag = new TextView(mContext);
            tvTag.setText(childItems.get(i).getName());
            tvTag.setTextSize(13);
            tvTag.setPadding(10, 10, 10, 10);
            tvTag.setGravity(Gravity.CENTER);
            tvTag.setBackground(ContextCompat
                    .getDrawable(mContext, R.drawable.home_item_bg_cls_lable));
            tvTag.setTextColor(ContextCompat
                    .getColor(mContext, R.color.color_5F8AFA));
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(18, 8, 18, 8);
            tagsLayout.addView(tvTag, params);
        }
    }

    private void getArticle(int cid) {
        ApiWrapper.getInstance().getHomeArticle(page, cid).subscribe(new Consumer<RxArticleData>() {
            @Override
            public void accept(RxArticleData rxArticleData) throws Exception {
                List<HomeMultiItem> homeMultiItems = new ArrayList<>();
                for (int i=0; i<rxArticleData.getDatas().size(); i++) {
                    homeMultiItems.add(new HomeMultiItem(rxArticleData.getDatas().get(i)));
                }
                adapterArticle.addData(homeMultiItems);
//                refreshLayout.finishLoadmore();
            }
        });
    }
}
