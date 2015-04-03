package anerdia.super5.GUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Poul on 03.04.2015.
 */
public class GameView extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private GameLoopThread theGameLoopThread;
    private ArrayList<Sprite> pictures;

    public GameView(Context context) {
        super(context);
        pictures= new ArrayList<Sprite>();
        for(int i=0; i<=10;i++)
        {
            pictures.add(new Sprite(this));
        }
        theGameLoopThread = new GameLoopThread(this);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback()        {
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                theGameLoopThread.setRunning(false);
                while(retry){
                    try {
                        theGameLoopThread.join();
                        retry=false;
                    }catch(InterruptedException e){

                    }
                }

            }

            public void surfaceCreated(SurfaceHolder holder) {
                theGameLoopThread.setRunning(true);
                theGameLoopThread.start();
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {

            }

        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
        for(int i =0; i<pictures.size();i++)
        {
            pictures.get(i).onDraw(canvas);
        }
    }
}
