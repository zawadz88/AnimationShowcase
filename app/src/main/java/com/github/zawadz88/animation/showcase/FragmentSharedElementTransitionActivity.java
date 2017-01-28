package com.github.zawadz88.animation.showcase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.github.zawadz88.animation.showcase.fragment.FragmentSharedElementTransitionSource;

/**
 * #####
 * #####
 * See: https://medium.com/@bherbst/fragment-transitions-with-shared-elements-7c7d71d31cbb#.e7uepyyrg
 * #####
 * #####
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FragmentSharedElementTransitionActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_shared_element_transition);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentSharedElementTransitionSource())
                    .commit();
        }
    }

}
