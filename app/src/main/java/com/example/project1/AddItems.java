package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.api.LogDescriptor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class AddItems extends AppCompatActivity {


    // DEBUG variable
    public static final String TAG = "Alay";

    // Outlets

    EditText usernameBox;
    EditText noteBox;
    Button btnSubmit, btnChoose;

    ImageView imageView;

    Uri imageFromGallery;


    String id;
    String num;

    // Create a storage reference from our app



    // Create the database
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    // Create the database
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);


        // setup the edittext outlet

        usernameBox = (EditText) findViewById(R.id.EditName);
        noteBox = (EditText) findViewById(R.id.EditNote);

        btnSubmit = (Button) findViewById(R.id.submitButton);
        btnChoose = (Button) findViewById(R.id.Choosebtn);

       imageView = (ImageView) findViewById(R.id.ShowImg);

        usernameBox = (EditText) findViewById(R.id.EditName);
        noteBox = (EditText) findViewById(R.id.EditNote);





    }





    public void submitPressed(View v) {

        // 0. disable the button
        Button b = (Button) findViewById(R.id.submitButton);
        //b.setEnabled(false);

//        Log.d(TAG, "Upload() started")
        //num = num + 1;

        Random r = new Random();
        int randomNumber = r.nextInt(10000);


        id = "UID"+ randomNumber;


        Log.d(TAG, "Submit Buttom");
        // 1. get user input
        String name = usernameBox.getText().toString();
        String note = noteBox.getText().toString();


        // 4. create a dictionary to store your data
        // - We will be sending this dictionary to Firebase
        Map<String, Object> gobieData = new HashMap<>();
        gobieData.put("name", name);
        gobieData.put("note", note);
        gobieData.put("id", id);

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


        Log.d(TAG, "Going to Upload Images");
        fileUpload();





    }



    public void BackButton(View view) {

        Log.d("Alay", " Add button!!!!");

        // creating a segue in Android
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

//    private String getExtension(Uri uri){
//
//        ContentResolver cr = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
//
//    }




    public void photoButtonPressed(View v) {

        Log.d("Alay", " Choose button!!!!");

        getPhotoFromGallery();
        Log.d("Alay", " Getphoto()!!!!");


    }



    public void getPhotoFromGallery() {
       Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.setType("image/*");

        // Remember: Choose any number for the request code!
        startActivityForResult(i, 123);
    }




    // Function for showing the permission popup box.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // This request code should match the number you chose in the .requestPermissions(...) function.
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhotoFromGallery();
            }
        }

    }


    // After the user selects a photo, run this code
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // This function will run anytime an Activity closes.
        // The only way for your code to tell the difference between the Activities is
        // through the REQUESTCODE.  Every Activity should have a different code!
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {

            imageFromGallery = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageFromGallery);

                imageView.setImageBitmap(bitmap);
                Log.d("Alay", " Got Image!!!!" +imageFromGallery );

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void  fileUpload() {

        Uri file = imageFromGallery;
        StorageReference riversRef = storageRef.child("images/"+id);
        riversRef.putFile(file)
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(AddItems.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(AddItems.this, "Uploaded", Toast.LENGTH_SHORT).show();

            }
        });


    }

}

