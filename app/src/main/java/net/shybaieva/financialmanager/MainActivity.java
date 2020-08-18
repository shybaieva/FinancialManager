package net.shybaieva.financialmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

     private EditText mail, password;
     private Button logInBtn;
     private TextView forgetPassword, createAccount;
     private FirebaseAuth mAuth;
     private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        init();

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
                String mailS = mail.getText().toString().trim();
                String passwordS = password.getText().toString().trim();

                if(TextUtils.isEmpty(mailS)){
                    Toast.makeText(MainActivity.this, "Field Mail is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(passwordS)){
                    Toast.makeText(MainActivity.this, "Field password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(mailS, passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login is failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });

        //TODO reset password activity

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
            }
        });
    }

    private void init (){
        mail = findViewById(R.id.mail_login);
        password = findViewById(R.id.password_login);
        logInBtn = findViewById(R.id.logInBtn);
        forgetPassword = findViewById(R.id.forgetPassword);
        createAccount = findViewById(R.id.signUp);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Processing...");
    }
}
