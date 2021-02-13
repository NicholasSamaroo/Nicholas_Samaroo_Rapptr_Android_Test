package com.datechnologies.androidtest.animation;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;

/**
 * Screen that displays the D & A Technologies logo.
 * The icon can be moved around on the screen as well as animated.
 */

public class AnimationActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        final ImageView logo = findViewById(R.id.animationLogo);
        Button fadeInButton = findViewById(R.id.fadeIn);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(1000);

        /*final AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(fadeOut);
        animationSet.addAnimation(fadeIn);*/

        // The listener for the "Fade in" button found in the activity is below. OnClick
        // of the button we start the fade out animation. The fade in and fade out animations
        // are found above. A listener for thr fade out animation is also set so when that completes
        // we can start the fade in animation
        fadeInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logo.startAnimation(fadeOut);
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        logo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(logo);

                    // startDrag() deprecated in API 24
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        logo.startDragAndDrop(data, shadowBuilder, logo, 0);
                    } else {
                        logo.startDrag(data, shadowBuilder, logo, 0);
                    }
                    logo.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });


        findViewById(R.id.relativeLayout).setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int eventType = event.getAction();
                switch (eventType) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DROP:
                        logo.setX(event.getX());
                        logo.setY(event.getY());
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        v = (View) event.getLocalState();
                        v.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
    }


// TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
// TODO: Add a ripple effect when the buttons are clicked

// TODO: When the fade button is clicked, you must animate the D & A Technologies logo.
// TODO: It should fade from 100% alpha to 0% alpha, and then from 0% alpha to 100% alpha

// TODO: The user should be able to touch and drag the D & A Technologies logo around the screen.

// TODO: Add a bonus to make yourself stick out. Music, color, fireworks, explosions!!!


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
