package com.example.a123.uploadpic;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    OpenPhoto openPhoto;
    ImageView showSelectImage;
    DisplayMetrics metrics; //取得螢幕大小
    private static int CAMERA;
    private static int PHOTO;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSelectImage=(ImageView)findViewById(R.id.showSelectImage);
        metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        openPhoto=new OpenPhoto();
        CAMERA=openPhoto
    }


}
