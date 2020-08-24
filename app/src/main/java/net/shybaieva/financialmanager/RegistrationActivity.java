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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    EditText mail, password;
    Button regBtn;
    TextView logInTV;

    private FirebaseAuth mAuth;
    ProgressDialog mDisalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        mDisalog = new ProgressDialog(this);

        init();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailS = mail.getText().toString().trim();
                String passwordS = password.getText().toString().trim();

                if(TextUtils.isEmpty(mailS)){
                    Toast.makeText(RegistrationActivity.this, "Field Mail is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(passwordS)){
                    Toast.makeText(RegistrationActivity.this, "Field Password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDisalog.setMessage("Processing...");
                mDisalog.show();

                mAuth.createUserWithEmailAndPassword(mailS, passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDisalog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Registration complete", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish();
                        }
                        else {
                            mDisalog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        logInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void init(){
        mail = findViewById(R.id.registration_mail);
        password = findViewById(R.id.registration_password);
        regBtn = findViewById(R.id.RegistrationBtn);
        logInTV = findViewById(R.id.logInExistingAccount);
    }
}
