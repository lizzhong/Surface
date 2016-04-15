package com.nudt.surface.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nudt.surface.MyViewThread;

/**
 * Created by Li-Zezhong on 2016/4/8.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback {

    private MyViewThread myViewThread;
    private SurfaceHolder surfaceHolder;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);//设置透明
        setZOrderOnTop(true);//置顶
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        clearDraw();
    }

    public void clearDraw()
    {
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.BLUE);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
    public void draw()
    {
        myViewThread = new MyViewThread(surfaceHolder);
        myViewThread.setRun(true);
        myViewThread.start();
    }
}
