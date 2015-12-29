package com.example.singh.log;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

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
        btnLinkRegister = (Button) findViewById(R.id.link_register);

        //Register click listener
        btnSignIn.setOnClickListener(this);
        btnLinkRegister.setOnClickListener(this);


        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

    }
    // Methos to handleClick Event of Sign In Button

    // get the Refferences of views


    // Set On ClickList
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.link_register) {
            System.out.println("kkk click over register");
            Intent j = new Intent(LoginActivity.this, SigningActivity.class);
            startActivity(j);
            finish();
        } else if (v.getId() == R.id.buttonSignIn) {
            System.out.println("kkk click over signing");
            // get The User name and Password
            String userName = editTextUserName.getText().toString();
            String password = editTextPassword.getText().toString();

            // fetch the Password form database for respective user name
            String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

            // check if the Stored password matches with Password entered by user
            if (password.equals(storedPassword)) {
                Intent i = new Intent(LoginActivity.this, index.class);
                startActivity(i);
            } else {
                Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                editTextUserName.requestFocus();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}