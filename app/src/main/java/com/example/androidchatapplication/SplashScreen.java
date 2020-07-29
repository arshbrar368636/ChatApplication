package com.example.androidchatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView slidetext,slidetextt;
    Button btnnn;
    ImageView fadeimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        slidetext=findViewById(R.id.slide_image);
        Animation slide= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide);
        slidetext.startAnimation(slide);

        slidetextt=findViewById(R.id.slide_image2);
        slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        slidetextt.startAnimation(slide);



        fadeimg=findViewById(R.id.fade_img);
        Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        fadeimg.startAnimation(anim);

        btnnn=findViewById(R.id.btncontinue);
        btnnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

    }
}
