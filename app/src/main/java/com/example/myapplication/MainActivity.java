package com.example.myapplication;



import android.content.ActivityNotFoundException;
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
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView imageview;
    final int PIC_CROP = 1;
    String filename_text;
ActivityResultLauncher<Intent> resultlauncher;
    private Bundle savedInstanceState;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PIC_CROP) {
            if (data != null) {

                Bundle extras = data.getExtras();
                Bitmap selectedBitmap = extras.getParcelable("data");


                imageview.setImageBitmap(selectedBitmap);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        imageview =findViewById(R.id.imageView2);
        registerResult();
        btn.setOnClickListener(view ->pickimage());

    }

            private void pickimage () {
                Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
                resultlauncher.launch(intent);


            }


            private void registerResult() {
                resultlauncher = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),

                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                try {

                                    Uri uri = result.getData().getData();


                                    imageview.setImageURI(uri);
                                    //performCrop(uri );
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "Pic", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                );

            }
            /*private void performCrop (Uri Uri){
        try {
                    Intent cropIntent = new Intent("com.example.myapplication");
                    // indicate image type and Uri
                    cropIntent.setDataAndType(Uri, "image/*");
                    // set crop properties here
                    cropIntent.putExtra("crop", true);
                    // indicate aspect of desired crop
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    // indicate output X and Y
                    cropIntent.putExtra("outputX", 128);
                    cropIntent.putExtra("outputY", 128);
                    // retrieve data on return
                    cropIntent.putExtra("return-data", true);
                    // start the activity - we handle returning in onActivityResult
                    startActivityForResult(cropIntent, PIC_CROP);
                }
                // respond to users whose devices do not support the crop action
                catch (ActivityNotFoundException anfe) {

                    String errorMessage = " your device doesn't support the crop action!";
                    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }


            }*/


}


