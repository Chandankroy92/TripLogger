package com.example.singh.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Home extends ActionBarActivity implements View.OnClickListener {
    ImageButton b1,b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        b1=(ImageButton)findViewById(R.id.imageButton2);
        b2=(ImageButton)findViewById(R.id.imageButton3);
        b3=(ImageButton)findViewById(R.id.imageButton5);
        b4=(ImageButton)findViewById(R.id.imageButton4);
        b5=(ImageButton)findViewById(R.id.imageButton6);

        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);


        b1.setOnClickListener(new View.OnClickListener() {
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
            case R.id.imageButton5:
                Intent i=new Intent(Home.this,login.class);
                startActivity(i);
                break;

            case R.id.imageButton3:
                Intent j=new Intent(Home.this,gallery.class);
                startActivity(j);
                break;
            case R.id.imageButton4:
                Intent x=new Intent(Home.this,MapsActivity.class);
                startActivity(x);
                break;
            case R.id.imageButton6:
                Intent y=new Intent(Home.this,email.class);
                startActivity(y);
                break;

        }

    }
}

