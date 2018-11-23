package com.yangxiong.gisuper.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class DemoSurfaceView extends SurfaceView  implements Callback{

    LoopThread thread;

    public DemoSurfaceView(Context context) {
        super(context);

        init(); //初始化,设置生命周期回调方法

    }

    public DemoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
         init(); //初始化,设置生命周期回调方法
    }

    public DemoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         init(); //初始化,设置生命周期回调方法
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DemoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
         init(); //初始化,设置生命周期回调方法                 
    }

    private void init(){

        SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
        thread = new LoopThread(holder, getContext());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning = true;
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行绘制的绘制线程
     * @author Administrator
     *
     */
    class LoopThread extends Thread{

        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;

        public LoopThread(SurfaceHolder surfaceHolder,Context context){

            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;

            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        @Override
        public void run() {

            Canvas c = null;

            while(isRunning){

                try{
                    synchronized (surfaceHolder) {

                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);
                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }

            }

        }

        public void doDraw(Canvas c){

            //这个很重要，清屏操作，清楚掉上次绘制的残留图像
            c.drawColor(Color.WHITE);

            c.translate(40, 40);
            c.drawCircle(0,0, radius++, paint);
/*            Path path = new Path();
            path.lineTo(200, 400);
//(200, 400)到（400,600）画一条直线
            path.lineTo(400, 600);
//以（400,600）为起始点（0
            c.drawPath(path,paint);*/
            if(radius > 100){
                radius = 10f;
            }

        }

    }

}