package com.vit.vitwanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.gyf.barlibrary.ImmersionBar;
import com.vit.vitwanandroid.base.BaseActivity;
import com.vit.vitwanandroid.modul.classify.ClassifyFragment;
import com.vit.vitwanandroid.modul.fragment.MineFragment;
import com.vit.vitwanandroid.modul.fragment.ProjectFragment;
import com.vit.vitwanandroid.modul.home.HomeFragment;
import com.vit.vitwanandroid.modul.web.WebActivity;
import com.vit.vitwanandroid.utils.btmnav.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private final static String TAG_MAIN_FRAGMENT = "tag_home_fragment";
    private final static String TAG_CLASSIFY_FRAGMENT = "tag_classify_fragment";
    private final static String TAG_PROJECT_FRAGMENT = "tag_project_fragment";
    private final static String TAG_MINE_FRAGMENT = "tag_mine_fragment";

    private Fragment homeFragment;
    private Fragment classifyFragment;
    private Fragment projectFragment;
    private Fragment mineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this).init();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //初始化导航页home
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected boolean isInitImmersionBar() {
        //当前Activity不需要初始化默认的ImmersionBar样式
        return false;
    }

    /**
     * 显示首页Fragment
     *
     * @param index
     */
    private void go2Page(int index) {

        FragmentManager fm = getSupportFragmentManager();
        String tagCurrentFragment;
        Fragment fg2Show;
        FragmentTransaction ft = fm.beginTransaction();

        homeFragment = fm.findFragmentByTag(TAG_MAIN_FRAGMENT);
        classifyFragment = fm.findFragmentByTag(TAG_CLASSIFY_FRAGMENT);
        projectFragment = fm.findFragmentByTag(TAG_PROJECT_FRAGMENT);
        mineFragment = fm.findFragmentByTag(TAG_MINE_FRAGMENT);

        switch (index) {
            case R.id.navigation_home:
                fg2Show = homeFragment == null ? new HomeFragment() : homeFragment;
                tagCurrentFragment = TAG_MAIN_FRAGMENT;
                break;

            case R.id.navigation_classify:
                fg2Show = classifyFragment == null ? new ClassifyFragment() : classifyFragment;
                tagCurrentFragment = TAG_CLASSIFY_FRAGMENT;
                break;

            case R.id.navigation_project:
                fg2Show = projectFragment == null ? new ProjectFragment() : projectFragment;
                tagCurrentFragment = TAG_PROJECT_FRAGMENT;
                break;

            case R.id.navigation_mine:
                fg2Show = mineFragment == null ? new MineFragment() : mineFragment;
                tagCurrentFragment = TAG_MINE_FRAGMENT;
                break;

            default:
                fg2Show = homeFragment == null ? new HomeFragment() : homeFragment;
                tagCurrentFragment = TAG_MAIN_FRAGMENT;
                break;
        }

        if (fg2Show != homeFragment && homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (fg2Show != classifyFragment && classifyFragment != null) {
            ft.hide(classifyFragment);
        }
        if (fg2Show != projectFragment && projectFragment != null) {
            ft.hide(projectFragment);
        }
        if (fg2Show != mineFragment && mineFragment != null) {
            ft.hide(mineFragment);
        }
        if (fg2Show.isDetached()) {
            ft.attach(fg2Show);
        } else if (!fg2Show.isAdded()) {
            ft.add(R.id.fl_container, fg2Show, tagCurrentFragment);
        } else if (fg2Show.isHidden()) {
            ft.show(fg2Show);
        }

        int result = ft.commitAllowingStateLoss();
        if (result < 0) {
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    /**
     * 首页底部导航监听对象
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    go2Page(item.getItemId());
                    return true;

                case R.id.navigation_classify:
                    go2Page(item.getItemId());
                    return true;

                case R.id.navigation_project:
                    go2Page(item.getItemId());
                    return true;

                case R.id.navigation_mine:
//                    go2Page(item.getItemId());
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    startActivity(intent);
                    return true;

                default:
                    break;
            }
            return false;
        }
    };
}
