package com.vv.learn.rxjava.behaviour;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ShenZhenWei
 * @date 2019/1/15
 */
public class FbtnBehaviour extends CoordinatorLayout.Behavior<View> {
    public FbtnBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof BottomNavigationView;//这里关系底部导航栏的滑动状态
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        float translationY = dependency.getTranslationY();
        child.setTranslationY(translationY);
        return true;
    }
}
