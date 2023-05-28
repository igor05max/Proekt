package com.example.proekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView textView_email = findViewById(R.id.editTextTextEmailAddress2);
        TextView textView_password = findViewById(R.id.editTextTextPassword3);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        TextView textView = findViewById(R.id.est_accaunt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        Button reg = findViewById(R.id.button_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textView_email.getText().toString();
                String password = textView_password.getText().toString();

                RegisterUser(email, password);
            }
        });

    }

    private void RegisterUser(String email, String password) {

        loadingBar.setTitle("Регистрация");
        loadingBar.setMessage("Подождите, пожалуйста...");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(RegistrationActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }
        });
    }
}