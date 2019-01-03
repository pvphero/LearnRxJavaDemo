package com.vv.base.library.mvp.view;

/**
 * View接口
 *
 * @author ShenZhenWei
 * @date 2018/12/28
 */
public interface BaseView {
    /**
     * 显示错误view
     */
    void showErrorView();

    /**
     * 显示错误信息
     */
    void showErrorMes();

    /**
     * 重新加载
     */
    void reload();

    /**
     * 显示加载中的布局
     */
    void showLoading();

    /**
     * 显示正常的布局
     */
    void showNormalView();

    /**
     * 显示dialog
     */
    void showDialog();

    /**
     * 显示Toast
     *
     * @param toast toast信息
     */
    void showToast(String toast);

    /**
     * 禁止刷新
     */
    void unableRefrsh();

    /**
     * 夜间模式
     *
     * @param isNight 是否夜间模式
     */
    void userNightNode(boolean isNight);

    /**
     * 设置状态栏的颜色
     *
     * @param isSet 是否设置
     */
    void setStausBarColor(boolean isSet);

    /**
     * 显示网络状态信息
     *
     * @param isConnection 是否连接
     */
    void showTipView(boolean isConnection);
}
