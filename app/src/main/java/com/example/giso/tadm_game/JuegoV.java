package com.example.giso.tadm_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by giso on 07/05/18.
 */

public class JuegoV extends SurfaceView implements Runnable  {

    //boolean variable to track if the game is playing or not
    volatile boolean estaJugando;
    private Thread juegoHilo = null;
    private Jugador jugador;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    //Adding an stars list
    private ArrayList<Estrellas> estrellasArrayList = new
            ArrayList<Estrellas>();

    private Enemigo[] enemigos;
    private int cantEnemigos = 3;
    private Pum pum;


    public JuegoV(Context context, int screenx, int screeny) {
        super(context);
        jugador = new Jugador(context, screenx, screeny);

        surfaceHolder = getHolder();
        paint = new Paint();

        //adding 100 stars you may increase the number
        for (int i = 0; i < 150; i++) {
            Estrellas e = new Estrellas(screenx, screeny);
            estrellasArrayList.add(e);
        }

        enemigos = new Enemigo[cantEnemigos];
        for(int i=0; i<cantEnemigos; i++){
            enemigos[i] = new Enemigo(context, screenx, screeny);
        }
        pum = new Pum(context);
    }


    public void run(){
        while (estaJugando){
            actualiza();
            dibuja();
            control();
        }
    }

    private void actualiza(){
        //actualiza la posicion del jugador
        jugador.update();

        for (Estrellas e: estrellasArrayList){
            e.actualiza(jugador.getSpeed());
        }

        for(int i=0; i<cantEnemigos; i++){
            enemigos[i].update(jugador.getSpeed());

            if (Rect.intersects(jugador.getDetectaColision(), enemigos[i].getDetectaColision())) {
                //moving enemy outside the left edge
                pum.setX(enemigos[i].getX());
                pum.setY(enemigos[i].getY());

                enemigos[i].setX(-200);
                enemigos[i].setX(-200);
            }
        }
    }

    private void dibuja(){
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);
            //Drawing the player
            paint.setColor(Color.WHITE);
            for (Estrellas e : estrellasArrayList) {
                paint.setStrokeWidth(e.getAnchoEstrella());
                canvas.drawPoint(e.getX(), e.getY(), paint);
            }

            canvas.drawBitmap(
                    jugador.getBitmap(),
                    jugador.getX(),
                    jugador.getY(),
                    paint);

            //drawing the enemies
            for (int i = 0; i < cantEnemigos; i++) {
                canvas.drawBitmap(
                        enemigos[i].getBitmap(),
                        enemigos[i].getX(),
                        enemigos[i].getY(),
                        paint
                );
            }

            canvas.drawBitmap(
                    pum.getBitmap(),
                    pum.getX(),
                    pum.getY(),
                    paint
            );

            surfaceHolder.unlockCanvasAndPost(canvas);
        }


    }

    private void control(){
        try {
            juegoHilo.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }//end control

    public void resume(){
        estaJugando=true;
        juegoHilo = new Thread(this);
        juegoHilo.start();
    }

    public void pause() {
        estaJugando=false;
        try {
            juegoHilo.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //control
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                jugador.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                jugador.setBoosting();
                break;
        }
        return true;
    }
}
