package com.github.zawadz88.animation.showcase;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.OnClick;

public class MainActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.animations)
    public void showAnimations() {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    @OnClick(R.id.drawableAnimations)
    public void showDrawableAnimations() {
        startActivity(new Intent(this, DrawableAnimationActivity.class));
    }

    @OnClick(R.id.windowAnimations)
    public void showWindowAnimations() {
        ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(this, R.anim.slide_up_from_bottom, R.anim.slide_up_to_top);
        startActivity(new Intent(this, WindowAnimationsActivity.class), activityOptions.toBundle());
        //Using ActivityOptions (since JB) is the same as calling the following after:
        //overridePendingTransition(R.anim.slide_up_from_bottom, R.anim.slide_up_to_top);
    }

    @OnClick(R.id.objectAnimators)
    public void showObjectAnimators() {
        startActivity(new Intent(this, ObjectAnimatorActivity.class));
    }

    @OnClick(R.id.viewPropertyAnimators)
    public void showViewPropertyAnimators() {
        startActivity(new Intent(this, ViewPropertyAnimatorActivity.class));
    }

    @OnClick(R.id.defaultLayoutAnimations)
    public void showDefaultLayoutAnimations() {
        startActivity(new Intent(this, DefaultLayoutAnimationsActivity.class));
    }

    @OnClick(R.id.customLayoutAnimations)
    public void showCustomLayoutAnimations() {
        startActivity(new Intent(this, CustomLayoutAnimationsActivity.class));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.transitions)
    public void showTransitions() {
        if (isKitKatOrAbove()) {
            startActivity(new Intent(this, TransitionActivity.class));
        } else {
            showUnsupportedAPIError(Build.VERSION_CODES.KITKAT);
        }
    }

    @OnClick(R.id.supportTransitions)
    public void showSupportTransitions() {
        startActivity(new Intent(this, SupportTransitionActivity.class));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.activityTransitions)
    public void showActivityTransitions() {
        if (isLollipopOrAbove()) {
            startActivity(new Intent(this, ActivityTransitionActivity.class));
        } else {
            showUnsupportedAPIError(Build.VERSION_CODES.LOLLIPOP);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.activityTransitionsNougatFix)
    public void showActivityTransitionsNougatFix() {
        if (isLollipopOrAbove()) {
            startActivity(new Intent(this, ActivityTransitionNougatFixActivity.class));
        } else {
            showUnsupportedAPIError(Build.VERSION_CODES.LOLLIPOP);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.activityTransitionsWithSharedElement)
    public void showSharedElementActivityTransitionsNougatFix() {
        if (isLollipopOrAbove()) {
            startActivity(new Intent(this, ActivitySharedElementTransitionActivity.class));
        } else {
            showUnsupportedAPIError(Build.VERSION_CODES.LOLLIPOP);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.fragmentTransitionsWithSharedElement)
    public void showSharedElementFragmentTransitionsNougatFix() {
        startActivity(new Intent(this, FragmentSharedElementTransitionActivity.class));
        showPartiallySupportedAPIInfoIfNeeded();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.revealAnimation)
    public void showRevealAnimation() {
        if (isLollipopOrAbove()) {
            startActivity(new Intent(this, RevealActivity.class));
        } else {
            showUnsupportedAPIError(Build.VERSION_CODES.LOLLIPOP);
        }
    }

    @OnClick(R.id.animatedVectorDrawables)
    public void showAnimatedVectorDrawables() {
        startActivity(new Intent(this, AnimatedVectorDrawableActivity.class));
        showPartiallySupportedAPIInfoIfNeeded();
    }

    @OnClick(R.id.animatedSelectors)
    public void showAnimatedSelectors() {
        startActivity(new Intent(this, AnimatedSelectorActivity.class));
        showPartiallySupportedAPIInfoIfNeeded();
    }

    @OnClick(R.id.stateListAnimators)
    public void showStateListAnimators() {
        startActivity(new Intent(this, StateListAnimatorActivity.class));
        showPartiallySupportedAPIInfoIfNeeded();
    }

    @OnClick(R.id.recyclerViewAnimations)
    public void showRecyclerViewAnimations() {
        startActivity(new Intent(this, RecyclerViewAnimationActivity.class));
    }

    private boolean isKitKatOrAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    private boolean isLollipopOrAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private void showUnsupportedAPIError(int minimumVersionInt) {
        Toast.makeText(this, "Minimum API version to run this is: " + minimumVersionInt, Toast.LENGTH_SHORT).show();
    }

    private void showPartiallySupportedAPIInfoIfNeeded() {
        if (!isLollipopOrAbove()) {
            Toast.makeText(this, "Run on Lollipop to see the desired behavior", Toast.LENGTH_SHORT).show();
        }
    }

}
