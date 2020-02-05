package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class Home extends AppCompatActivity {

    // DEBUG variable
    public static final String TAG = "Alay";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





    }


    public void AddButton(View view) {

        Log.d("Alay", " Add button!!!!");

        // creating a segue in Android
        Intent i = new Intent(this, AddItems.class);
        startActivity(i);

    }

    public void CommentButton(View view) {

        Log.d("Alay", " Comment button!!!!");

        // creating a segue in Android
        Intent i = new Intent(this, Comment.class);
        startActivity(i);

    }


    public void LogoutButton(View view) {

        FirebaseAuth auth = FirebaseAuth.getInstance();


        Log.d("Alay", "Logout button!!!!");

        if (auth.getCurrentUser() != null) {

         logout();

        }

    }


    // 9. Code for logging a user out.
    // ------------------------------------
    public void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out

                        Log.d(TAG, "Logged out!");
                        Toast.makeText(getApplicationContext(),"Logged out!", Toast.LENGTH_SHORT);

                        //startActivity(new Intent(MyActivity.this, SignInActivity.class));
                        finish(); //close the app
                    }
                });
    }



}
