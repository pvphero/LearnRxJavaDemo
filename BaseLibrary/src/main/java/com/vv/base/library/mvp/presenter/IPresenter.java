package com.vv.base.library.mvp.presenter;

import com.vv.base.library.mvp.view.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * @author ShenZhenWei
 * @date 2019/1/2
 */
public interface IPresenter<T extends BaseView> {

    /**
     * 注入View
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 判断是否注入View
     *
     * @return true注入，flase没有注入
     */
    boolean isAttachView();

    /**
     * 解绑View
     */
    void detachView();

    /**
     * 订阅事件,管理事件的生命周期
     *
     * @param disposable disposable
     */
    void addSubcriber(Disposable disposable);

    /**
     * 订阅事件
     */
    void subscribleEvent();

    boolean getNoImageState();

    boolean getAutoCacheState();

    boolean getNightModeState();

    boolean getStatusBarState();

    boolean getAutoUpdataState();
}
