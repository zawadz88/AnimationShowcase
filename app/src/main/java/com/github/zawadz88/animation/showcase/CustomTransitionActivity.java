package com.github.zawadz88.animation.showcase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.ViewGroup;

import com.github.zawadz88.animation.showcase.transition.FancyTransition;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/transitions/index.html
 * #####
 * #####
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CustomTransitionActivity extends AbstractActivity {

    @BindView(R.id.scene_root)
    ViewGroup sceneRoot;

    private Scene mScene1;

    private Scene mScene2;

    private boolean mIsInScene2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition);

        mScene1 = Scene.getSceneForLayout(sceneRoot, R.layout.custom_scene1, this);
        mScene2 = Scene.getSceneForLayout(sceneRoot, R.layout.custom_scene2, this);
    }

    @OnClick(R.id.transition_square)
    public void onSquareClicked() {
        Transition transition = createSceneTransition();

        if (mIsInScene2) {
            TransitionManager.go(mScene1, transition);
        } else {
            TransitionManager.go(mScene2, transition);
        }
        mIsInScene2 = !mIsInScene2;
    }

    @NonNull
    private Transition createSceneTransition() {
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new FancyTransition()
                        .excludeTarget(R.id.transition_textview, true))
                .setDuration(1000L);
        transitionSet.setPathMotion(new ArcMotion());
        transitionSet.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                //rebind after scene transition so that clicks work
                ButterKnife.bind(CustomTransitionActivity.this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return transitionSet;
    }

/* Uncomment to see the default transition
    @NonNull
    private Transition createSceneTransition() {
        AutoTransition transition = new AutoTransition();
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                //rebind after scene transition so that clicks work
                ButterKnife.bind(CustomTransitionActivity.this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return transition;
    }*/

}
