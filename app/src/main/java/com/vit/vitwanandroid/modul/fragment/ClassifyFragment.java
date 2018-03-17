package com.vit.vitwanandroid.modul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseStatusFragment;
import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.modul.classify.adapter.ClassifyFragmentAdapter;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.widget.recyclertab.RecyclerTabLayout;
import com.vit.vitwanandroid.widget.vitstatus.VitStatusLayout;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ClassifyFragment extends BaseStatusFragment {

    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;
    @BindView(R.id.rtl_classify)
    RecyclerTabLayout rtlClassify;
    @BindView(R.id.vp_classify)
    ViewPager vpClassify;

    private ImmersionBar immersionBar;
    private ClassifyFragmentAdapter fragmentAdapter;

    public static ClassifyFragment newInstance() {

        Bundle args = new Bundle();

        ClassifyFragment fragment = new ClassifyFragment();
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
    protected VitStatusLayout initVitStatusLayout() {
        return statusLayout;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_classify;
    }

    @Override
    protected void initView() {
        showLoadingView();
        getClassifyData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && immersionBar != null) {
            immersionBar.getTag(TAG)
                    .init();
        }
    }

    /**
     * 获取体系（分类）数据
     */
    private void getClassifyData() {
        ApiWrapper.getInstance().getClassify().subscribe(new Consumer<List<RxClassifyItem>>() {
            @Override
            public void accept(List<RxClassifyItem> rxClassifyItems) throws Exception {
                showContentView();
                setClassify(rxClassifyItems);
            }
        });
    }

    private void setClassify(List<RxClassifyItem> rxClassifyItems) {
        fragmentAdapter = new ClassifyFragmentAdapter(getFragmentManager(), rxClassifyItems);
        vpClassify.setAdapter(fragmentAdapter);
        rtlClassify.setUpWithViewPager(vpClassify);
    }
}
