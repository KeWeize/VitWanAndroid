package com.vit.vitwanandroid.modul.home;

import com.vit.vitwanandroid.base.mvp.BaseView;
import com.vit.vitwanandroid.bean.HomeMultiItem;

import java.util.List;

/**
 * @author kewz
 * @date 2018/3/19
 */

public interface HomeView extends BaseView {

    void showHomeData(List<HomeMultiItem> homeMultiItems, boolean isRefresh);

}
