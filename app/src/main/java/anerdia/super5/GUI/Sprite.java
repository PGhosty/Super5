package anerdia.super5.GUI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Random;

import anerdia.super5.R;

public class Sprite {
    private int xSpeed = 10;
    private int ySpeed = 15;
    private int xdirection=1;
    private int ydirection=1;

    private Bitmap bmp;
    private GameView gameView;
    private int x=0;
    private int y=0;

    private int r=0;
    private int g=0;
    private int b=0;

    private int size=200;

    private Drawable drawable;

    public Sprite(GameView view) {
        gameView=view;

        Random rnd = new Random();
        ySpeed = rnd.nextInt(25)+5;
        xSpeed = rnd.nextInt(25)+5;
        size = rnd.nextInt(300)+50;
        bmp = BitmapFactory.decodeResource(gameView.getResources(), R.drawable.lebenslicht);

        r=rnd.nextInt(255);
        g=rnd.nextInt(255);
        b=rnd.nextInt(255);

        drawable = new BitmapDrawable(gameView.getResources(),bmp);
        drawable.mutate().setColorFilter(null);
        drawable.mutate().setColorFilter(new PorterDuffColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY));
    }

    private void bounceOff() {
        if (x > gameView.getWidth() - size - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > gameView.getHeight() - size - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
    }

    public void onDraw(Canvas canvas){
        bounceOff();
        drawable.setBounds(x,y,x+size,y+size);
        drawable.draw(canvas);
    }
}