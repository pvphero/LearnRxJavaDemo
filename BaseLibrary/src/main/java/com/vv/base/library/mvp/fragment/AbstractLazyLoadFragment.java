package com.vv.base.library.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.vv.base.library.utils.LogUtils;


/**
 * @author ShenZhenWei
 * @date 2019/1/2
 */
public abstract class AbstractLazyLoadFragment extends Fragment {
    /**
     * 布局是否被创建
     */
    private boolean isViewCreated = false;
    /**
     * 数据是否被加载
     */
    private boolean isLoadData = false;
    /**
     * 是否第一次可见
     */
    private boolean isFirstVisible = false;

    private static final String TAG = "baseLazyFragmnet";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isFragmentVisable(this) && this.isAdded()) {
            if ((this.getParentFragment() != null && isFragmentVisable(this.getParentFragment()))
                    || this.getParentFragment() == null) {
                onLazyLoadData();
                isLoadData = true;
                if (isFirstVisible) {
                    isFirstVisible = false;
                }
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(TAG, "setUserVisibleHint(): "
                + " hide: " + this.isHidden()
                + " add :" + this.isAdded()
                + " visible: " + this.isVisible()
                + " resumed: " + this.isResumed());
        if (isFragmentVisable(this) && !isLoadData && isViewCreated && this.isAdded()) {
            onLazyLoadData();
            isLoadData = true;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d(TAG, "onHiddenChanged(): "
                + " hide: " + this.isHidden()
                + " add :" + this.isAdded()
                + " visible: " + this.isVisible()
                + " resumed: " + this.isResumed());
        //onHiddenChanged 调用在resumed之前，所以有可能，fragment被add，但是还没有resumed
        if (!hidden && !this.isResumed()) {
            return;
        }
        //使用hide跟show时，fragment的所有的生命周期方法都不会调用，除了onHiddenChange（）
        if (!hidden && isFirstVisible && this.isAdded()) {
            onLazyLoadData();
            isFirstVisible = false;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isLoadData = false;
        isFirstVisible = false;
    }

    protected abstract void onLazyLoadData();

    /**
     * 当前fragment是否可见
     *
     * @param fragment 当前的fragment
     * @return 是否可见
     */
    private boolean isFragmentVisable(Fragment fragment) {
        return !fragment.isHidden() && fragment.getUserVisibleHint();
    }
}
