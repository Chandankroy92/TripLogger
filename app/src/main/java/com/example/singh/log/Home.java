package com.example.singh.log;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class Home extends Activity implements View.OnClickListener {

    ImageButton img_camera,img_gallery,img_feedback,img_location,img_login;

    private static final int PICK_FROM_FILE = 1, PICK_FROM_CAMERA = 2;
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


        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

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
                Intent j=new Intent(Home.this,gallery.class);
                startActivity(j);
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
}

