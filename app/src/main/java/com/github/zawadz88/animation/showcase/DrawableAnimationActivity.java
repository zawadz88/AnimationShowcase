package com.github.zawadz88.animation.showcase;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/guide/topics/graphics/drawable-animation.html
 * #####
 * #####
 */
public class DrawableAnimationActivity extends AbstractActivity {

    @BindView(R.id.star_icon)
    AppCompatImageView starIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
    }

    @OnClick(android.R.id.content)
    public void onClick() {
        AnimationDrawable animationDrawable = (AnimationDrawable) starIcon.getDrawable();
        animationDrawable.stop();
        animationDrawable.start();
    }

}
