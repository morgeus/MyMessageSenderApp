package com.example.mesajgonderme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText email,pass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.buttonloginlogin);
        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginClick();
            }
        });
    }
    private void loginClick(){
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if (userEmail.isEmpty()){
            Tools.showMessage("email boş kalamaz!");
        }
        if (userPass.isEmpty() || userPass.length()<6){
            Tools.showMessage("Şifre geçersiz!");
        }

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPass).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Tools.showMessage("Giriş başarılı!");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        else{
                            Tools.showMessage("Giriş başarısız!");
                        }

                    }
                });

    }
    public void signupclicklogin(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}