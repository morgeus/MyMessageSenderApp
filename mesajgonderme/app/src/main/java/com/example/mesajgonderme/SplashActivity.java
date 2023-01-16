package com.example.mesajgonderme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Thread wait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Tools.context = getApplicationContext();
        firebaseAuth = FirebaseAuth.getInstance();
        SplashThread();

        if(firebaseAuth.getCurrentUser()!=null){
            Tools.showMessage("Zaten giriş yaptınız. Ana ekrana gönderiliyorsunuz.");
            wait.start();
        }
        else{
            Tools.showMessage("Lütfen giriş yapın veya kaydolun.");
        }
    }
    public void loginclicksplash(View view){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }
    public void signupclicksplash(View view){
        startActivity(new Intent(SplashActivity.this, SignupActivity.class));
    }

    public void SplashThread(){
        wait = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}