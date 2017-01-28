package com.github.zawadz88.animation.showcase.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zawadz88.animation.showcase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Piotr Zawadzki (Piotr.Zawadzki@stepstone.com)
 */

public class FragmentSharedElementTransitionSource extends Fragment {

    @BindView(R.id.square2)
    View square2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_element_transition_source, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.square2)
    public void onClick() {

        FragmentSharedElementTransitionTarget targetFragment = new FragmentSharedElementTransitionTarget();
        // FIXME: 08/02/2017 the shared element transitions to the next fragment below other views while it should be on top as in Activity transitions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.addTransition(new ChangeBounds());
            transitionSet.setPathMotion(new ArcMotion());
            targetFragment.setSharedElementEnterTransition(transitionSet);
            targetFragment.setSharedElementReturnTransition(transitionSet);
            targetFragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.slide_bottom));
            targetFragment.setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.slide_top_and_fade));
            setExitTransition(new Explode());
            setReenterTransition(new Explode());
            setAllowReturnTransitionOverlap(false);
        }
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(square2, getString(R.string.custom_element_transition_name))
                .replace(R.id.fragmentContainer, targetFragment)
                .addToBackStack(null)
                .commit();
    }

}
