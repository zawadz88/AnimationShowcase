package com.github.zawadz88.animation.showcase;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/guide/topics/graphics/prop-animation.html#layout
 * #####
 * #####
 */
public class CustomLayoutAnimationsActivity extends AbstractActivity {

    private int numButtons = 1;

    @BindView(R.id.buttonContainer)
    ViewGroup gridContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_animations);


        LayoutTransition layoutTransition = new LayoutTransition();

        ObjectAnimator appearingAnim = ObjectAnimator.ofFloat(null, "rotationY", 0f, 90f, 180f, 270f, 359.9999f);
        appearingAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        layoutTransition.setAnimator(LayoutTransition.APPEARING, appearingAnim);
        layoutTransition.setDuration(LayoutTransition.APPEARING, 2000L);
        layoutTransition.setStartDelay(LayoutTransition.APPEARING, 0);

        gridContainer.setLayoutTransition(layoutTransition);
    }

    @OnClick(R.id.addNewButton)
    public void addButton() {
        Button newButton = new Button(this);
        ViewCompat.setElevation(newButton, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            newButton.setStateListAnimator(null);
        }
        newButton.setText(String.valueOf(numButtons++));
        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridContainer.removeView(v);
            }
        });
        gridContainer.addView(newButton, Math.min(1, gridContainer.getChildCount()));
    }

}
