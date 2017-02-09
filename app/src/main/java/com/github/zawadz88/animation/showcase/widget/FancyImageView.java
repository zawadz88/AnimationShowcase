package com.github.zawadz88.animation.showcase.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author Piotr Zawadzki (Piotr.Zawadzki@stepstone.com)
 */
public class FancyImageView extends AppCompatImageView {

    public FancyImageView(Context context) {
        super(context);
    }

    public FancyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FancyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTranslationXY(float translationXY) {
        setTranslationY(translationXY);
        setTranslationX(translationXY);
    }

    public float getTranslationXY() {
        return getTranslationX();
    }
}
