package com.github.zawadz88.animation.showcase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import butterknife.BindView;

/**
 * #####
 * #####
 * MUST READ: https://medium.com/google-developers/transitions-in-the-android-support-library-8bc86a1d688e#.w2iejzoz8
 * #####
 * #####
 * @see TransitionActivity
 */
public class SupportTransitionActivity extends AbstractActivity implements RadioGroup.OnCheckedChangeListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        radioGroup.setOnCheckedChangeListener(this);


        // A Scene can be instantiated from a live view hierarchy.
        mScene1 = new Scene(sceneRoot, sceneRoot.findViewById(R.id.container));

        // You can also inflate a generate a Scene from a layout resource file.
        mScene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this);


        // Another scene from a layout resource file.
        mScene3 = Scene.getSceneForLayout(sceneRoot, R.layout.scene3, this);


        // We create a custom TransitionManager for Scene 3, in which ChangeBounds and Fade
        // take place at the same time.

        /* Same as:

        mTransitionManagerForScene3 = TransitionInflater.from(this)
                .inflateTransitionManager(R.transition.scene3_transition_manager, sceneRoot);

        + in transition/scene3_transition_manager.xml

                <transitionManager xmlns:android="http://schemas.android.com/apk/res/android">
                    <transition
                        android:toScene="@layout/scene3"
                        android:transition="@transition/changebounds_fadein_together"/>
                </transitionManager>

        + in transition/changebounds_fadein_together.xml

        <transitionSet xmlns:android="http://schemas.android.com/apk/res/android">
            <changeBounds/>
            <fade android:fadingMode="fade_in">
                <targets>
                    <target android:targetId="@id/transition_title" />
                </targets>
            </fade>
        </transitionSet>
        */

        TransitionSet scene3Transitions = new TransitionSet();
        scene3Transitions
                .addTransition(new ChangeBounds())
                .addTransition(new Fade(Fade.IN).addTarget(R.id.transition_title));
        mTransitionManagerForScene3 = new TransitionManager();
        mTransitionManagerForScene3.setTransition(mScene3, scene3Transitions);
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
                /* Same as:

                TransitionInflater transitionInflater = TransitionInflater.from(this);
                Transition transition = transitionInflater.inflateTransition(R.transition.changebounds_fadeout_sequential);

                + in transition/changebounds_fadeout_sequential.xml:

                <transitionSet xmlns:android="http://schemas.android.com/apk/res/android"
                    android:transitionOrdering="sequential">
                    <changeBounds/>
                    <fade android:fadingMode="fade_out">
                        <targets>
                            <target android:targetId="@id/transition_square" />
                        </targets>
                    </fade>
                </transitionSet>
                */
                TransitionSet transitionSet = new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeBounds())
                    .addTransition(new Fade(Fade.OUT).addTarget(R.id.transition_square));

                transitionSet.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(@NonNull Transition transition) {
                        System.out.println("transition START");
                    }

                    @Override
                    public void onTransitionEnd(@NonNull Transition transition) {
                        System.out.println("transition END");
                    }

                    @Override
                    public void onTransitionCancel(@NonNull Transition transition) {
                        System.out.println("transition CANCEL");
                    }

                    @Override
                    public void onTransitionPause(@NonNull Transition transition) {
                        System.out.println("transition PAUSE");
                    }

                    @Override
                    public void onTransitionResume(@NonNull Transition transition) {
                        System.out.println("transition RESUME");
                    }
                });
                TransitionManager.beginDelayedTransition(sceneRoot, transitionSet);

                square.setVisibility(View.GONE);
                break;
            }
        }
    }
}
