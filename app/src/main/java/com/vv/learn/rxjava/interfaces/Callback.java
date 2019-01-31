package com.vv.learn.rxjava.interfaces;

/**
 * @author ShenZhenWei
 * @date 2019/1/31
 */
public interface Callback<K, V> {
    void onSuccess(K data);

    void onFail(V data);
}
