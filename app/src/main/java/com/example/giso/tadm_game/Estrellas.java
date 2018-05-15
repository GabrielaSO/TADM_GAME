package com.example.giso.tadm_game;

import java.util.Random;

/**
 * Created by giso on 07/05/18.
 */

public class Estrellas {

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private int x;
    private int y;
    private int speed;

    public Estrellas(int screenx, int screeny) {
        maxX = screenx;
        maxY = screeny;
        minX = 0;
        minY = 0;
        Random r= new Random();
        x = r.nextInt(maxX);
        y = r.nextInt(maxY);
        speed = r.nextInt(15);
    }

    public void actualiza(int playerSpeed) {
        //animating the star horizontally left side
        //by decreasing x coordinate with player speed
        x -= playerSpeed;
        x -= speed;
        //if the star reached the left edge of the screen
        if (x < 0) {
            //again starting the star from right edge
            //this will give a infinite scrolling background effect
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
    }//end actualiza

    public float getAnchoEstrella() {
        //Making the star width random so that
        //it will give a real look
        float minX = 1.0f;
        float maxX = 4.0f;
        Random r = new Random();
        float finalX = r.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
