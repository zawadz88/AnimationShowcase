package com.github.zawadz88.animation.showcase;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://android-developers.googleblog.com/2011/05/introducing-viewpropertyanimator.html
 * See: https://developer.android.com/guide/topics/graphics/prop-animation.html#view-prop-animator
 * #####
 * #####
 */
public class ViewPropertyAnimatorActivity extends AbstractActivity {

    public static final long FULL_ANIMATION_DURATION = 3000L;

    @BindView(R.id.icon)
    View icon;

    private boolean animateForward = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);
    }

    @OnClick(R.id.rotation)
    public void onRotation() {
        float targetRotation = animateForward
                ? 180.0f
                : 0.0f;
        animateForward = !animateForward;
        icon.animate()
                .rotation(targetRotation)
                .setDuration(FULL_ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @OnClick(R.id.adaptive_rotation)
    public void onAdaptiveRotation() {
        float targetRotation = animateForward
                ? 180.0f
                : 0.0f;
        float currentRotation = icon.getRotation();
        float actualRotationDelta = Math.abs(targetRotation - currentRotation);
        long duration = (long) (FULL_ANIMATION_DURATION * actualRotationDelta / 180.0f);
        ViewPropertyAnimator animator = icon.animate();
        if (duration < FULL_ANIMATION_DURATION / 2) {
            animator.setInterpolator(new AccelerateInterpolator());
        } else {
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        animator
                .rotation(targetRotation)
                .setDuration(duration);
        animateForward = !animateForward;
    }

    @OnClick(R.id.translateAndFade)
    public void onTranslateAndFade() {
        icon.animate()
                .alpha(animateForward ? 0.0f : 1.0f)
                .translationY(animateForward ? icon.getHeight() * 4 : 0.0f)
                .translationX(animateForward ? icon.getWidth() * 2 : 0.0f)
                .setDuration(FULL_ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());
        animateForward = !animateForward;
    }

    @OnClick(R.id.scaleAndFlip)
    public void onScaleAndFlip() {
        icon.animate()
                .scaleX(animateForward ? 3.0f : 1.0f)
                .scaleY(animateForward ? 3.0f : 1.0f)
                .rotationY(animateForward ? -180.0f : 0.0f)
                .setDuration(FULL_ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator());
        animateForward = !animateForward;
    }

}
