package com.vv.learn.rxjava.api;

import com.vv.learn.rxjava.bean.ArticleListBean;
import com.vv.learn.rxjava.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ShenZhenWei
 * @date 2019/1/31
 */
public interface Api {
    /**
     * wanandroid 首页文章列表
     *
     * @param curPage 当前第几页
     * @return
     */
    @GET("article/list/{curPage}/json")
    Observable<ArticleListBean> getData(@Path("curPage") int curPage);

    /**
     * 获取首页banner数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<BannerBean> getBanner();
}
