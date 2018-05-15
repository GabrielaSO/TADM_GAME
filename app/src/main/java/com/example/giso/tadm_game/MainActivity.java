package com.example.giso.tadm_game;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layoutBaja;
    Animation blinkAnimation, rotation;
    TextView nombre, materia, press, spacewar;
    ImageView imagen, asteroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        press=findViewById(R.id.press);
        spacewar=findViewById(R.id.spaceWar);
        blink();

        asteroid = findViewById(R.id.asteroid);
        rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotar);
        rotation.setFillAfter(true);
        asteroid.startAnimation(rotation);
        //animation frame
        imagen = (ImageView)findViewById(R.id.imagen);
        imagen.setBackgroundResource(R.drawable.animation_space);
        AnimationDrawable anim = (AnimationDrawable) imagen.getBackground();
        anim.start();

        layoutBaja = findViewById(R.id.layoutBaja);
        layoutBaja.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    cambioActivity();
                }
                return false;
            }
        });

    }
    public void cambioActivity(){
        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
    }
    public void blink() {
        blinkAnimation = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.blink);
        press.startAnimation(blinkAnimation);
        spacewar.startAnimation(blinkAnimation);

    }
}