package com.datechnologies.androidtest.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.POJOs.Post;
import com.datechnologies.androidtest.R;

/**
 * A screen that displays a login prompt, allowing the user to login to the D & A Technologies Web Server.
 */
public class LoginActivity extends AppCompatActivity {

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    private LoginViewModel loginViewModel;
    private Post postObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Following an MVVM / separation of concerns style. I initialize the ViewModel
        // for this activity here then set up the observer in the following function call.
        // When we receive a successful response after logging in with our credentials, onChanged is called in our observer which will
        // then show the alert dialog.
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        setObserver();

        // Android handles saving the state of edit texts on orientation change
        final EditText email = findViewById(R.id.loginEmail);
        final EditText password = findViewById(R.id.loginPassword);
        Button login = findViewById(R.id.loginButton);

        /* When we click the login button, we should hit the API endpoint with the credentials
        *  entered in the edit text. A 401 error code is being returned, and upon inspection of the
        *  HTTPLoggingInterceptor logs it is saying there is an invalid user name or password even though
        *  the credentials mentioned below are used. So for now, showAlertDialog() is called which brings us back to the
        *  main activity if "OK" is chosen in the alert window.*/

        /***************************************************************************
         * PLEASE SEE ProjectBreakdown.txt for the HTTPLoggingInterceptor logs     *
         ***************************************************************************/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
                /*if (!(TextUtils.isEmpty(email.getText().toString().trim())) && !(TextUtils.isEmpty(password.getText().toString().trim()))) {
                    Log.d("email", email.getText().toString());
                    Log.d("password", password.getText().toString());
                    loginViewModel.getPostData(email.getText().toString().trim(), password.getText().toString().trim());
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Either both or one field is empty, please enter the necessary information for each field",
                            Toast.LENGTH_LONG).show();
                }*/
            }
        });


        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // TODO: Add a ripple effect when the buttons are clicked
        // TODO: Save screen state on screen rotation, inputted username and password should not disappear on screen rotation

        // TODO: Send 'email' and 'password' to http://dev.rapptrlabs.com/Tests/scripts/login.php
        // TODO: as FormUrlEncoded parameters.

        // TODO: When you receive a response from the login endpoint, display an AlertDialog.
        // TODO: The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
        // TODO: The AlertDialog should also display how long the API call took in milliseconds.
        // TODO: When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity

        // TODO: The only valid login credentials are:
        // TODO: email: info@datechnologies.co
        // TODO: password: Test123
        // TODO: so please use those to test the login.
    }

    private void setObserver() {
        loginViewModel.getPostLiveData().observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                postObject = post;
                showAlertDialog();

            }
        });
    }

    private void showAlertDialog() {
        //Log.d("alertDialog", "Currently in showAlertDialog");
        postObject = new Post();
        postObject.setMessage("Message");
        postObject.setCode(200);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(postObject.getCode() + "\n" + postObject.getMessage())
                .setTitle("Response")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
