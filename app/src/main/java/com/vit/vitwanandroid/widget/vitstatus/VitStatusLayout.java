package com.vit.vitwanandroid.widget.vitstatus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.vit.vitwanandroid.R;

/**
 * @author kewz
 * @date 2018/3/12
 */

public class VitStatusLayout extends FrameLayout {

    private static String TAG = VitStatusLayout.class.getSimpleName();

    private View vContent;
    private View vLoading;
    private View vEmpty;
    private View vError;
    private View vNetoff;

    private ViewStub vsLoading;
    private ViewStub vsEmpty;
    private ViewStub vsError;
    private ViewStub vsNetoff;
    private View vStatusContainer;

    private View currentShowingView;

    public VitStatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public VitStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VitStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        //初始化自定义参数
        //...

        LayoutInflater inflater = LayoutInflater.from(mContext);
        //获取多状态布局
        vStatusContainer = inflater.inflate(R.layout.statuslayout_view_status_container, this, false);
        vsLoading = vStatusContainer.findViewById(R.id.vs_loading);
        vsEmpty = vStatusContainer.findViewById(R.id.vs_empty);
        vsError = vStatusContainer.findViewById(R.id.vs_error);
        vsNetoff = vStatusContainer.findViewById(R.id.vs_netoff);
        //添加多状态布局
        addView(vStatusContainer);
    }

    /**
     * 检查并获取内容布局
     *
     * @param view
     */
    private void checkIsContentView(View view) {
        if (vContent != null && view != vContent) {
            throw new InflateException(VitStatusLayout.class.getSimpleName() + " can host only one direct child");
        }
        if (vContent == null && view != vStatusContainer) {
            vContent = view;
            currentShowingView = vContent;
        }
    }

    /**
     * 显示内容View
     */
    public void showContentView() {
        switchStatusView(vContent);
    }

    /**
     * 显示加载View
     */
    public void showLoadingView() {
        if (vLoading == null && vsLoading != null) {
            vsLoading.inflate();
            vLoading = vStatusContainer.findViewById(R.id.progress_content);
        }
        switchStatusView(vLoading);
    }

    /**
     * 显示空状态View
     */
    public void showEmptyView() {
        if (vEmpty == null && vsEmpty != null) {
            vsEmpty.inflate();
            vEmpty = vStatusContainer.findViewById(R.id.empty_content);
        }
        switchStatusView(vEmpty);
    }

    /**
     * 显示错误状态View
     */
    public void showErrorView() {
        if (vError == null && vsError != null) {
            vsError.inflate();
            vError = vStatusContainer.findViewById(R.id.error_content);
        }
        switchStatusView(vError);
    }

    /**
     * 显示无网络状态View
     */
    public void showNetoff() {
        if (vNetoff == null && vsNetoff != null) {
            vsNetoff.inflate();
            vNetoff = vStatusContainer.findViewById(R.id.netoffline_content);
        }
        switchStatusView(vNetoff);
    }

    /**
     * 显示某个状态View
     *
     * @param toShowView
     */
    private void switchStatusView(View toShowView) {
        View toHideView = currentShowingView;
        if (toShowView == null || toHideView == toShowView) {
            return ;
        }

        if (toHideView != null && toHideView.getVisibility() != View.GONE) {
            toHideView.setVisibility(View.GONE);
        }
        if (toShowView.getVisibility() != View.VISIBLE) {
            toShowView.setVisibility(VISIBLE);
        }
        currentShowingView = toShowView;
    }

    /**
     * addView
     */

    @Override
    public void addView(View child) {
        checkIsContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        checkIsContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        checkIsContentView(child);
        super.addView(child, width, height);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }
}
