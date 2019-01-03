package com.vv.base.library.mvp.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.vv.base.library.R;
import com.vv.base.library.utils.AnimUtil;

import static com.vv.base.library.config.Constant.ERROR_STATE;
import static com.vv.base.library.config.Constant.LOADING_STATE;
import static com.vv.base.library.config.Constant.NORMAL_STATE;

/**
 * @author ShenZhenWei
 * @date 2019/1/2
 */
public abstract class BaseLoadActivity extends BaseActivity {

    private View mErrorView;
    private View mLoadingView;
    private ViewGroup mNormalView;
    private ImageView mIvReload;
    private ObjectAnimator mReloadAnimator;
    private int mCurrentState = NORMAL_STATE;

    @Override
    protected void initView() {
        mNormalView = findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException("The subclass of BaseLoadActivity must contain a View it's id is named normal_view");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("mNormalView's ParentView should be a ViewGroup");
        }

        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        //把错误布局加载进正常布局的父布局中
        View.inflate(this, R.layout.error_view, parent);
        //把加载布局加载进正常布局的父布局中
        View.inflate(this, R.layout.loaging_view, parent);
        //从父布局找到错误布局实例
        mErrorView = parent.findViewById(R.id.cl_reload);
        //从父布局找到加载布局实例
        mLoadingView = parent.findViewById(R.id.rl_loading);
        mIvReload = mErrorView.findViewById(R.id.iv_reload);

        mErrorView.setOnClickListener(v -> reload());

        //初始化View状态
        mNormalView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
        mLoadingView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void reload() {
        startReloadAnimator();
        super.reload();
    }

    @Override
    protected void onDestroy() {
        if (mReloadAnimator != null) {
            mReloadAnimator.cancel();
        }
        super.onDestroy();
    }

    @SuppressLint("WrongConstant")
    private void startReloadAnimator() {
        mReloadAnimator = ObjectAnimator.ofFloat(mIvReload, "rotation", 0, 360).setDuration(600);
        mReloadAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mReloadAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mReloadAnimator.setRepeatMode(ValueAnimator.INFINITE);
        mReloadAnimator.start();
    }

    @Override
    public void showLoading() {
        if (mCurrentState == LOADING_STATE) {
            return;
        }
        hideCurrentViewByState();
        mCurrentState = LOADING_STATE;
        showCurrentViewByState();

    }

    @Override
    public void showErrorView() {
        if (mReloadAnimator != null) {
            mReloadAnimator.cancel();
        }
        if (mCurrentState == ERROR_STATE) {
            return;
        }
        hideCurrentViewByState();
        mCurrentState = ERROR_STATE;
        showCurrentViewByState();
    }

    @Override
    public void showNormalView() {
        if (mCurrentState == NORMAL_STATE) {
            return;
        }
        hideCurrentViewByState();
        mCurrentState = NORMAL_STATE;
        showCurrentViewByState();
    }


    /**
     * 显示当前根布局mCurrentState
     */
    private void showCurrentViewByState() {
        View showView = null;
        switch (mCurrentState) {
            case NORMAL_STATE:
                showView = mNormalView;
                break;
            case LOADING_STATE:
                showView = mLoadingView;
                break;
            case ERROR_STATE:
                showView = mErrorView;
                break;
            default:
                break;
        }
        if (showView == null) {
            return;
        }
        AnimUtil.showByAlpha(showView);
    }

    /**
     * 隐藏当前根布局mCurrentState
     */
    private void hideCurrentViewByState() {
        View hideView = null;
        switch (mCurrentState) {
            case NORMAL_STATE:
                hideView = mNormalView;
                break;
            case LOADING_STATE:
                hideView = mLoadingView;
                break;
            case ERROR_STATE:
                hideView = mErrorView;
                break;
            default:
                break;
        }
        if (hideView == null) {
            return;
        }
        AnimUtil.hideByAlpha(hideView);

    }
}
