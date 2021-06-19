package com.example.matajer.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matajer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button signup, login;
    TextView forgetpassword;
    FirebaseAuth firebaseAuth;
    SessionManagement sessionManagement;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManagement = new SessionManagement(this);
        initviews();
    }

    private void initviews() {
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        signup = findViewById(R.id.login_signup);
        login = findViewById(R.id.login_login);
        forgetpassword = findViewById(R.id.login_forget);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        forgetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                if (Validation())
                    authStateListener();

                break;

            case R.id.login_signup:
                startActivity(new Intent(Login.this, SignUp.class));

                break;
            case R.id.login_forget:
                startActivity(new Intent(Login.this,Forget_password.class));
                break;
        }
    }

    private boolean Validation() {
        if (email.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Please enter your mail", Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }

    public void authStateListener() {
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (!task.isSuccessful()){
                Toast.makeText(Login.this, "Login Failure, Please login again", Toast.LENGTH_SHORT).show();
            }else {
                startActivity(new Intent(Login.this, HomePage.class));
            }
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}