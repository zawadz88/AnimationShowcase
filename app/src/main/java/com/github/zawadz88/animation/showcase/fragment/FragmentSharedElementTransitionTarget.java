package com.github.zawadz88.animation.showcase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zawadz88.animation.showcase.R;

/**
 * @author Piotr Zawadzki (Piotr.Zawadzki@stepstone.com)
 */

public class FragmentSharedElementTransitionTarget extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shared_element_transition_target, container, false);
    }

}
