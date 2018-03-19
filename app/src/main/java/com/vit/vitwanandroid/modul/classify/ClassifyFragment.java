package com.vit.vitwanandroid.modul.classify;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.base.BaseMvpFragment;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.modul.classify.adapter.ClassifyFragmentAdapter;
import com.vit.vitwanandroid.widget.recyclertab.RecyclerTabLayout;
import com.vit.vitwanandroid.widget.vitstatus.VitStatusLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @author kewz
 * @date 2018/3/8
 */

public class ClassifyFragment extends BaseMvpFragment<ClassifyPresenter, ClassifyView>
        implements ClassifyView {

    @BindView(R.id.vsl_status)
    VitStatusLayout statusLayout;
    @BindView(R.id.rtl_classify)
    RecyclerTabLayout rtlClassify;
    @BindView(R.id.vp_classify)
    ViewPager vpClassify;

    private ImmersionBar immersionBar;
    private ClassifyFragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_classify;
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
        statusLayout.showLoadingView();
        mPresenter.getClsTag();
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
    public void showClsTag(List<RxClassifyItem> rxClassifyItems) {
        fragmentAdapter = new ClassifyFragmentAdapter(getFragmentManager(), rxClassifyItems);
        vpClassify.setAdapter(fragmentAdapter);
        rtlClassify.setUpWithViewPager(vpClassify);
        statusLayout.showContentView();
    }

    @Override
    public void onGenTags(List<RxClassifyItem> rxChildClsItems) {
    }

    @Override
    public void onClickTag(TextView tvTag) {
    }

    @Override
    public void showArticleData(List<HomeMultiItem> homeMultiItems, boolean isReplace) {
    }

}
