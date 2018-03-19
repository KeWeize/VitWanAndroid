package com.vit.vitwanandroid.modul.classify;

import android.widget.TextView;

import com.vit.vitwanandroid.base.mvp.BasePresenter;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxArticleData;
import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.net.ApiWrapper;
import com.vit.vitwanandroid.utils.common.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author kewz
 * @date 2018/3/19
 */

public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    private int page = 0;
    private int cid;

    /**
     * 获取体系分类标签
     */
    public void getClsTag() {
        ApiWrapper.getInstance().getClassify().subscribe(new Consumer<List<RxClassifyItem>>() {
            @Override
            public void accept(List<RxClassifyItem> rxClassifyItems) throws Exception {
                getView().showClsTag(rxClassifyItems);
            }
        });
    }

    /**
     * 生成分类标签
     */
    public void genTags(RxClassifyItem rxClassifyItem) {
        if (rxClassifyItem == null || EmptyUtils.isEmpty(rxClassifyItem.getChildren())) {
            return ;
        }
        cid = rxClassifyItem.getChildren().get(0).getId();
        getView().onGenTags(rxClassifyItem.getChildren());
    }

    /**
     * 点击分类标签
     *
     * @param tvTag
     */
    public void clickTag(TextView tvTag) {
        getView().onClickTag(tvTag);
        cid = (int) tvTag.getTag();
        refresh();
    }

    /**
     * 刷新数据
     */
    public void refresh() {
        if (cid != 0) {
            this.cid = cid;
        }
        page = 0;
        getArticle();
    }

    /**
     * 加载更多
     */
    public void loadMore() {
        page ++;
        getArticle();
    }

    /**
     * 获取分类下文章数据
     */
    public void getArticle() {
        ApiWrapper.getInstance().getHomeArticle(page, cid).subscribe(new Consumer<RxArticleData>() {
            @Override
            public void accept(RxArticleData rxArticleData) throws Exception {
                List<HomeMultiItem> homeMultiItems = new ArrayList<>();
                for (int i=0; i<rxArticleData.getDatas().size(); i++) {
                    homeMultiItems.add(new HomeMultiItem(rxArticleData.getDatas().get(i)));
                }
                getView().showArticleData(homeMultiItems, page == 0);
            }
        });
    }


}
