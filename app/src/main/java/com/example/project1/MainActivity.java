package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "JENELLE";

    // 1. Choose any number to go here
    // This number will be used to identify your requests to/from Firebase
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public void onStart() {
        super.onStart();

        // 2. Setup the Firebase Authentication variable
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // 3. Check if the user is ALREADY logged in
        // If logged in already, do something
        // If NOT logged in, then show them the login UI
        // -----------------------------------------------
        if (auth.getCurrentUser() != null) {

            // 3a. User is ALREADY logged in:
            Log.d(TAG, "Already signed in!");
            Toast.makeText(this,"User already logged in!", Toast.LENGTH_SHORT);

            Log.d("Alay", " Comment button!!!!");

            // creating a segue in Android
            Intent i = new Intent(this, Home.class);
            startActivity(i);

        }
        else {

            // 3b. User is NOT logged in!

            Log.d(TAG, "Not signed in!");

            /*
            // This is for email/password authentication
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    RC_SIGN_IN);
            */


            // 4. Show the UI for email/password + phone authentication
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .build(),
                    RC_SIGN_IN);


        }

    }


    // 5. This function automatically gets run when the person finishes
    // entering their information into the login screen.
    // This is a CALL BACK function!
    // ------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            // 5a. The callback will send you some information and store it in this variable
            // (eg: success or error code)
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // 6. If login was successful, run this code
            if (resultCode == RESULT_OK) {


                // 7. OPTIONAL: You can get the "user" object with this code
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // 8. Send the user to the next screen!
                Intent i = new Intent(this, Home.class);
                startActivity(i);
            }
            // 6b. If login failed, run this code.
            else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                if (response == null) {
                    Log.d(TAG, "User cancelled the signin process");

                }
                else {
                    Log.d(TAG,"an error occurred during login");
                    Log.d(TAG, response.getError().getMessage());
                }
            }
        }
    }




}