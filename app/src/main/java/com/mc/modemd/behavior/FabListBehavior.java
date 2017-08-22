package com.mc.modemd.behavior;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/17.
 */

public class FabListBehavior extends FloatingActionButton.Behavior {

    private static final int MIN_CHANGED_DISTANCE = 30;

    public FabListBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > MIN_CHANGED_DISTANCE) {
            createValueAnimator(coordinatorLayout, child, false).start();
        } else if (dyConsumed < -MIN_CHANGED_DISTANCE) {
            createValueAnimator(coordinatorLayout, child, true).start();
        }
    }

    private Animator createValueAnimator(CoordinatorLayout coordinatorLayout, final View fab, boolean dismiss) {
        int distanceToDismiss = coordinatorLayout.getBottom() - fab.getBottom() + fab.getHeight();
        int end = dismiss ? 0 : distanceToDismiss;
        float start = fab.getTranslationY();
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fab.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        return animator;
    }
}
