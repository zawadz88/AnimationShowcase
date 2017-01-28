package com.github.zawadz88.animation.showcase;

import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.widget.AppCompatImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/material/animations.html#AnimVector
 * See: https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html
 * See: https://developer.android.com/guide/topics/resources/complex-xml-resources.html
 * More animated vectors: https://github.com/alexjlockwood/adp-delightful-details
 * #####
 * #####
 */
public class AnimatedVectorDrawableActivity extends AbstractActivity {

    @BindView(R.id.expandcollapse)
    AppCompatImageView icon;

    private boolean animateForward = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector_drawable);
        setDrawable();
    }

    @OnClick(android.R.id.content)
    public void onClick() {
        AnimatedVectorDrawableCompat animatable = setDrawable();
        if(!animatable.isRunning()) {
            animatable.start();
        }
        animateForward = !animateForward;
    }

    private AnimatedVectorDrawableCompat setDrawable() {
        int resId = animateForward
                ? R.drawable.avd_checkable_expandcollapse_expanded_to_collapsed
                : R.drawable.avd_checkable_expandcollapse_collapsed_to_expanded;
        AnimatedVectorDrawableCompat animatable = AnimatedVectorDrawableCompat.create(this, resId);
        icon.setImageDrawable(animatable);
        return animatable;
    }

}
