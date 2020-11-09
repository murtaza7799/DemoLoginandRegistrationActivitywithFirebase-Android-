package com.java.oop.lab.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginActivity extends AppCompatActivity {
    String password="Pn41jM7u1c";
    private Button mbtnLogin,mbtnRegister;
    private TextInputEditText mtextEmail,mtextPass;
    private String pass,email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mbtnLogin=findViewById(R.id.btn_login);
        mbtnRegister=findViewById(R.id.btn_Register);
        mtextEmail=findViewById(R.id.customer_email_login);
        mtextPass=findViewById(R.id.customer_pass_login);
        firebaseAuth=FirebaseAuth.getInstance();




        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
                email=mtextEmail.getText().toString().trim();
                pass=mtextPass.getText().toString().trim();
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                if (TextUtils.isEmpty(email)){ mtextEmail.setError("Please Enter Email");}
                if(TextUtils.isEmpty(pass)){mtextPass.setError("Please Enter Password ");}
            }
             else{
                 System.out.println("test");
                 userlogin(email,pass);}

            }
        });
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterCustomer.class);
                startActivity(intent);
            }
        });
    }
    private void userlogin(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w( "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    public  void addEmployee() {

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://remotemysql.com/rASo5saTHx?useSSL=false",
                    "rASo5saTHx",
                    "Pn41jM7u1c");
            String insertQuery = "INSERT INTO employees(id, name, gender,type) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Ali");
            preparedStatement.setBoolean(3, true);
            preparedStatement.setString(4, "Permanent");


            int num = preparedStatement.executeUpdate();

            System.out.println(num + " Rows effected. ");
            System.out.println("User Added with user id : " + 5);
            Toast.makeText(this, "TEST"+5, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();

                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
