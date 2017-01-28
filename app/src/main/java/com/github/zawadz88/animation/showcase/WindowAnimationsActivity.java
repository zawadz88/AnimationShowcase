package com.github.zawadz88.animation.showcase;

import android.os.Bundle;

/**
 * #####
 * #####
 * See: https://developer.android.com/guide/topics/graphics/view-animation.html
 * #####
 * #####
 */
public class WindowAnimationsActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_animations);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_down_from_top, R.anim.slide_down_to_bottom);
    }
}
