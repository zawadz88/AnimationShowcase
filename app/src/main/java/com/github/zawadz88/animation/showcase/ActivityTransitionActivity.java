package com.github.zawadz88.animation.showcase;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/training/material/animations.html#Transitions
 * For custom transitions see: https://github.com/nickbutcher/plaid/tree/master/app/src/main/java/io/plaidapp/ui/transitions
 * #####
 * #####
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActivityTransitionActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transition);
    }

    @OnClick(android.R.id.content)
    public void onClick() {
        startActivity(new Intent(this, ActivityTransitionTargetActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
