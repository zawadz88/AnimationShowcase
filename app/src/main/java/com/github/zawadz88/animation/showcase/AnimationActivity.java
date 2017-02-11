package com.github.zawadz88.animation.showcase;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/guide/topics/graphics/view-animation.html
 * #####
 * #####
 */
public class AnimationActivity extends AbstractActivity {

    @BindView(R.id.star_icon)
    View starIcon;

    private Animation inifiniteRotationAnimation;

    private Animation basicRotationAnimation;

    private Animation fadeAndMoveAnimation;

    private Animation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        basicRotationAnimation = AnimationUtils.loadAnimation(this, R.anim.rotation_basic);
        basicRotationAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                System.out.println("basicRotationAnimation START");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("basicRotationAnimation END");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                System.out.println("basicRotationAnimation REPEAT");
            }
        });
        inifiniteRotationAnimation = AnimationUtils.loadAnimation(this, R.anim.rotation_infinite);
        inifiniteRotationAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                System.out.println("inifiniteRotationAnimation START");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("inifiniteRotationAnimation END");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                System.out.println("inifiniteRotationAnimation REPEAT");
            }
        });
        fadeAndMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_and_move);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
    }

    @OnClick(R.id.single_rotation)
    public void onSingleRotation() {
        starIcon.startAnimation(basicRotationAnimation);
    }

    @OnClick(R.id.infinite_rotation)
    public void onInfiniteRotation() {
        starIcon.startAnimation(inifiniteRotationAnimation);
    }

    @OnClick(R.id.fade)
    public void onFadeAndMove() {
        starIcon.startAnimation(fadeAndMoveAnimation);
    }

    @OnClick(R.id.scale)
    public void onScale() {
        starIcon.startAnimation(scaleAnimation);
    }
}
