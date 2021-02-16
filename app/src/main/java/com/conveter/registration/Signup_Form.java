package com.conveter.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {
    EditText Name,UName,Email,Password,CPassword;
    RadioButton Male,Female;
    Button Registration;
    String gander="";



    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup_Form");
        Name=(EditText)findViewById(R.id.etName);
        UName=(EditText)findViewById(R.id.etUName);
        Email=(EditText)findViewById(R.id.etEmail);
        Password=(EditText)findViewById(R.id.etPassword);
        CPassword=(EditText)findViewById(R.id.etCPassword);
        Registration=(Button)findViewById(R.id.etReg);
        Male=(RadioButton)findViewById(R.id.EtM);
        Female=(RadioButton)findViewById(R.id.etF);

        databaseReference=FirebaseDatabase.getInstance().getReference("farmer");
        firebaseAuth=FirebaseAuth.getInstance();

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 final String fullName = Name.getText().toString().trim();
                 final String userName = UName.getText().toString().trim();
                 final String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String Fpassword = Password.getText().toString().trim();

                if (Male.isChecked()) {
                    gander = "Male";

                }
                if (Female.isChecked()) {
                    gander = "Female";
                }
                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(Signup_Form.this, "Please Enter FullName", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(Signup_Form.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(Fpassword)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (password.length() < 6) {
                    Toast.makeText(Signup_Form.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(Fpassword)) {

                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup_Form.this, (task)-> {
                                if (task.isSuccessful()) {

                                    Farmer farmer=new Farmer(fullName,userName,email,gander);
                                    // ...
                                    FirebaseDatabase.getInstance().getReference("farmer")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(farmer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Signup_Form.this, "Registration", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        }
                                    });
                                }


                        });
                 }
            });
            }
        }
