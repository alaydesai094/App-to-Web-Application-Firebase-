package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Comment extends AppCompatActivity {



    // DEBUG variable
    public static final String TAG = "Alay";

    // Outlets

    TextView ReadName;
    TextView ReadNote;
    TextView ReadComment;

    EditText EditReply;

    String id;
    int num;

    String passID;

    // Create the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            passID = extras.getString("key");
            //The key argument here must match that used in the other activity
        }


        // setup the edittext outlet

        ReadName = (TextView) findViewById(R.id.ReadName);
        ReadNote = (TextView) findViewById(R.id.ReadNote);
        ReadComment = (TextView) findViewById(R.id.ReadComment);


        DocumentReference docRef = db.collection("gobie").document(passID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        String name = document.getData().get("name").toString();
                        String note = document.getData().get("note").toString();
                        //String comment = document.getData().get("Comment").toString();


                        ReadName.setText(name);
                        ReadNote.setText(note);
                       // ReadComment.setText(comment);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }


    public void BackButton(View view) {

        Log.d("Alay", " Add button!!!!");

        // creating a segue in Android
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }





//    public void replyPressed(View v) {
//
//        // 0. disable the button
//        Button b = (Button) findViewById(R.id.replyButton);
//        //b.setEnabled(false);
//
//        Log.d(TAG, "Submit Buttom");
//        // 1. get user input
//        String reply = EditReply.getText().toString();
//
////        // 4. create a dictionary to store your data
////        // - We will be sending this dictionary to Firebase
//        Map<String, Object> gobieData = new HashMap<>();
//        gobieData.put("Reply", reply);
//
//        Log.d(TAG,"Going to database");
//        //5. connect to firebase
//        // Add a new document with a ID = gameID
//
//        final DocumentReference ref = db.collection("gobie").document("UID1");
//        // Set the "isCapital" field of the city 'DC'
//       ref
//                .update(gobieData)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshot successfully updated!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error updating document", e);
//                    }
//                });
//
//    }
//



}
