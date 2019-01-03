package com.vv.base.library.mvp.presenter;

import com.vv.base.library.mvp.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author ShenZhenWei
 * @date 2019/1/2
 */
public class BasePresenter<T extends BaseView> implements IPresenter<T> {

    protected T mView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter() {
        //这里需要绑定model
    }

    @Override
    public void attachView(T view) {
        this.mView = view;

    }

    @Override
    public boolean isAttachView() {
        return this.mView != null;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void addSubcriber(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribleEvent() {

    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public boolean getStatusBarState() {
        return false;
    }

    @Override
    public boolean getAutoUpdataState() {
        return false;
    }
}
