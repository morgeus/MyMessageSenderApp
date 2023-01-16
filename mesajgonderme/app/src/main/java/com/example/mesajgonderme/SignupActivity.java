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

public class SignupActivity extends AppCompatActivity {

    Button signup;
    EditText email,pass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = findViewById(R.id.buttonsignupsignup);
        email = findViewById(R.id.editTextTextEmailAddress2);
        pass = findViewById(R.id.editTextTextPassword2);
        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupClick();
            }
        });
    }

    private void signupClick(){
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if (userEmail.isEmpty()){
            Tools.showMessage("email boş kalamaz!");
        }
        if (userPass.isEmpty() || userPass.length()<6){
            Tools.showMessage("Şifre geçersiz!");
        }

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Tools.showMessage("Kayıt başarılı!");
                            startActivity(new Intent(SignupActivity.this, SplashActivity.class));
                        }
                        else{
                            Tools.showMessage("Kayıt başarısız!");
                        }

                    }
                });

    }
    public void loginclicksignup(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }
}