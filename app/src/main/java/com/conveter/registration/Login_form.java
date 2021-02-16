package com.conveter.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_form extends AppCompatActivity {
    EditText Email, textPassword, textConfirmPassword;
    Button btn_Login;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");

        Email = (EditText) findViewById(R.id.txt_email);
        textPassword = (EditText) findViewById(R.id.txt_pass);
        textConfirmPassword = (EditText) findViewById(R.id.txt_cmpass);
        btn_Login = (Button) findViewById(R.id.bt_log);


        firebaseAuth = FirebaseAuth.getInstance();
        btn_Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String compassword = textConfirmPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(compassword)) {
                    Toast.makeText(Login_form.this, "Please Enter ConfirmPassword", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (password.length() < 6) {
                    Toast.makeText(Login_form.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                if (password.equals(compassword)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login_form.this, (task)-> {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        Toast.makeText(Login_form.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Login_form.this, "Authentication Fail", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...

                });
                }
            }
        });


    }
    public void bt_Reg(View view)
    {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
