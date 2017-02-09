package com.github.zawadz88.animation.showcase;

import android.os.Bundle;
import android.widget.TextSwitcher;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: http://abhiandroid.com/ui/viewanimator
 * See: http://abhiandroid.com/ui/viewflipper
 * See: http://abhiandroid.com/ui/viewswitcher
 * See: http://www.learn-android-easily.com/2013/06/android-textswitcher.html
 * #####
 * #####
 */
public class ViewAnimatorsActivity extends AbstractActivity {

    @BindView(R.id.viewAnimator)
    ViewAnimator viewAnimator;

    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;

    @BindView(R.id.textSwitcher)
    TextSwitcher textSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animators);
    }

    @OnClick(android.R.id.content)
    public void onClick() {
        viewAnimator.showNext();
        viewSwitcher.showNext();
        textSwitcher.setText(UUID.randomUUID().toString());
        //If autoStart disabled
        //viewFlipper.startFlipping();
    }

}
