package com.example.singh.log;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class email extends AppCompatActivity {

        private ImageButton send;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home);

            send=(ImageButton)findViewById(R.id.imageButton6);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"singhmania@hotmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                        intent.putExtra(Intent.EXTRA_TEXT, "Life is open Trip so don't waste it n get started now!!!");
                        intent.setType("message/rfc822");
                        startActivity(intent);
                    } catch (ActivityNotFoundException ae) {
                        Toast toast=Toast.makeText(email.this, "Sorry!No Email Client Found :(", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }
            });
        }
    }

