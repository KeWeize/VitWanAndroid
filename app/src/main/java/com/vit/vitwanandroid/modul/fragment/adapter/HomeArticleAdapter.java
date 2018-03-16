package com.vit.vitwanandroid.modul.fragment.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vit.vitwanandroid.R;
import com.vit.vitwanandroid.bean.RxArticleItem;
import com.vit.vitwanandroid.bean.RxHomeBannerItem;
import com.vit.vitwanandroid.widget.brvah.VitViewHolder;


/**
 * @author kewz
 * @date 2018/3/16
 */

public class HomeArticleAdapter extends BaseQuickAdapter<RxArticleItem, VitViewHolder> {

    public HomeArticleAdapter() {
        super(R.layout.home_item_article);
    }

    @Override
    protected void convert(VitViewHolder helper, RxArticleItem item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_des, item.getDesc());
    }

}
