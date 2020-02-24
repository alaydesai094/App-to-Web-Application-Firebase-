package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListComments extends AppCompatActivity {

    // DEBUG variable
    public static final String TAG = "Alay";

//    String [] list = new String[] {"Alay", "Dhyanee", "gobie"};
    ArrayList<String> items=new ArrayList<String>();
    ArrayList<String> ids=new ArrayList<String>();


    // Create the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ListView listView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comments);

        listView = (ListView) findViewById(R.id.listView);


        db.collection("gobie")
               // .whereEqualTo("CommentType","true")  // This is to get list of people commented
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                //Log.d(TAG, "looking for document");

                                String id = document.getData().get("id").toString();
                                String name = document.getData().get("name").toString();
                                String note = document.getData().get("note").toString();

                                // uncomment this to see the comments in next page
                                
                                //String comment = document.getData().get("Comment").toString();


                                items.add(name);
                                ids.add(id);

                                showdata();






                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Log.d(TAG, "No such document");
                        }
                    }
                });





    }


    public void showdata(){


        listView = (ListView) findViewById(R.id.listView);
        Log.d(TAG, " found document"+ items);
        Log.d(TAG, " found document"+ ids);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "loading data " + position, Toast.LENGTH_LONG)
                        .show();

                String passID = ids.get(position);

                Log.d(TAG, " passing id = "+ passID);



                Intent i = new Intent(ListComments.this, Comment.class);
                i.putExtra("key", passID);
                startActivity(i);
            }
        });

    }



}
