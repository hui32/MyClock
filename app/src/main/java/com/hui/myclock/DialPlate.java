package com.hui.myclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * 绘制表盘
 * Created by liuhui on 16/5/25.
 */
public class DialPlate extends View {
    private Paint mPaint = new Paint();
    private int mWidth = 800;
    private int mHeight = 800;
    private float o_x ;
    private float o_y ;
    private int mRadius = 350;
    private int hRadius = mRadius - 200;
    private int minRadius = mRadius - 150;
    private int sRadius = mRadius - 100;
    private int currentH;
    private int currentM;
    private int currentS;
    private String TAG = DialPlate.class.getSimpleName();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public DialPlate(Context context) {
        this(context,null);
        //getCurrentTime();
    }

    public DialPlate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.e(TAG,"wSpecMode = "+wSpecMode+" hSpecMode = "+hSpecMode+" 1"+getWidth()+" -"+getHeight());
        /*if (wSpecMode == MeasureSpec.EXACTLY&&hSpecMode == MeasureSpec.EXACTLY){
            Log.e(TAG,"wSpecMode = "+wSpecMode+" hSpecMode = "+hSpecMode+" 2"+getWidth()+" -"+getHeight());

            mWidth = mHeight = Math.min(getWidth(),getHeight());
            mRadius = mWidth - 50*mWidth/16;
            hRadius = mRadius - 200*mWidth/16;
            minRadius = mRadius - 150*mWidth/16;
            sRadius = mRadius - 100*mWidth/16;
        }*/

        setMeasuredDimension(mWidth,mHeight);
        o_x = getWidth()/2;
        o_y = getHeight()/2;
    }

    private void getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        currentH = calendar.get(Calendar.HOUR);
        currentM = calendar.get(Calendar.MINUTE);
        currentS = calendar.get(Calendar.SECOND);
        //Log.e("getCurrentTime - data","h = "+currentH+"  m = "+currentM+" s = "+currentS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getCurrentTime();
        mPaint.setColor(Color.BLACK);
        //mPaint.setARGB(155, 167, 190, 206);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);

        canvas.drawCircle(o_x,o_y,mRadius,mPaint);
        mPaint.setStrokeWidth(13);
        canvas.drawCircle(o_x,o_y,3,mPaint);


        //秒
        mPaint.setStrokeWidth(3);
        canvas.drawLines(getPosition(currentS,sRadius,6),mPaint);

        //分
        mPaint.setStrokeWidth(5);
        canvas.drawLines(getPosition(currentM,minRadius,6,(float)currentS/60),mPaint);

        //时
        mPaint.setStrokeWidth(10);
        canvas.drawLines(getPosition(currentH,hRadius,30,(float)currentM/60),mPaint);
        super.onDraw(canvas);

        mPaint.setStrokeWidth(10);
        for (int i=1;i<13;i++){
            float x = (float) Math.sin(Math.toRadians(30*i))*(mRadius-50);
            float y = (float) Math.cos(Math.toRadians(30*i))*(mRadius-50);
            canvas.drawPoint(o_x+x,o_y-y,mPaint);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postInvalidate();
            }
        },1000);
    }

    private float[] getPosition(int index,float radius,int minScale){
        float[] point = new float[4];
        point[0] = o_x;
        point[1] = o_y;
        point[2] = o_x+(float) Math.sin(Math.toRadians(minScale*index))*radius;
        point[3] = o_y-(float) Math.cos(Math.toRadians(minScale*index))*radius;
        return point;
    }

    private float[] getPosition(int index,float radius,int minScale,float percentage){
        float[] point = new float[4];
        point[0] = o_x;
        point[1] = o_y;
        point[2] = o_x+(float) Math.sin(Math.toRadians(minScale*index+percentage*minScale))*radius;
        point[3] = o_y-(float) Math.cos(Math.toRadians(minScale*index+percentage*minScale))*radius;
        return point;
    }
}
