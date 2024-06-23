package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView imageview;
ActivityResultLauncher<Intent> resultlauncher;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        imageview =findViewById(R.id.imageView2);
        registerResult();
        btn.setOnClickListener(view ->pickimage());

    }
    private void pickimage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultlauncher.launch(intent);
    }



    private void registerResult(){
        resultlauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

                new ActivityResultCallback <ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result){
                        try {
                            Uri imageuri = result.getData().getData();
                            imageview.setImageURI(imageuri);
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "die", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }



}