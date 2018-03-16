package com.vit.vitwanandroid.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by vitar on 2018/3/16.
 */

public class HomeMultiItem implements MultiItemEntity {

    /**
     * 但文本（标题）
     */
    public static final int TYPE_TEXT = 0;
    /**
     * 文章
     */
    public static final int TYPE_ARTICLE = 1;
    /**
     * Banner
     */
    public static final int TYPE_BANNER = 2;

    private int type;
    private List<RxHomeBannerItem> rxHomeBanner;
    private RxArticleItem rxArticleItem;
    private String text;

    public HomeMultiItem(String text) {
        type = TYPE_TEXT;
        this.text = text;
    }

    public HomeMultiItem(RxArticleItem rxArticleItem) {
        type = TYPE_ARTICLE;
        this.rxArticleItem = rxArticleItem;
    }

    public HomeMultiItem(List<RxHomeBannerItem> rxHomeBanner) {
        type = TYPE_BANNER;
        this.rxHomeBanner = rxHomeBanner;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public List<RxHomeBannerItem> getRxHomeBanner() {
        return rxHomeBanner;
    }

    public void setRxHomeBanner(List<RxHomeBannerItem> rxHomeBanner) {
        this.rxHomeBanner = rxHomeBanner;
    }

    public RxArticleItem getRxArticleItem() {
        return rxArticleItem;
    }

    public void setRxArticleItem(RxArticleItem rxArticleItem) {
        this.rxArticleItem = rxArticleItem;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
