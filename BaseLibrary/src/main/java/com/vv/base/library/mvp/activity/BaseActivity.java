package com.vv.base.library.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vv.base.library.R;
import com.vv.base.library.mvp.view.BaseView;
import com.vv.base.library.utils.StatusBarUtil;
import com.vv.base.library.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ShenZhenWei
 * @date 2018/12/28
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private Unbinder mUnbinder;
    private TextView mTipView;
    protected boolean isEnableTip = true;

    /**
     * 获取资源布局的Id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        if (mTipView != null && mTipView.getParent() != null) {
            ((ViewGroup) getWindow().getDecorView()).removeView(mTipView);
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
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void setStausBarColor(boolean isSet) {
        if (isSet) {
            StatusBarUtil.immersive(this, getResources().getColor(R.color.colorPrimary));
        } else {
            StatusBarUtil.immersive(this, getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public void showTipView(boolean isConnection) {
        if (!isEnableTip) {
            return;
        }
        if (mTipView == null) {
            mTipView = new TextView(this);
        }
        if (isConnection) {
            if (mTipView.getParent() != null) {
                ((ViewGroup) getWindow().getDecorView()).removeView(mTipView);
                reload();
            }
        } else {
            if (mTipView.getParent() != null) {
                return;
            }
            ToastUtils.toastMake(mTipView,
                    (ViewGroup) getWindow().getDecorView(),
                    getString(R.string.error_http),
                    ContextCompat.getColor(this, R.color.colorTipBackground),
                    ContextCompat.getColor(this, R.color.colorTip));

        }
    }
}
