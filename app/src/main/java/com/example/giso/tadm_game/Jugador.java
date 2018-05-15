package com.example.giso.tadm_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by giso on 07/05/18.
 */

public class Jugador {

    private Bitmap bitmap;

    //coordinates
    private int x;
    private int y;
    private int speed = 0;//motion speed of the character
    private boolean boosting;//boolean variable to track the ship is boosting or not
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;
    private Rect detectaColision;


    public Jugador(Context context, int screenx, int screeny) {
        x = 75;
        y = 50;
        speed = 1;
        boosting = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        maxY = screeny - bitmap.getHeight();
        minY = 0;

        detectaColision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }//constructor

    public void setBoosting(){boosting=true;}
    public void stopBoosting(){boosting=false;}

    //Method to update coordinate of character
    public void update(){
        //if the ship is boosting
        if (boosting) {

            speed += 2;//speeding up the ship
        } else {

            speed -= 5;//slowing down if not boosting
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;//controlling the top speed
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        y -= speed + GRAVITY;//moving the ship down

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        //updating x coordinate
        //x++;

        detectaColision.left = x;
        detectaColision.top = y;
        detectaColision.right = x + bitmap.getWidth();
        detectaColision.bottom = y + bitmap.getHeight();
    }

    public Rect getDetectaColision() {
        return detectaColision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
