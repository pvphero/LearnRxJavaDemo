package com.vv.learn.rxjava.behaviour;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ShenZhenWei
 * @date 2019/1/15
 */
public class BottomNavBehaviour extends CoordinatorLayout.Behavior<View> {

    private ObjectAnimator mHideAnimator, mShowAnimator;

    public BottomNavBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //只接受垂直方向上的滑动
        return axes == ViewGroup.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //dy是我们手指滑动的垂直方向，child为设置了此Behavior的View
        if (dy > 0) {
            //向上滑动是正值，隐藏
            hide(child);
        } else if (dx < 0) {
            //向下滑动是负值，显示
            show(child);
        }
    }

    private void show(View child) {
        if (mShowAnimator == null) {
            mShowAnimator = ObjectAnimator.ofFloat(child, "translationY", child.getHeight(), 0).setDuration(200);
            mShowAnimator.setInterpolator(new FastOutLinearInInterpolator());
        }

        if (!mShowAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
            mShowAnimator.start();
        }
    }

    private void hide(View child) {
        if (mHideAnimator == null) {
            mHideAnimator = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight()).setDuration(300);
            mHideAnimator.setInterpolator(new FastOutSlowInInterpolator());
        }

        if (!mHideAnimator.isRunning() && child.getTranslationY() <= 0) {
            mHideAnimator.start();
        }
    }
}
