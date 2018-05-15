package com.example.giso.tadm_game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    ImageButton nuevoJuego, puntaje;
    Button salir, acerca;
    ImageView imagen, asteroid;
    Animation blinkAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        asteroid=findViewById(R.id.asteroid);

        imagen = (ImageView)findViewById(R.id.imagen);
        imagen.setBackgroundResource(R.drawable.animation_space);
        AnimationDrawable anim = (AnimationDrawable) imagen.getBackground();
        anim.start();

        nuevoJuego=findViewById(R.id.nuevoJuego);
        //salir=findViewById(R.id.salir);
        puntaje=findViewById(R.id.puntaje);
        //acerca=findViewById(R.id.acerca);

        blink();

        nuevoJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNuevoJuego = new Intent(getApplicationContext(), Juego.class);

                startActivity(intentNuevoJuego);
            }
        });

       /* salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(), "Hasta pronto!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });*/
    }

    public void blink() {
        blinkAnimation = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.blink);
        asteroid.startAnimation(blinkAnimation);
    }
}
