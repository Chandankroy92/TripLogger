package com.example.singh.log;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by singh on 30/12/15.
 */
public class ImageDisplayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image_layout);

        ImageView imgDisplayImage = (ImageView) findViewById(R.id.imgDisplayImage);

        String imagePath = getIntent().getExtras().getString("imagePath");
        File imageFile = new File(imagePath);
        if(imageFile.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imgDisplayImage.setImageBitmap(bitmap);
        }
    }
}
