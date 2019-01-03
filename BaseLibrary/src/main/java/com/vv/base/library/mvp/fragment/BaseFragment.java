package com.vv.base.library.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vv.base.library.mvp.view.BaseView;
import com.vv.base.library.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ShenZhenWei
 * @date 2019/1/2
 */
public abstract class BaseFragment extends AbstractLazyLoadFragment implements BaseView {

    private Unbinder mUnbinder;
    protected Activity mActivity;
    protected View mView;

    protected abstract void initView();

    protected abstract void loadData();

    protected abstract int getLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    protected void onLazyLoadData() {
        loadData();
    }

    @Override
    public void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showErrorMes() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void showToast(String toast) {
        ToastUtils.showShort(toast);
    }

    @Override
    public void unableRefrsh() {

    }

    @Override
    public void userNightNode(boolean isNight) {

    }

    @Override
    public void setStausBarColor(boolean isSet) {

    }

    @Override
    public void showTipView(boolean isConnection) {

    }
}
