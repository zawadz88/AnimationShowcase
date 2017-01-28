package com.github.zawadz88.animation.showcase;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

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
public class ActivityTransitionNougatFixActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transition);
    }

    @OnClick(android.R.id.content)
    public void onClick() {
        View statusBar = findViewById(android.R.id.statusBarBackground);
        View navigationBar = findViewById(android.R.id.navigationBarBackground);

        List<Pair<View, String>> pairs = new ArrayList<>();
        if (statusBar != null) {
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }
        if (navigationBar != null) {
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        Bundle options = ActivityOptions.makeSceneTransitionAnimation(this,
                pairs.toArray(new Pair[pairs.size()])).toBundle();
        startActivity(new Intent(this, ActivityTransitionTargetActivity.class),
                options);

    }
}
