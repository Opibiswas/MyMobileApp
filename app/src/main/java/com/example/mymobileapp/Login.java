package com.example.mymobileapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private TextView  gotoSignup;


    private EditText edtEmail, edtPassword;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        loginButton =  findViewById(R.id.signIn);
        gotoSignup = findViewById(R.id.gotoSignupId);

        loginButton.setOnClickListener(this);
        gotoSignup.setOnClickListener(this);


        edtEmail = findViewById(R.id.emailId);
        edtPassword = findViewById(R.id.passwordId);



        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.signIn ) {
            login();
        } else if (viewId == R.id.gotoSignupId) {
            Intent intent = new Intent(Login.this, Registration.class);
            startActivity(intent);
        }
    }


    private void login() {

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(email.isEmpty()){
            edtEmail.setError("email required");
            edtEmail.requestFocus();
            return;
        }
        else if(password.isEmpty()) {
            edtPassword.setError("email required");
            edtPassword.requestFocus();
            return;}



        else if(password.length() < 6){
            edtPassword.setError("password should be at least 6 charcter");
            edtPassword.requestFocus();
            return;
        }
        else {
            progressDialog.setMessage("Wait while login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            // try signing in the user with Firebase using the provided email and password.
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if(user.isEmailVerified()){
                            nextActivity();    //home activity
                        }else{
                            user.sendEmailVerification();
                            Toast.makeText(Login.this,"Email is not verified",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        progressDialog.dismiss();
                        String s = ""+task.getException();
                        if(s.length() == 137){
                            Toast.makeText(Login.this,"Network connection needed",Toast.LENGTH_SHORT).show();
                        }
                        else if(s.length() == 127){
                            Toast.makeText(Login.this,"Password invalid",Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(Login.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser != null){
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
            finish();
        }
    }


    private void nextActivity(){
        Intent i = new Intent(Login.this, Home.class);
        startActivity(i);
    }
}