package com.vv.learn.rxjava.hierarchy;

import com.vv.base.library.mvp.fragment.BaseFragment;
import com.vv.learn.rxjava.R;

/**
 * @author ShenZhenWei
 * @date 2019/1/15
 */
public class HierarchyFragmnet extends BaseFragment {
    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy;
    }

    public static HierarchyFragmnet newInstance() {
        return new HierarchyFragmnet();
    }
}
