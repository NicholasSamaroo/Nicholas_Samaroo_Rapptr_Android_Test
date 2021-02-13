package com.datechnologies.androidtest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.datechnologies.androidtest.animation.AnimationActivity;
import com.datechnologies.androidtest.chat.ChatActivity;
import com.datechnologies.androidtest.login.LoginActivity;

/**
 * The main screen that lets you navigate to all other screens in the app.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // I set the theme for this activity in the manifest, and then also set this line here
        // so we can get the splash screen on app launch
        setTheme(R.style.splashScreenTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.activity_main_title);

        /*
         * =========================================================================================
         * INSTRUCTIONS
         * =========================================================================================
         *
         * 1. UI must work on Android phones of multiple sizes. Do not worry about Android Tablets.
         *
         * 2. Use this starter project as a base and build upon it. It is ok to remove some of the
         *    provided code if necessary.
         *
         * 3. Read the additional 'TODO' comments throughout the codebase, they will guide you.
         *
         * 3. Please take care of the bug(s) we left for you in the project as well.
         *
         * Thank you and Good luck. -  D & A Technologies
         * =========================================================================================
         */

        // TODO: Make the UI look like it does in the mock-up
        // TODO: Add a ripple effect when the buttons are clicked

        /*
         * PLEASE SEE THE README.MD FILE ADDED WITH THIS PROJECT TO SEE
         * MY PROJECT BREAKDOWN
         */
    }

    //==============================================================================================
    // Button Click Methods
    //==============================================================================================

    public void onChatClicked(View v) {
        ChatActivity.start(this);
    }

    public void onLoginClicked(View v) {
        LoginActivity.start(this);
    }

    public void onAnimationClicked(View v) {
        AnimationActivity.start(this);
    }
}
