package anerdia.super5.GUI;


import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {
    private static final long fps = 25;
    private GameView theView;
    private boolean isRunning = false;

    public GameLoopThread(GameView theView) {
        this.theView = theView;
    }

    public void setRunning(boolean run) {
        isRunning = run;
    }

    @SuppressLint("WrongCall")     @Override
    public void run() {
        long ticksPerSecond = 1000 / fps;
        long startTime, sleepTime;
        while (isRunning) {
            Canvas theCanvas = null;
            startTime = System.currentTimeMillis();
            try {
                theCanvas = theView.getHolder().lockCanvas();
                synchronized (theView.getHolder()) {
                    theView.onDraw(theCanvas);
                }
            } finally {
                if (theCanvas != null) {
                    theView.getHolder().unlockCanvasAndPost(theCanvas);
                }
            }
            sleepTime = ticksPerSecond - System.currentTimeMillis() - startTime;
            try {
                if(sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            }
            catch (Exception e)
            {
            }
        }
    }

}