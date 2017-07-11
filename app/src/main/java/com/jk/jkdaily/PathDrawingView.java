package com.jk.jkdaily;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

/**
 * Created by jk on 2017/7/11.
 */

public class PathDrawingView extends View {
    private Paint paint = null; //

    private Bitmap originalBitmap = null;//原始图

//    private Bitmap new1Bitmap = null;

//    private Bitmap new2Bitmap = null;

    private float clickX = 0;

    private float clickY = 0;

    private float startX = 0;

    private float startY = 0;

    private boolean isMove = true;

    private boolean isClear = false;

    private int color = Color.RED;//默认画笔颜色

    private float strokeWidth = 20f;//默认画笔宽度

    Path mPath;

    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private Context mContext;
    private Point screenMetrics;

    public PathDrawingView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public PathDrawingView(Context context, AttributeSet atts) {
        this(context, atts, 0);
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("static-access")
    public PathDrawingView(Context context, AttributeSet atts, int defStyle) {
        super(context, atts, defStyle);
        // TODO Auto-generated constructor stub
//        originalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher).copy(Bitmap.Config.ARGB_8888, true);//白色的画板
        mContext = context;
        initHandler();
        screenMetrics = getScreenMetrics(context);
        originalBitmap = Bitmap.createBitmap(screenMetrics.x, screenMetrics.y, Bitmap.Config.ARGB_8888);
//        new1Bitmap = originalBitmap.createBitmap(originalBitmap);
        mPath = new Path();
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);

    }

    //清楚
    @SuppressWarnings("static-access")
    public void clear() {
        isClear = true;
//        new2Bitmap = originalBitmap.createBitmap(originalBitmap);
        invalidate();//重置
    }

    /**
     * 设置画笔粗细
     * @param width
     */
    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        Bitmap writer = writer(originalBitmap);
        canvas.drawBitmap(writer, 0, 0, null);
//        writer.recycle();
//        writer = null;

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//
//
//        clickX = event.getX();
//
//        clickY = event.getY();
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            //手指点下屏幕时触发
//
//            startX = clickX;
//            startY = clickY;
//            mPath.reset();
//            mPath.moveTo(clickX, clickY);
//
////   isMove =false;
////   invalidate();
////   return true;
//        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            //手指移动时触发
//            float dx = Math.abs(clickX - startX);
//            float dy = Math.abs(clickY - startY);
////   if(dx>=3||dy>=3){
//            //设置贝塞尔曲线的操作点为起点和终点的一半
//            float cX = (clickX + startX) / 2;
//            float cY = (clickY + startY) / 2;
//            mPath.quadTo(startX, startY, cX, cY);
//
//            startX = clickX;
//            startY = clickY;
//
////   }
////   isMove =true;
////   invalidate();
////   return true;
//        }
//
//
//        invalidate();
        return true;
    }

    /**
     * 初始化handler
     */

    private void initHandler(){
//        mHandlerThread = new HandlerThread("draw");
//        mHandlerThread.start();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        Bundle data = msg.getData();
                        int indexX = data.getInt("moveX");
                        int indexY = data.getInt("moveY");

                        float dx = Math.abs(indexX - heartRateStartX);
                        float dy = Math.abs(indexY - heartRateStartY);

                        //设置贝塞尔曲线的操作点为起点和终点的一半
                        float cX = (indexX + heartRateStartX) / 2;
                        float cY = (indexY + heartRateStartY) / 2;
                        mPath.quadTo(heartRateStartX, heartRateStartY, cX, cY);

                        heartRateStartX = indexX;
                        heartRateStartY = indexY;

                        invalidate();
                        break;
                    case 1:

                        break;


                }
            }
        };
    }
    /**
     * 绘制心率
     */
    private int heartRateStartX;
    private int heartRateStartY;
    private boolean isReset = true;
    public void drawHeartHate(final int indexX, final int indexY){

        heartRateStartX = indexX;
        heartRateStartY = indexY;
        if (isReset){
            isReset = false;
            mPath.reset();
            mPath.moveTo(clickX, clickY);
        }

                Message message = new Message();
                Bundle bundleX = new Bundle();
                bundleX.putInt("moveX",indexX);
                bundleX.putInt("moveY",indexY);
                message.what = 0;
                message.setData(bundleX);

                mHandler.sendMessage(message);



    }

    /**
     * 绘制
     * @param pic
     * @return
     */
    Canvas canvas = null;
    Canvas canvas2 = null;
    int tiems = 0;
    public Bitmap writer(Bitmap pic) {

        if (paint == null){
        }
        initPaint();



//        if (isClear) {
//            canvas = new Canvas(new2Bitmap);
//        } else {
//            canvas = new Canvas(pic);
//        }




        if (heartRateStartX == screenMetrics.x){
            tiems++;
            canvas = new Canvas(originalBitmap);
            Matrix m = new Matrix();
            m.setScale((float) 0.5, 1);
            canvas.drawBitmap(originalBitmap, m, paint);

        }else{
            canvas2 = new Canvas(originalBitmap);
        }


        canvas2.drawPath(mPath, paint);



        return originalBitmap;
    }

    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    private Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
//        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        Bitmap bitmap = Bitmap.createBitmap(screenMetrics.x, screenMetrics.y, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        Matrix m = new Matrix();
        m.setScale(scaleWidth, scaleHeight);
        canvas.drawBitmap(origin, m, paint);


//        if (!origin.isRecycled()) {
//            origin.recycle();
//        }
        return bitmap;
    }



    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();//初始化画笔

        paint.setStyle(Paint.Style.STROKE);//设置为画线

        paint.setAntiAlias(true);//设置画笔抗锯齿

        paint.setColor(color);//设置画笔颜色

        paint.setStrokeWidth(strokeWidth);//设置画笔宽度
    }


    /**
     * 设置画笔颜色
     */
    public void setColor(int color) {
        this.color = color;
        initPaint();
    }

    public Bitmap getPaint() {
        return originalBitmap;
    }
}
