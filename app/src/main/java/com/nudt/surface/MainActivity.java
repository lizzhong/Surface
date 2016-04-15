package com.nudt.surface;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nudt.surface.view.MyView;

import java.io.IOException;

/**
 *2016/4/08 23:00 带扫描框的摄像机第一版
 * 1、利用双层SurfaceView初步实现了摄像机带框预览
 */
public class MainActivity extends Activity {

    private SurfaceView cameraPreview;
    private MyView myView;
    private SurfaceHolder holder1;

    private Camera camera=null;

    private class CameraPreviewCallback implements SurfaceHolder.Callback{


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            startCameraPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            stopCameraPreview();
        }
    }

    private boolean hasSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
        holder1=cameraPreview.getHolder();
        holder1.addCallback(new CameraPreviewCallback());
        holder1.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder1.setFixedSize(500, 350);
        holder1.setFormat(PixelFormat.TRANSPARENT);

        myView = (MyView) findViewById(R.id.myViewfinder_view);

    }

    private void startCameraPreview() {
        if (!hasSurface) {
            hasSurface = true;
            camera= Camera.open();
            try {
                camera.setPreviewDisplay(cameraPreview.getHolder());
                camera.setDisplayOrientation(90);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void stopCameraPreview(){
        camera.stopPreview();
        camera.release();
    }
}

