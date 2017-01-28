package com.github.zawadz88.animation.showcase;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/material/animations.html#ViewState
 * #####
 * #####
 */
public class AnimatedSelectorActivity extends AbstractActivity {

    @BindView(R.id.expandcollapse)
    AppCompatImageView expandCollapseIcon;

    @BindView(R.id.star_icon)
    AppCompatImageView starIcon;

    private boolean animateForward = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_selector);
    }

    @OnClick(android.R.id.content)
    public void onClick() {
        final int[] stateSet = {android.R.attr.state_checked * (animateForward ? 1 : -1)};
        expandCollapseIcon.setImageState(stateSet, true);
        starIcon.setImageState(stateSet, true);
        animateForward = !animateForward;
    }

}
