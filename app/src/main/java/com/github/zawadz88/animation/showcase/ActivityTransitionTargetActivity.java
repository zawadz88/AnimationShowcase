package com.github.zawadz88.animation.showcase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/material/animations.html#Transitions
 * For custom transitions see: https://github.com/nickbutcher/plaid/tree/master/app/src/main/java/io/plaidapp/ui/transitions
 * #####
 * #####
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActivityTransitionTargetActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transition_target);
    }

}
