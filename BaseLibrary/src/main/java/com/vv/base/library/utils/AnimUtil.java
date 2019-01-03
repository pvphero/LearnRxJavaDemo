package com.vv.base.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class AnimUtil {

    private AnimUtil() {
        throw new AssertionError();
    }

    /**
     * 通过透明度显示控件
     */
    public static void showByAlpha(View view) {
        int shortAnimTime = SuperUtils.getApp().getResources().getInteger(android.R.integer.config_shortAnimTime);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1).setDuration(shortAnimTime).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        }).start();
    }

    /**
     * 通过透明度隐藏控件
     */
    public static void hideByAlpha(View view) {
        int shortAnimTime = SuperUtils.getApp().getResources().getInteger(android.R.integer.config_shortAnimTime);
        view.animate().alpha(0).setDuration(shortAnimTime).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }
        }).start();
    }
}
