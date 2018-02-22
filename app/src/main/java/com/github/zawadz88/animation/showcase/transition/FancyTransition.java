package com.github.zawadz88.animation.showcase.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class FancyTransition extends Transition {

    private static final String PROPNAME_SIZE = "animationshowcase:size";

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_SIZE, transitionValues.view.getHeight());
    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_SIZE, transitionValues.view.getHeight());
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        if (!startValues.values.containsKey(PROPNAME_SIZE) || !endValues.values.containsKey(PROPNAME_SIZE)) {
            return null;
        }

        System.out.println("Creating animator for view: " + view);

        return ObjectAnimator.ofFloat(view, View.ROTATION, 0.0f, 90.0f, 180.0f, 270.0f, 360f);
    }
}
