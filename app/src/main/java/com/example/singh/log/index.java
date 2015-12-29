package com.example.singh.log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class index extends AppCompatActivity implements View.OnClickListener {

ImageButton c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        c1=(ImageButton)findViewById(R.id.imageButton);
        c1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.imageButton)
        {
            Intent i=new Intent(index.this,Home.class);
            startActivity(i);

        }

    }
}
