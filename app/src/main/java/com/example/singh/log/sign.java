package com.example.singh.log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign extends Activity implements View.OnClickListener {
    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editemail,editPhone;
    Button btnCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);

// get Instance of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        editTextUserName=(EditText)findViewById(R.id.etnames);
        editTextPassword=(EditText)findViewById(R.id.etpassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.etpass);
        editemail=(EditText)findViewById(R.id.etemail);
        editPhone=(EditText)findViewById(R.id.etphone);

        btnCreateAccount=(Button)findViewById(R.id.btnRegister);
        Button b2=(Button)findViewById(R.id.btnLinkToLoginScreen);
        b2.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String email=editemail.getText().toString();
                String phone=editPhone.getText().toString();


                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    editTextPassword.requestFocus();
                    editTextConfirmPassword.requestFocus();
                    return;
                }
                else if(validatephone(editPhone.getText().toString())) {
                    
                    editPhone.setError("invalid phone number");
                    editPhone.requestFocus();
                }
                 else if (!validateemail(editemail.getText().toString()))
                {
                    editemail.setError("invalid email");
                    editemail.requestFocus();


                }

               else if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
// Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName,password,email,phone);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    private boolean validateemail(String emailval) {
        String pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patern=Pattern.compile(pattern);
        Matcher matcher=patern.matcher(emailval);
        return matcher.matches();
    }

    private boolean validatephone(String s) {
        if(editPhone.length()>10||editPhone.length()<10)
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnLinkToLoginScreen)
        {
            Intent i= new Intent(sign.this,login.class);
            startActivity(i);
        }
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

}
