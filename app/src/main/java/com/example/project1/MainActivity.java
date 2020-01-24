package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // DEBUG variable
    public static final String TAG = "Alay";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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




}
