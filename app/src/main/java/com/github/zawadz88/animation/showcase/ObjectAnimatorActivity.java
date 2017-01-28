package com.github.zawadz88.animation.showcase;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/guide/topics/graphics/prop-animation.html#object-animator
 * #####
 * #####
 */
public class ObjectAnimatorActivity extends AbstractActivity {

    public static final long FULL_ANIMATION_DURATION = 3000L;

    @BindView(R.id.icon)
    View icon;

    private boolean animateForward = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
    }

    @OnClick(R.id.rotation)
    public void onRotation() {
        float targetRotation = animateForward
                ? 180.0f
                : 0.0f;
        animateForward = !animateForward;
        ValueAnimator animator = ObjectAnimator.ofFloat(icon, "rotation", icon.getRotation(), targetRotation);
        animator.setDuration(FULL_ANIMATION_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @OnClick(R.id.translateAndFade)
    public void onTranslateAndFade() {
        ValueAnimator xAnimator = ObjectAnimator.ofFloat(icon, "translationX", icon.getTranslationX(), animateForward ? icon.getWidth() * 2 : 0.0f);
        ValueAnimator yAnimator = ObjectAnimator.ofFloat(icon, "translationY", icon.getTranslationY(), animateForward ? icon.getHeight() * 4 : 0.0f);
        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(icon, "alpha", icon.getAlpha(), animateForward ? 0.0f : 1.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(FULL_ANIMATION_DURATION);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(xAnimator, yAnimator, alphaAnimator);
        animatorSet.start();

        animateForward = !animateForward;
    }

    @OnClick(R.id.translateAndFadeWithPropertyValuesHolder)
    public void onTranslateAndFadeWithPropertyValuesHolder() {
        /*
         * https://android-developers.googleblog.com/2011/05/introducing-viewpropertyanimator.html
         * The code above code creates three separate animations and plays them together in an AnimatorSet.
         * This means that there is the processing overhead of setting up the AnimatorSet
         * and running three Animators in parallel to animate these x/y/alpha properties.
         * There is an alternative approach using PropertyValuesHolder that you can use to combine multiple properties inside of one single Animator:
         */
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("translationX", icon.getTranslationX(), animateForward ? icon.getWidth() * 2 : 0.0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationY", icon.getTranslationY(), animateForward ? icon.getHeight() * 4 : 0.0f);
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha", icon.getAlpha(), animateForward ? 0.0f : 1.0f);

        Animator animator = ObjectAnimator.ofPropertyValuesHolder(icon, pvhX, pvhY, pvhAlpha);
        animator.setDuration(FULL_ANIMATION_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

        animateForward = !animateForward;
    }

    @OnClick(R.id.fadeFromXML)
    public void onFadeFromXML() {
        Animator animator = AnimatorInflater.loadAnimator(this, animateForward ? R.animator.fade_out : R.animator.fade_in);
        animator.setTarget(icon);
        animator.start();
        animateForward = !animateForward;
    }

}
