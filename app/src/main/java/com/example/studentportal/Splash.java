package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    //variabel
    Animation topAnim;
    ImageView logo;

    private static int SPLASH_SCREEN= 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        //animation
        topAnim=AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //image
        logo = findViewById(R.id.logo);

        logo.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Splash.this, greeting_selamat.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
