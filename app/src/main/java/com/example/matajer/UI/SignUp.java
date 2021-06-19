package com.example.matajer.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matajer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText fristname,lastname,email,phone,password;
    Button signup , login;
    SessionManagement mSessionManagement;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initviews();
        mSessionManagement = new SessionManagement(SignUp.this);
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    private void initviews() {
        fristname = findViewById(R.id.signup_frist_name);
        lastname = findViewById(R.id.signup_last_name);
        email = findViewById(R.id.signup_email);
        phone = findViewById(R.id.signup_phone);
        password = findViewById(R.id.signup_password);
        signup = findViewById(R.id.signup_signup);
        login = findViewById(R.id.signup_login);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.signup_signup:
                if (Validation())
            mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(SignUp.this, "SignUp Unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignUp.this, "SignUp Successful!!", Toast.LENGTH_SHORT).show();
                    mSessionManagement.creatLoginSession(fristname.getText().toString(),lastname.getText().toString(),
                                                         email.getText().toString(),phone.getText().toString(),password.getText().toString());
                    startActivity(new Intent(SignUp.this,HomePage.class));
                }
                }
            });
                break;

            case R.id.signup_login:
               startActivity(new Intent(SignUp.this,Login.class));
                break;

    }

    }
    private boolean Validation() {
        if (fristname.getText().toString().isEmpty()) {
            Toast.makeText(SignUp.this, "plase enter your frist name", Toast.LENGTH_LONG).show();
        } else if (lastname.getText().toString().isEmpty()) {
            Toast.makeText(SignUp.this, "plase enter your last name", Toast.LENGTH_LONG).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(SignUp.this, "plase enter your E-mail", Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(SignUp.this, "plase enter your Password", Toast.LENGTH_LONG).show();
        } else if (password.length() < 8) {
            Toast.makeText(SignUp.this, "plase enter at least 8 charcters", Toast.LENGTH_LONG).show();
        } else if (password.length() > 16) {
            Toast.makeText(SignUp.this, "plase enter Maximum 16 charcters", Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }


    }
