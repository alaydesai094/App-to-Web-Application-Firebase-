package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddItems extends AppCompatActivity {


    // DEBUG variable
    public static final String TAG = "Alay";

    // Outlets

    EditText usernameBox;
    EditText noteBox;
    Button btnSubmit;

    String id;
    int num;

    // Create the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);


        // setup the edittext outlet

        usernameBox = (EditText) findViewById(R.id.EditName);
        noteBox = (EditText) findViewById(R.id.EditNote);

        btnSubmit = (Button) findViewById(R.id.submitButton);

        usernameBox = (EditText) findViewById(R.id.EditName);
        noteBox = (EditText) findViewById(R.id.EditNote);


    }





    public void submitPressed(View v) {

        // 0. disable the button
        Button b = (Button) findViewById(R.id.submitButton);
        //b.setEnabled(false);

        num = num + 1;
        id = "UID"+ num;


        Log.d(TAG, "Submit Buttom");
        // 1. get user input
        String name = usernameBox.getText().toString();
        String note = noteBox.getText().toString();


        // 4. create a dictionary to store your data
        // - We will be sending this dictionary to Firebase
        Map<String, Object> gobieData = new HashMap<>();
        gobieData.put("name", name);
        gobieData.put("note", note);

        Log.d(TAG,"Going to database");
        //5. connect to firebase
        // Add a new document with a ID = gameID

        final DocumentReference ref = db.collection("gobie").document(id);
        ref.set(gobieData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Data added to firebase");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                         Log.d(TAG, "Error adding document", e);
                    }
                });


    }



}
