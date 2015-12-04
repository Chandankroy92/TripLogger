package com.example.singh.log;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity implements OnClickListener {

    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText editTextUserName;
    EditText editTextPassword;
    Button btnSignIn, btnLinkRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextPassword = (EditText) findViewById(R.id.etpass1);
        editTextUserName = (EditText) findViewById(R.id.etname);

        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(this);
        btnLinkRegister = (Button) findViewById(R.id.link_register);
        btnLinkRegister.setOnClickListener(this);


// create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

    }
    // Methos to handleClick Event of Sign In Button

// get the Refferences of views


// Set On ClickList
    public void onClick(View v) {
// get The User name and Password
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

// fetch the Password form database for respective user name
        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

// check if the Stored password matches with Password entered by user
        if (password.equals(storedPassword)) {
            Intent i = new Intent(login.this, index.class);
            startActivity(i);

        } else {
            Toast.makeText(login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
            editTextUserName.requestFocus();
        }



            if (v.getId() == R.id.link_register) {
                Intent j = new Intent(login.this, sign.class);
                startActivity(j);
            }
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();

// Close The Database
            loginDataBaseAdapter.close();
        }
    }