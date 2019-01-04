package com.vv.base.library.mvp.activity;

import com.vv.base.library.mvp.presenter.IPresenter;

/**
 * @author ShenZhenWei
 * @date 2019/1/4
 */
public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity {

    protected abstract T getPresenter();

    protected T mPresenter;

    @Override
    protected void initView() {
        mPresenter = getPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
