/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.zawadz88.animation.showcase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import butterknife.BindView;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/transitions/index.html
 * #####
 * #####
 */
public class TransitionActivity extends AbstractActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.select_scene)
    RadioGroup radioGroup;

    /**
     * Transitions take place in this ViewGroup. We retain this for the dynamic transition on scene 4.
     */
    @BindView(R.id.scene_root)
    ViewGroup sceneRoot;

    // We transition between these Scenes
    private Scene mScene1;

    private Scene mScene2;

    private Scene mScene3;

    /** A custom TransitionManager */
    private TransitionManager mTransitionManagerForScene3;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        radioGroup.setOnCheckedChangeListener(this);


        // A Scene can be instantiated from a live view hierarchy.
        mScene1 = new Scene(sceneRoot, (ViewGroup) sceneRoot.findViewById(R.id.container));

        // You can also inflate a generate a Scene from a layout resource file.
        mScene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this);


        // Another scene from a layout resource file.
        mScene3 = Scene.getSceneForLayout(sceneRoot, R.layout.scene3, this);


        // We create a custom TransitionManager for Scene 3, in which ChangeBounds and Fade
        // take place at the same time.
        mTransitionManagerForScene3 = TransitionInflater.from(this)
                .inflateTransitionManager(R.transition.scene3_transition_manager, sceneRoot);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View square = sceneRoot.findViewById(R.id.transition_square);
        switch (checkedId) {
            case R.id.select_scene_1: {
                square.setVisibility(View.VISIBLE);

                // You can start an automatic transition with TransitionManager.go().
                TransitionManager.go(mScene1);

                break;
            }
            case R.id.select_scene_2: {
                square.setVisibility(View.VISIBLE);
                TransitionManager.go(mScene2);
                break;
            }
            case R.id.select_scene_3: {
                square.setVisibility(View.VISIBLE);

                // You can also start a transition with a custom TransitionManager.
                mTransitionManagerForScene3.transitionTo(mScene3);

                break;
            }
            case R.id.select_scene_4: {

                // Alternatively, transition can be invoked dynamically without a Scene.
                // For this, we first call TransitionManager.beginDelayedTransition().
                TransitionManager.beginDelayedTransition(sceneRoot);
                // Then, we can just change view properties as usual.
                square.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams params = square.getLayoutParams();
                int newSize = getResources().getDimensionPixelSize(R.dimen.square_size_expanded);
                params.width = newSize;
                params.height = newSize;
                square.setLayoutParams(params);

                break;
            }
            case R.id.select_scene_5: {
                TransitionInflater transitionInflater = TransitionInflater.from(this);
                Transition transition = transitionInflater.inflateTransition(R.transition.changebounds_fadeout_sequential);
                TransitionManager.beginDelayedTransition(sceneRoot, transition);

                square.setVisibility(View.GONE);
                break;
            }
        }
    }
}
