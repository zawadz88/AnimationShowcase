package com.github.zawadz88.animation.showcase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/material/animations.html#Transitions
 * See: http://stackoverflow.com/a/26748694/973379
 * For custom transitions see: https://github.com/nickbutcher/plaid/tree/master/app/src/main/java/io/plaidapp/ui/transitions
 * #####
 * #####
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActivityTransitionNougatFixTargetActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transition_target);

        // Postpone the transition until the window's decor view has
        // finished its layout.
        postponeEnterTransition();

        final View decor = getWindow().getDecorView();
        decor.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decor.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });
    }

}
