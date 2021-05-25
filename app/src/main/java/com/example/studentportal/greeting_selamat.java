package com.example.studentportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class greeting_selamat extends AppCompatActivity {
    private static int SPLASH_SCREEN= 5000;
    Button greetbuton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting_selamat);
        greetbuton= findViewById(R.id.buttongreet);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent= new Intent(greeting_selamat.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_SCREEN);

        greetbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(greeting_selamat.this, LoginActivity.class);
                startActivity(intent);
                greeting_selamat.this.finish();
            }
        });
    }
}