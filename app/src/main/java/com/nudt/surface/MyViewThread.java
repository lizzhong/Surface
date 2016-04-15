package com.nudt.surface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * Created by Li-Zezhong on 2016/4/8.
 */
public class MyViewThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private boolean run;
    private Paint p;

    public MyViewThread(SurfaceHolder surfaceHolder){

        this.surfaceHolder=surfaceHolder;
        run=true;
    }

    @Override
    public void run() {

        Canvas canvas=null;

        while (run){
            try {
                canvas=surfaceHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT);//设置画布透明

                p = new Paint();
                p.setColor(Color.RED);
                p.setTextSize(30);

                p.setAntiAlias(true);
                p.setStyle(Paint.Style.STROKE);
                //canvas.drawPoint(100.0f, 100.0f, p);
                //canvas.drawLine(0, 110, 500, 110, p);
                //canvas.drawCircle(110, 110, 10.0f, p);

                Rect rect = new Rect(100, 50, 380, 330);

                canvas.drawRect(rect,p);
                canvas.drawText("将目标放入方框中", 100, 410, p);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}

