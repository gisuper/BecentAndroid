package com.yangxiong.gisuper.myapplication.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.daasuu.library.DisplayObject;
import com.daasuu.library.FPSSurfaceView;
import com.daasuu.library.drawer.BitmapDrawer;
import com.daasuu.library.easing.Ease;
import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.utils.TitleBarUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslateActivity extends AppCompatActivity {
    @BindView(R.id.animation_texture_view)
    FPSSurfaceView fpsTtv;

    private HandlerThread handlerThread;
    private Handler handler;
    private ArrayList<Bitmap> bitmaps;
    private int count = 0;
    private Bitmap decodeResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        TitleBarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        ButterKnife.bind(this);
        handlerThread = new HandlerThread("TranslateHandlerThread");
        handlerThread.start( );
        handler = new Handler(handlerThread.getLooper( )) {
            @Override
            public void dispatchMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        bitmaps = new ArrayList<>( );
                        decodeResource = BitmapFactory.decodeResource(getResources( ), R.drawable.star_big_1);
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_1));
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_2));
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_3));
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_4));
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_5));
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_6));
                        bitmaps.add(BitmapFactory.decodeResource(getResources( ), R.drawable.star_small_7));
                        sendEmptyMessage(4);
                        sendEmptyMessage(2);
                        break;
                    case 2:
                        for (int i = 0; i < 10; i++) {
                            int i1 = new Random( ).nextInt(1007);
                            int i2 = new Random( ).nextInt(7);
                            int i3 = new Random( ).nextInt(10000);
                            DisplayObject bitmapDisplay = new DisplayObject( );
                            bitmapDisplay
                                    .with(new BitmapDrawer(bitmaps.get(i2)))
                                    .tween( )
                                    .tweenLoop(true)
                                    .transform(i1, -10)
                                    .waitTime(i3)
                                    /* .rotation(3000, 72)*/
                                    .to(35000, i1, 1920, 0f, Ease.LINEAR)
                                    .waitTime(0)
                                    .end( );
                            fpsTtv
                                    .addChild(bitmapDisplay);
                        }
                        if (count < 9){
                            sendEmptyMessageDelayed(2, 3000);
                        }
                        sendEmptyMessage(3);
                        count++;
                        break;
                    case 3:
                        fpsTtv.tickStart( );
                        break;
                    case 4:

                        break;
                }
            }
        };
        handler.sendEmptyMessage(1);
       /* decodeResource = BitmapFactory.decodeResource(getResources( ), R.drawable.star_big_1);
        DisplayObject tweenText = new DisplayObject();
        tweenText.with(new BitmapDrawer(decodeResource)
                .rotateRegistration(118, 118))
                .tween()
                .tweenLoop(true)
                .transform(0, 0, 255, 1f,1f, 180f)
                .rotation(3000,180)
                .to(5000, 0, 1920, 720f, Ease.CIRC_IN)
                .end();
        fpsTtv.addChild(tweenText);
        fpsTtv.tickStart( );*/

    }

}
