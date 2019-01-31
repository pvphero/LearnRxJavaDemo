package com.vv.learn.rxjava.demo;

import com.vv.base.library.utils.LogUtils;
import com.vv.learn.rxjava.api.Api;
import com.vv.learn.rxjava.bean.ArticleListBean;
import com.vv.learn.rxjava.interfaces.Callback;
import com.vv.learn.rxjava.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ShenZhenWei
 * @date 2019/1/31
 */
public class SingleInterfaceModel {

    private static final String TAG = "SingleInterfaceModel";

    public void getData(int curPage, Callback calback) {
        NetUtils.getRetrofit()
                .create(Api.class)
                .getData(curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleListBean articleListBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
    }

}
