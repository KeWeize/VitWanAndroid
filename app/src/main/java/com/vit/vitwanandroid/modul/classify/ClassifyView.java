package com.vit.vitwanandroid.modul.classify;

import android.widget.TextView;

import com.vit.vitwanandroid.base.mvp.BaseView;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxClassifyItem;

import java.util.List;

/**
 * @author kewz
 * @date 2018/3/19
 */

public interface ClassifyView extends BaseView {

    /**
     * 显示一级分类tag
     *
     * @param rxClassifyItems
     */
    void showClsTag(List<RxClassifyItem> rxClassifyItems);

    /**
     * 响应生成标签
     *
     * @param rxChildClsItems
     */
    void onGenTags(List<RxClassifyItem> rxChildClsItems);

    /**
     * 响应点击标签
     *
     * @param tvTag
     */
    void onClickTag(TextView tvTag);

    /**
     * 显示文章
     *
     * @param homeMultiItems
     * @param isRefresh
     */
    void showArticleData(List<HomeMultiItem> homeMultiItems, boolean isRefresh);

}
