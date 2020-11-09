package com.java.oop.lab.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterCustomer extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnRegister;
    private TextInputEditText mName,mEmail,mPassFirst,mpassSecond;
    private String email,pass,pass2,name;
    private CheckBox mchekBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);
        mAuth=FirebaseAuth.getInstance();
        btnRegister=findViewById(R.id.btn_Register_User);
        mName=findViewById(R.id.cus_name);
        mEmail=findViewById(R.id.cus_email_register);
        mPassFirst=findViewById(R.id.first_password);
        mpassSecond=findViewById(R.id.conf_password);
        mchekBox=findViewById(R.id.chekbox_terms);
        mchekBox.setChecked(false);
        btnRegister.setEnabled(false);

        mchekBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) btnRegister.setEnabled(true); else btnRegister.setEnabled(false);

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=mName.getText().toString().trim();
                email=mEmail.getText().toString().trim();
                pass=mPassFirst.getText().toString().trim();
                pass2=mpassSecond.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(pass2)){
                    if (TextUtils.isEmpty(name))mName.setError("Requires");
                    if (TextUtils.isEmpty(email))mEmail.setError("Requires");
                    if (TextUtils.isEmpty(pass))mPassFirst.setError("Requires");
                    if (TextUtils.isEmpty(pass2))mpassSecond.setError("Requires");
                }else if( !pass.equals(pass2)){
                    mpassSecond.setError("Password Not Matched");

                }else {
                       newUser(email,pass);
                }
            }
        });
    }
    public void newUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplication(), "Registered Successully :",
                                    Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                              startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getApplication(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
