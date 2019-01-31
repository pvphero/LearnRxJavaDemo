package com.vv.learn.rxjava;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.vv.base.library.mvp.activity.BaseActivity;
import com.vv.base.library.mvp.fragment.BaseFragment;
import com.vv.learn.rxjava.Home.HomeFragment;
import com.vv.learn.rxjava.hierarchy.HierarchyFragmnet;

import butterknife.BindView;


public class MainActivity extends BaseActivity {


    @BindView(R.id.bnv_btn)
    BottomNavigationView btnBtm;

    Fragment[] mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        btnBtm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.item_home:
//                        break;
//                    case R.id.item_hierarchy:
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new BaseFragment[2];
        if (savedInstanceState == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = HierarchyFragmnet.newInstance();
            loadMultipleFragment(R.id.fl_container, 0, mFragments);
        } else {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = HierarchyFragmnet.newInstance();
//            btnBtm.setSelectedItemId(getSelectedId(mp));
        }
    }

    private void loadMultipleFragment(int containerId, int showFragment, Fragment... fragments) {
        FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            transation.add(containerId, fragment, fragment.getClass().getName());
            if (i != showFragment) {
                transation.hide(fragment);
            }
        }
        transation.commitAllowingStateLoss();
    }

    private void showAndHideFragment(Fragment show, Fragment hide) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show != hide) {
            transaction.show(show).hide(hide).commitAllowingStateLoss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
