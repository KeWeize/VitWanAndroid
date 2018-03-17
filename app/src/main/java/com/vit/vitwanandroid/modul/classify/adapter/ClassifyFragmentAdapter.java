package com.vit.vitwanandroid.modul.classify.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vit.vitwanandroid.bean.RxClassifyItem;
import com.vit.vitwanandroid.modul.classify.ClassifyListFragment;

import java.util.List;

/**
 * Created by vitar on 2018/3/18.
 */

public class ClassifyFragmentAdapter extends FragmentPagerAdapter {

    List<RxClassifyItem> datas;
    List<Fragment> fragments;

    public ClassifyFragmentAdapter(FragmentManager fm, List<RxClassifyItem> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = datas == null || datas.get(position) == null ?
                "" : datas.get(position).getName();
        return pageTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return ClassifyListFragment.newInstance(datas.get(position));
    }

    @Override
    public int getCount() {
        int count = datas == null ? 0 : datas.size();
        return count;
    }
}
