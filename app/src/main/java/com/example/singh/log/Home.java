package com.example.singh.log;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Home extends Activity implements View.OnClickListener {

    ImageButton img_camera,img_gallery,img_feedback,img_location,img_login;

    private static final int PICK_FROM_FILE = 1, PICK_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    private String imagePath;
    File imageFilePath, mediaStorageDir;
    ImagePathInformation imagePathInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        img_camera=(ImageButton)findViewById(R.id.img_camera);
        img_gallery=(ImageButton)findViewById(R.id.img_gallery);
        img_feedback=(ImageButton)findViewById(R.id.img_feedback);
        img_location=(ImageButton)findViewById(R.id.img_location);
        img_login=(ImageButton)findViewById(R.id.img_login);

        img_gallery.setOnClickListener(this);
        img_feedback.setOnClickListener(this);
        img_location.setOnClickListener(this);
        img_login.setOnClickListener(this);
        img_camera.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
         switch(v.getId())
        {
            case R.id.img_login:
                Intent i=new Intent(Home.this,LoginActivity.class);
                startActivity(i);
                break;

            case R.id.img_gallery:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_FILE);
                break;

            case R.id.img_camera:
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFilePath = new ImagePathInformation().getOutputMediaFile();
                File rootsd = Environment.getExternalStorageDirectory();
                    mediaStorageDir = new File(rootsd.getAbsolutePath() + "/TripLogger/");

                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Log.d("TripLogger", "failed to create directory");
                        return;
                    }
                }
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

                imageFilePath = new File(mediaStorageDir.getAbsolutePath(),
                        timeStamp + ".jpg");

                mImageCaptureUri = Uri.fromFile(new File(imageFilePath.getPath()));

                intentCamera.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                        mImageCaptureUri);

                try {
                    intentCamera.putExtra("return-data", true);

                    startActivityForResult(intentCamera, PICK_FROM_CAMERA);
                } catch (ActivityNotFoundException e) {
                    System.out.println("kkkk error found here");
                    e.printStackTrace();
                }
                break;


            case R.id.img_location:
                Intent x=new Intent(Home.this,MapsActivity.class);
                startActivity(x);
                break;
            case R.id.img_feedback:
                sendFeedBack();
                break;

        }
    }

    private void sendFeedBack(){
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"singhmania@hotmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
            intent.putExtra(Intent.EXTRA_TEXT, "Life is open Trip so don't waste it n get started now!!!");
            intent.setType("message/rfc822");
            startActivity(intent);
        } catch (ActivityNotFoundException ae) {
            Toast toast=Toast.makeText(Home.this, "Sorry!No Email Client Found :(", Toast.LENGTH_LONG);
            toast.show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {

            case PICK_FROM_FILE:
                mImageCaptureUri = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(mImageCaptureUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                cursor.close();

                Intent intent = new Intent(Home.this, ImageDisplayActivity.class);
                intent.putExtra("imagePath", imagePath);
                startActivity(intent);

                break;

            case PICK_FROM_CAMERA:
                System.out.println("kkkk on camera result");
                imagePath = imageFilePath.toString();
                intent = new Intent(Home.this, ImageDisplayActivity.class);
                intent.putExtra("imagePath", imagePath);
                startActivity(intent);
                break;

        }
    }
}

