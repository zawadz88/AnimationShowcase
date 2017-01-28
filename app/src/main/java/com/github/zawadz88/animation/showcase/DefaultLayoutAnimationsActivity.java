package com.github.zawadz88.animation.showcase;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * #####
 * #####
 * See: https://developer.android.com/guide/topics/graphics/prop-animation.html#layout
 * #####
 * #####
 */
public class DefaultLayoutAnimationsActivity extends AbstractActivity {

    private int numButtons = 1;

    @BindView(R.id.buttonContainer)
    ViewGroup gridContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_layout_animations);
    }

    @OnClick(R.id.addNewButton)
    public void addButton() {
        Button newButton = new Button(this);
        newButton.setText(String.valueOf(numButtons++));
        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridContainer.removeView(v);
            }
        });
        gridContainer.addView(newButton, Math.min(1, gridContainer.getChildCount()));
    }

}
