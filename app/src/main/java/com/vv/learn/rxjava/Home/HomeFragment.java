package com.vv.learn.rxjava.Home;

import com.vv.base.library.mvp.fragment.BaseFragment;
import com.vv.learn.rxjava.R;

/**
 * @author ShenZhenWei
 * @date 2019/1/15
 */
public class HomeFragment extends BaseFragment {
    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}
