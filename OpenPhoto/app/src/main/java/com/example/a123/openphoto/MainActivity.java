package com.example.a123.openphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    OpenPhoto openPhoto;
    ImageView imageView;
    DisplayMetrics metrics;
    private static int CAMERA;
    private static int PHOTO;
    FirebaseDatabase database;
    DatabaseReference myRef;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        openPhoto = new OpenPhoto();
        CAMERA = openPhoto.GetCAMERA();
        PHOTO = openPhoto.GetPHOTO();
        OpenPhoto();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("image");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Debug", dataSnapshot.getValue() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void OpenPhoto(){
        Intent intent=openPhoto.Open();
        startActivityForResult(intent, PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap result =openPhoto.CheckActivityResult(MainActivity.this,requestCode,data,metrics);
        if(result!=null)
            imageView.setImageBitmap(result);

            myRef.setValue(Uitlity.BitmapToString(result));
        super.onActivityResult(requestCode, resultCode, data);
    }
}