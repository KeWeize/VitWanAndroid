package com.vit.vitwanandroid.modul.fragment.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.bean.HomeMultiItem;
import com.vit.vitwanandroid.bean.RxArticleItem;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;
import com.vit.vitwanandroid.utils.banner.GlideBannerImageLoader;
import com.vit.vitwanandroid.utils.imageload.GlideLoader;
import com.vit.vitwanandroid.widget.brvah.VitViewHolder;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


/**
 * @author kewz
 * @date 2018/3/16
 */

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeMultiItem, VitViewHolder> {

    public HomeAdapter() {
        super(null);
        addItemType(HomeMultiItem.TYPE_TEXT, R.layout.home_item_text);
        addItemType(HomeMultiItem.TYPE_ARTICLE, R.layout.home_item_article);
        addItemType(HomeMultiItem.TYPE_BANNER, R.layout.home_item_banner);
    }

    @Override
    protected void convert(VitViewHolder helper, HomeMultiItem item) {

        switch (helper.getItemViewType()) {
            case HomeMultiItem.TYPE_TEXT:
                covertTypeText(helper, item);
                break;

            case HomeMultiItem.TYPE_ARTICLE:
                covertTypeArticle(helper, item);
                break;

            case HomeMultiItem.TYPE_BANNER:
                covertTypeBanner(helper, item);
                break;

            default:
                covertTypeText(helper, item);
                break;
        }
    }

    /**
     * 纯文本
     */
    private void covertTypeText(VitViewHolder helper, HomeMultiItem item) {
        helper.setText(R.id.tv_text, item.getText());
    }

    /**
     * 文章
     */
    private void covertTypeArticle(VitViewHolder helper, HomeMultiItem item) {
        RxArticleItem articleItem = item.getRxArticleItem();
        helper.setText(R.id.tv_title, articleItem.getTitle())
                .setText(R.id.tv_desc, articleItem.getDesc())
                .setText(R.id.tv_chapte, articleItem.getChapterName())
                .setText(R.id.tv_author, articleItem.getAuthor())
                .setText(R.id.tv_date, articleItem.getNiceDate());
        if (articleItem.getEnvelopePic() != null && !"".equals(articleItem.getEnvelopePic())) {
            helper.setGone(R.id.iv_envelope, true);
            GlideLoader.loadImage(mContext, articleItem.getEnvelopePic(),
                    (ImageView)helper.getView(R.id.iv_envelope));
        } else {
            helper.setGone(R.id.iv_envelope, false);
        }
    }

    /**
     * Banner
     */
    private void covertTypeBanner(VitViewHolder helper, HomeMultiItem item) {
        List<RxHomeBannerItem> listBanner = item.getRxHomeBanner();
        Banner banner = helper.getView(R.id.banner);
        banner.setImageLoader(new GlideBannerImageLoader());
        List<String> imagePaths = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i=0; i<listBanner.size(); i++) {
            imagePaths.add(listBanner.get(i).getImagePath());
            titles.add(listBanner.get(i).getTitle());
        }
        banner.releaseBanner();
        banner.setImages(imagePaths);
        banner.setBannerTitles(titles);
        banner.start();
    }

}
