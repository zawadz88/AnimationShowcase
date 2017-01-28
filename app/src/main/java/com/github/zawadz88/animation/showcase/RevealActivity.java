package com.github.zawadz88.animation.showcase;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/material/animations.html#Reveal
 * #####
 * #####
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class RevealActivity extends AbstractActivity {

    @BindView(R.id.revealContainer)
    View revealContainer;

    private boolean animateForward = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
    }

    @OnClick(R.id.reveal)
    public void onReveal() {

        // get the center for the clipping circle
        int cx = revealContainer.getWidth() / 2;
        int cy = revealContainer.getHeight() / 2;

        // get the radius for the clipping circle
        float radius = (float) Math.hypot(cx, cy);
        Animator anim;

        if (animateForward) {
            // create the animator for this view (the start radius is zero)
            anim = ViewAnimationUtils.createCircularReveal(revealContainer, cx, cy, 0, radius);

            // make the view visible and start the animation
            revealContainer.setVisibility(View.VISIBLE);
        } else {
            // create the animation (the final radius is zero)
            anim = ViewAnimationUtils.createCircularReveal(revealContainer, cx, cy, radius, 0);

            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    revealContainer.setVisibility(View.INVISIBLE);
                }
            });
        }

        anim.start();
        animateForward = !animateForward;
    }


}
